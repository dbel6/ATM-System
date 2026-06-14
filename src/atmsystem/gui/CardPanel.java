package atmsystem.gui;

import atmsystem.dao.CardDAO;
import atmsystem.dao.AccountDAO;
import atmsystem.model.Card;
import atmsystem.model.Account;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CardPanel extends JPanel 
{
    private CardDAO cardDAO = new CardDAO();
    private AccountDAO accountDAO = new AccountDAO();
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField cardNumberField, pinField, cvcField;
    private JComboBox<String> accountCombo;
    private JComboBox<Integer> dayCombo, monthCombo, yearCombo;
    private JButton addButton, updateButton, deleteButton, clearButton;
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

    public CardPanel() 
    {
        setLayout(new BorderLayout());

        String[] columns = {"Card Number", "Account ID", "PIN", "Expiration Date", "CVC"};
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
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        cardNumberLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(cardNumberLabel, gbc);
        cardNumberField = createStyledField();
        gbc.gridx = 1;
        formPanel.add(cardNumberField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel accountLabel = new JLabel("Account:");
        accountLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        accountLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(accountLabel, gbc);
        accountCombo = new JComboBox<>();
        accountCombo.setFont(new Font("Verdana", Font.PLAIN, 18));
        accountCombo.setPreferredSize(new Dimension(0, 60));
        gbc.gridx = 1;
        formPanel.add(accountCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        pinLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(pinLabel, gbc);
        pinField = createStyledField();
        gbc.gridx = 1;
        formPanel.add(pinField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JLabel expirationLabel = new JLabel("Expiration Date:");
        expirationLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        expirationLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(expirationLabel, gbc);

        // Date picker panel
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        datePanel.setBackground(new Color(230, 230, 230));
        dayCombo = new JComboBox<>();
        for (int i = 1; i <= 31; i++) dayCombo.addItem(i);
        dayCombo.setFont(new Font("Verdana", Font.PLAIN, 18));
        dayCombo.setPreferredSize(new Dimension(80, 60));
        monthCombo = new JComboBox<>();
        for (int i = 1; i <= 12; i++) monthCombo.addItem(i);
        monthCombo.setFont(new Font("Verdana", Font.PLAIN, 18));
        monthCombo.setPreferredSize(new Dimension(80, 60));
        yearCombo = new JComboBox<>();
        for (int i = 2025; i <= 2040; i++) yearCombo.addItem(i);
        yearCombo.setFont(new Font("Verdana", Font.PLAIN, 18));
        yearCombo.setPreferredSize(new Dimension(120, 60));
        datePanel.add(dayCombo);
        datePanel.add(new JLabel("/ "));
        datePanel.add(monthCombo);
        datePanel.add(new JLabel("/ "));
        datePanel.add(yearCombo);
        gbc.gridx = 1;
        formPanel.add(datePanel, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        JLabel cvcLabel = new JLabel("CVC:");
        cvcLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        cvcLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(cvcLabel, gbc);
        cvcField = createStyledField();
        gbc.gridx = 1;
        formPanel.add(cvcField, gbc);

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

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        loadAccountCombo();
        loadCards();

        table.getSelectionModel().addListSelectionListener(e -> 
        {
            int row = table.getSelectedRow();
            if (row != -1) 
            {
                cardNumberField.setText(String.valueOf(tableModel.getValueAt(row, 0)));
                int accountID = (int) tableModel.getValueAt(row, 1);
                for (int i = 0; i < accountList.size(); i++) 
                {
                    if (accountList.get(i).getAccountID() == accountID) 
                    {
                        accountCombo.setSelectedIndex(i);
                        break;
                    }
                }
                pinField.setText((String) tableModel.getValueAt(row, 2));
                String date = (String) tableModel.getValueAt(row, 3);
                if (date != null && date.length() == 10) 
                {
                    yearCombo.setSelectedItem(Integer.parseInt(date.substring(0, 4)));
                    monthCombo.setSelectedItem(Integer.parseInt(date.substring(5, 7)));
                    dayCombo.setSelectedItem(Integer.parseInt(date.substring(8, 10)));
                }
                cvcField.setText(String.valueOf(tableModel.getValueAt(row, 4)));
            }
        });

        addButton.addActionListener(e -> addCard());
        updateButton.addActionListener(e -> updateCard());
        deleteButton.addActionListener(e -> deleteCard());
        clearButton.addActionListener(e -> clearFields());
    }

    private String getSelectedDate() 
    {
        int day = (int) dayCombo.getSelectedItem();
        int month = (int) monthCombo.getSelectedItem();
        int year = (int) yearCombo.getSelectedItem();
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    private void loadAccountCombo() 
    {
        accountList = accountDAO.getAllAccounts();
        accountCombo.removeAllItems();
        for (Account a : accountList) 
        {
            accountCombo.addItem(a.getAccountID() + " - " + a.getType());
        }
    }

    private void loadCards() 
    {
        tableModel.setRowCount(0);
        List<Card> cards = cardDAO.getAllCards();
        for (Card c : cards) 
        {
            tableModel.addRow(new Object[]{
                c.getCardNumber(), c.getAccountID(), c.getPin(),
                c.getExpirationDate(), c.getCvc()
            });
        }
    }

    private void addCard() 
    {
        if (cardNumberField.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Card number is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!cardNumberField.getText().trim().matches("\\d{13,19}")) 
        {
            JOptionPane.showMessageDialog(this, "Card number must be between 13 and 19 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (pinField.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "PIN is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!pinField.getText().trim().matches("\\d{4}")) 
        {
            JOptionPane.showMessageDialog(this, "PIN must be exactly 4 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cvcField.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "CVC is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!cvcField.getText().trim().matches("\\d{3}")) 
        {
            JOptionPane.showMessageDialog(this, "CVC must be exactly 3 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (accountCombo.getItemCount() == 0) 
        {
            JOptionPane.showMessageDialog(this, "No accounts available. Please add an account first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try 
        {
            long cardNumber = Long.parseLong(cardNumberField.getText().trim());
            int cvc = Integer.parseInt(cvcField.getText().trim());
            int accountID = accountList.get(accountCombo.getSelectedIndex()).getAccountID();
            String expirationDate = getSelectedDate();
            Card card = new Card(cardNumber, accountID, pinField.getText().trim(), expirationDate, cvc);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this card?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            cardDAO.addCard(card);
            loadCards();
            clearFields();
            JOptionPane.showMessageDialog(this, "Card added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this, "Card number and CVC must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCard() 
    {
        int row = table.getSelectedRow();
        if (row == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a card to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cardNumberField.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Card number is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!cardNumberField.getText().trim().matches("\\d{13,19}")) 
        {
            JOptionPane.showMessageDialog(this, "Card number must be between 13 and 19 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (pinField.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "PIN is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!pinField.getText().trim().matches("\\d{4}")) 
        {
            JOptionPane.showMessageDialog(this, "PIN must be exactly 4 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cvcField.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "CVC is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!cvcField.getText().trim().matches("\\d{3}")) 
        {
            JOptionPane.showMessageDialog(this, "CVC must be exactly 3 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try 
        {
            long cardNumber = Long.parseLong(cardNumberField.getText().trim());
            int cvc = Integer.parseInt(cvcField.getText().trim());
            int accountID = accountList.get(accountCombo.getSelectedIndex()).getAccountID();
            String expirationDate = getSelectedDate();
            Card card = new Card(cardNumber, accountID, pinField.getText().trim(), expirationDate, cvc);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this card?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            cardDAO.updateCard(card);
            loadCards();
            clearFields();
            JOptionPane.showMessageDialog(this, "Card updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this, "Card number and CVC must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCard() 
    {
        int row = table.getSelectedRow();
        if (row == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a card to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        long cardNumber = Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0)));
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this card?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) 
        {
            cardDAO.deleteCard(cardNumber);
            loadCards();
            clearFields();
            JOptionPane.showMessageDialog(this, "Card deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearFields() 
    {
        cardNumberField.setText("");
        pinField.setText("");
        cvcField.setText("");
        table.clearSelection();
        if (accountCombo.getItemCount() > 0) accountCombo.setSelectedIndex(0);
        dayCombo.setSelectedIndex(0);
        monthCombo.setSelectedIndex(0);
        yearCombo.setSelectedIndex(0);
    }

    public void refreshAccountCombo() 
    {
        loadAccountCombo();
    }
}