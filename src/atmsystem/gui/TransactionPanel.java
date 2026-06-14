package atmsystem.gui;

import atmsystem.dao.TransactionDAO;
import atmsystem.dao.AccountDAO;
import atmsystem.model.Transaction;
import atmsystem.model.Account;
import atmsystem.model.Card;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TransactionPanel extends JPanel 
{
    private TransactionDAO transactionDAO = new TransactionDAO();
    private AccountDAO accountDAO = new AccountDAO();
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> typeCombo, accountCombo, targetAccountCombo;
    private JTextField amountField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JLabel targetAccountLabel;
    private List<Account> accountList;

    private JTextField createStyledField() 
    {
        JTextField field = new JTextField(25) 
        {
            @Override
            protected void paintComponent(Graphics g) 
            {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) 
            {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(180, 180, 180));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }
        };
        field.setOpaque(false);
        field.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        field.setPreferredSize(new Dimension(0, 60));
        field.setFont(new Font("Verdana", Font.PLAIN, 20));
        return field;
    }

    private JButton createStyledButton(String text) 
    {
        JButton button = new JButton(text) 
        {
            @Override
            protected void paintComponent(Graphics g) 
            {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(34, 85, 34));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(Color.WHITE);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {}
        };
        button.setFont(new Font("Verdana", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(180, 60));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public TransactionPanel(Card card) 
    {
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Account ID", "Type", "Amount", "Date", "Target Account ID"};
        tableModel = new DefaultTableModel(columns, 0) 
        {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(tableModel);
        table.setBackground(new Color(210, 230, 210));
        table.setGridColor(new Color(34, 85, 34));
        table.setFont(new Font("Verdana", Font.PLAIN, 16));
        table.setForeground(new Color(34, 85, 34));
        table.setRowHeight(30);
        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() 
        {{
            setHorizontalAlignment(JLabel.CENTER);
            setForeground(new Color(34, 85, 34));
            setBackground(new Color(210, 230, 210));
        }});
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setBackground(new Color(34, 85, 34));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 16));
        ((javax.swing.table.DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(0, 200));
        scrollPane.getViewport().setBackground(new Color(210, 230, 210));
        add(scrollPane, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(230, 230, 230));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 60, 20, 60);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel accountLabel = new JLabel("Account:");
        accountLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        accountLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(accountLabel, gbc);
        accountCombo = new JComboBox<>();
        accountCombo.setFont(new Font("Verdana", Font.PLAIN, 18));
        accountCombo.setPreferredSize(new Dimension(0, 60));
        gbc.gridx = 1;
        formPanel.add(accountCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        typeLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(typeLabel, gbc);
        typeCombo = new JComboBox<>(new String[]{"Withdraw", "Deposit", "Transfer"});
        typeCombo.setFont(new Font("Verdana", Font.PLAIN, 18));
        typeCombo.setPreferredSize(new Dimension(0, 60));
        gbc.gridx = 1;
        formPanel.add(typeCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        amountLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(amountLabel, gbc);
        amountField = createStyledField();
        gbc.gridx = 1;
        formPanel.add(amountField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        targetAccountLabel = new JLabel("Target Account:");
        targetAccountLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        targetAccountLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(targetAccountLabel, gbc);
        targetAccountCombo = new JComboBox<>();
        targetAccountCombo.setFont(new Font("Verdana", Font.PLAIN, 18));
        targetAccountCombo.setPreferredSize(new Dimension(0, 60));
        gbc.gridx = 1;
        formPanel.add(targetAccountCombo, gbc);
        targetAccountLabel.setVisible(false);
        targetAccountCombo.setVisible(false);

        addButton = createStyledButton("Add");
        updateButton = createStyledButton("Update");
        deleteButton = createStyledButton("Delete");
        clearButton = createStyledButton("Clear");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        buttonPanel.setBackground(new Color(230, 230, 230));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        loadAccountCombo();
        loadTransactions();

        typeCombo.addActionListener(e -> 
        {
            boolean isTransfer = typeCombo.getSelectedItem().equals("Transfer");
            targetAccountLabel.setVisible(isTransfer);
            targetAccountCombo.setVisible(isTransfer);
        });

        table.getSelectionModel().addListSelectionListener(e -> 
        {
            int row = table.getSelectedRow();
            if (row != -1) 
            {
                int accountID = (int) tableModel.getValueAt(row, 1);
                for (int i = 0; i < accountList.size(); i++) 
                {
                    if (accountList.get(i).getAccountID() == accountID) 
                    {
                        accountCombo.setSelectedIndex(i);
                        break;
                    }
                }
                typeCombo.setSelectedItem(tableModel.getValueAt(row, 2));
                amountField.setText(String.valueOf(tableModel.getValueAt(row, 3)));
            }
        });

        addButton.addActionListener(e -> addTransaction());
        updateButton.addActionListener(e -> updateTransaction());
        deleteButton.addActionListener(e -> deleteTransaction());
        clearButton.addActionListener(e -> clearFields());
    }

    private void loadAccountCombo() 
    {
        accountList = accountDAO.getAllAccounts();
        accountCombo.removeAllItems();
        targetAccountCombo.removeAllItems();
        for (Account a : accountList) 
        {
            accountCombo.addItem(a.getAccountID() + " - " + a.getType());
            targetAccountCombo.addItem(a.getAccountID() + " - " + a.getType());
        }
    }

    private void loadTransactions() 
    {
        tableModel.setRowCount(0);
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        for (Transaction t : transactions) 
        {
            tableModel.addRow(new Object[]{
                t.getTransactionID(), t.getAccountID(), t.getType(),
                t.getAmount(), t.getDateOfTransaction(), t.getTargetAccountID()
            });
        }
    }

    private void addTransaction() 
    {
        if (accountCombo.getItemCount() == 0) 
        {
            JOptionPane.showMessageDialog(this, "No accounts available. Please add an account first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (amountField.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Amount is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try 
        {
            double amount = Double.parseDouble(amountField.getText().trim());

            if (amount <= 0) 
            {
                JOptionPane.showMessageDialog(this, "Amount must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (amount > 99999) 
            {
                JOptionPane.showMessageDialog(this, "Amount cannot exceed 99,999.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int accountID = accountList.get(accountCombo.getSelectedIndex()).getAccountID();
            String type = (String) typeCombo.getSelectedItem();
            String date = LocalDate.now().toString();
            int targetAccountID = 0;

            Account account = accountDAO.getAccountByID(accountID);

            if (type.equals("Withdraw")) 
            {
                if (account.getBalance() < amount) 
                {
                    JOptionPane.showMessageDialog(this, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                account.setBalance(account.getBalance() - amount);
                accountDAO.updateAccount(account);
            } 
            else if (type.equals("Deposit")) 
            {
                account.setBalance(account.getBalance() + amount);
                accountDAO.updateAccount(account);
            } 
            else if (type.equals("Transfer")) 
            {
                if (account.getBalance() < amount) 
                {
                    JOptionPane.showMessageDialog(this, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                targetAccountID = accountList.get(targetAccountCombo.getSelectedIndex()).getAccountID();
                if (targetAccountID == accountID) 
                {
                    JOptionPane.showMessageDialog(this, "Cannot transfer to the same account.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Account targetAccount = accountDAO.getAccountByID(targetAccountID);
                account.setBalance(account.getBalance() - amount);
                targetAccount.setBalance(targetAccount.getBalance() + amount);
                accountDAO.updateAccount(account);
                accountDAO.updateAccount(targetAccount);
            }

            Transaction transaction = new Transaction(0, accountID, type, amount, date, targetAccountID);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this transaction?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            transactionDAO.addTransaction(transaction);
            loadTransactions();
            clearFields();
            JOptionPane.showMessageDialog(this, "Transaction completed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this, "Amount must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTransaction() 
    {
        int row = table.getSelectedRow();
        if (row == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a transaction to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (amountField.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Amount is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try 
        {
            double amount = Double.parseDouble(amountField.getText().trim());

            if (amount <= 0) 
            {
                JOptionPane.showMessageDialog(this, "Amount must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (amount > 99999) 
            {
                JOptionPane.showMessageDialog(this, "Amount cannot exceed 99,999.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = (int) tableModel.getValueAt(row, 0);
            int accountID = accountList.get(accountCombo.getSelectedIndex()).getAccountID();
            String type = (String) typeCombo.getSelectedItem();
            String date = (String) tableModel.getValueAt(row, 4);
            int targetAccountID = 0;

            if (type.equals("Transfer")) 
            {
                targetAccountID = accountList.get(targetAccountCombo.getSelectedIndex()).getAccountID();
                if (targetAccountID == accountID) 
                {
                    JOptionPane.showMessageDialog(this, "Cannot transfer to the same account.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Transaction transaction = new Transaction(id, accountID, type, amount, date, targetAccountID);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this transaction?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            transactionDAO.updateTransaction(transaction);
            loadTransactions();
            clearFields();
            JOptionPane.showMessageDialog(this, "Transaction updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this, "Amount must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTransaction() 
    {
        int row = table.getSelectedRow();
        if (row == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a transaction to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this transaction?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) 
        {
            transactionDAO.deleteTransaction(id);
            loadTransactions();
            clearFields();
            JOptionPane.showMessageDialog(this, "Transaction deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearFields() 
    {
        amountField.setText("");
        typeCombo.setSelectedIndex(0);
        table.clearSelection();
        targetAccountLabel.setVisible(false);
        targetAccountCombo.setVisible(false);
        if (accountCombo.getItemCount() > 0) accountCombo.setSelectedIndex(0);
    }

    public void refreshAccountCombo() 
    {
        loadAccountCombo();
    }
}