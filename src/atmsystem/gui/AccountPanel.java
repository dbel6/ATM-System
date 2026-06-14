package atmsystem.gui;

import atmsystem.dao.AccountDAO;
import atmsystem.dao.CustomerDAO;
import atmsystem.model.Account;
import atmsystem.model.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AccountPanel extends JPanel 
{
    private AccountDAO accountDAO = new AccountDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField balanceField;
    private JComboBox<String> typeCombo;
    private JComboBox<String> customerCombo;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private List<Customer> customerList;

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

    public AccountPanel() 
    {
        setLayout(new BorderLayout());

        String[] columns = {"Account ID", "Customer ID", "Type", "Balance"};
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
        JLabel customerLabel = new JLabel("Customer:");
        customerLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        customerLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(customerLabel, gbc);
        customerCombo = new JComboBox<>();
        customerCombo.setFont(new Font("Verdana", Font.PLAIN, 18));
        customerCombo.setPreferredSize(new Dimension(0, 60));
        gbc.gridx = 1;
        formPanel.add(customerCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        typeLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(typeLabel, gbc);
        typeCombo = new JComboBox<>(new String[]{"Checking", "Saving"});
        typeCombo.setFont(new Font("Verdana", Font.PLAIN, 18));
        typeCombo.setPreferredSize(new Dimension(0, 60));
        gbc.gridx = 1;
        formPanel.add(typeCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel balanceLabel = new JLabel("Balance:");
        balanceLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        balanceLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(balanceLabel, gbc);
        balanceField = createStyledField();
        gbc.gridx = 1;
        formPanel.add(balanceField, gbc);

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

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        loadCustomerCombo();
        loadAccounts();

        table.getSelectionModel().addListSelectionListener(e -> 
        {
            int row = table.getSelectedRow();
            if (row != -1) 
            {
                int customerID = (int) tableModel.getValueAt(row, 1);
                for (int i = 0; i < customerList.size(); i++) 
                {
                    if (customerList.get(i).getCustomerID() == customerID) 
                    {
                        customerCombo.setSelectedIndex(i);
                        break;
                    }
                }
                typeCombo.setSelectedItem(tableModel.getValueAt(row, 2));
                balanceField.setText(String.valueOf(tableModel.getValueAt(row, 3)));
            }
        });

        addButton.addActionListener(e -> addAccount());
        updateButton.addActionListener(e -> updateAccount());
        deleteButton.addActionListener(e -> deleteAccount());
        clearButton.addActionListener(e -> clearFields());
    }

    private void loadCustomerCombo() 
    {
        customerList = customerDAO.getAllCustomers();
        customerCombo.removeAllItems();
        for (Customer c : customerList) 
        {
            customerCombo.addItem(c.getCustomerID() + " - " + c.getFirstname() + " " + c.getLastname());
        }
    }

    private void loadAccounts() 
    {
        tableModel.setRowCount(0);
        List<Account> accounts = accountDAO.getAllAccounts();
        for (Account a : accounts) 
        {
            tableModel.addRow(new Object[]{
                a.getAccountID(), a.getCustomerID(), a.getType(), a.getBalance()
            });
        }
    }

    private void addAccount() 
    {
        if (balanceField.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Balance is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (customerCombo.getItemCount() == 0) 
        {
            JOptionPane.showMessageDialog(this, "No customers available. Please add a customer first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try 
        {
            double balance = Double.parseDouble(balanceField.getText().trim());

            if (balance < 0) 
            {
                JOptionPane.showMessageDialog(this, "Balance cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (balance > 999999) 
            {
                JOptionPane.showMessageDialog(this, "Balance cannot exceed 999,999.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int customerID = customerList.get(customerCombo.getSelectedIndex()).getCustomerID();
            String type = (String) typeCombo.getSelectedItem();
            Account account = new Account(0, customerID, type, balance);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this account?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            accountDAO.addAccount(account);
            loadAccounts();
            clearFields();
            JOptionPane.showMessageDialog(this, "Account added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this, "Balance must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateAccount()
    {
        int row = table.getSelectedRow();
        if (row == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select an account to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (balanceField.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Balance is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try 
        {
            double balance = Double.parseDouble(balanceField.getText().trim());

            if (balance < 0) 
            {
                JOptionPane.showMessageDialog(this, "Balance cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (balance > 999999) 
            {
                JOptionPane.showMessageDialog(this, "Balance cannot exceed 999,999.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = (int) tableModel.getValueAt(row, 0);
            int customerID = customerList.get(customerCombo.getSelectedIndex()).getCustomerID();
            String type = (String) typeCombo.getSelectedItem();
            Account account = new Account(id, customerID, type, balance);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this account?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            accountDAO.updateAccount(account);
            loadAccounts();
            clearFields();
            JOptionPane.showMessageDialog(this, "Account updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this, "Balance must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAccount() 
    {
        int row = table.getSelectedRow();
        if (row == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select an account to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this account?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) 
        {
            accountDAO.deleteAccount(id);
            loadAccounts();
            clearFields();
            JOptionPane.showMessageDialog(this, "Account deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearFields() 
    {
        balanceField.setText("");
        table.clearSelection();
        if (customerCombo.getItemCount() > 0) customerCombo.setSelectedIndex(0);
        typeCombo.setSelectedIndex(0);
    }

    public void refreshCustomerCombo() 
    {
        loadCustomerCombo();
        loadAccounts();
    }
}