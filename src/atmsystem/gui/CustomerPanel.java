package atmsystem.gui;

import atmsystem.dao.CustomerDAO;
import atmsystem.model.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerPanel extends JPanel 
{
    private CustomerDAO customerDAO = new CustomerDAO();
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField firstnameField, lastnameField, addressField, phoneField, emailField;
    private JButton addButton, updateButton, deleteButton, clearButton;

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

    public CustomerPanel() 
    {
        setLayout(new BorderLayout());

        String[] columns = {"ID", "First Name", "Last Name", "Address", "Phone", "Email"};
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
        JLabel firstnameLabel = new JLabel("First Name:");
        firstnameLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        firstnameLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(firstnameLabel, gbc);
        firstnameField = createStyledField();
        gbc.gridx = 1;
        formPanel.add(firstnameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lastnameLabel = new JLabel("Last Name:");
        lastnameLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        lastnameLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(lastnameLabel, gbc);
        lastnameField = createStyledField();
        gbc.gridx = 1;
        formPanel.add(lastnameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        addressLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(addressLabel, gbc);
        addressField = createStyledField();
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        phoneLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(phoneLabel, gbc);
        phoneField = createStyledField();
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        emailLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(emailLabel, gbc);
        emailField = createStyledField();
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

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

        loadCustomers();

        table.getSelectionModel().addListSelectionListener(e -> 
        {
            int row = table.getSelectedRow();
            if (row != -1) 
            {
                firstnameField.setText((String) tableModel.getValueAt(row, 1));
                lastnameField.setText((String) tableModel.getValueAt(row, 2));
                addressField.setText((String) tableModel.getValueAt(row, 3));
                phoneField.setText((String) tableModel.getValueAt(row, 4));
                emailField.setText((String) tableModel.getValueAt(row, 5));
            }
        });

        addButton.addActionListener(e -> addCustomer());
        updateButton.addActionListener(e -> updateCustomer());
        deleteButton.addActionListener(e -> deleteCustomer());
        clearButton.addActionListener(e -> clearFields());
    }

    private void loadCustomers() 
    {
        tableModel.setRowCount(0);
        List<Customer> customers = customerDAO.getAllCustomers();
        for (Customer c : customers) 
        {
            tableModel.addRow(new Object[]{
                c.getCustomerID(), c.getFirstname(), c.getLastname(),
                c.getAddress(), c.getPhone(), c.getEmail()
            });
        }
    }

    private void addCustomer() 
    {
        String firstname = firstnameField.getText().trim();
        String lastname = lastnameField.getText().trim();
        String address = addressField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        if (firstname.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "First name is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (firstname.length() < 2 || firstname.length() > 15) 
        {
            JOptionPane.showMessageDialog(this, "First name must be between 2 and 15 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!firstname.matches("[a-zA-Z]+")) 
        {
            JOptionPane.showMessageDialog(this, "First name must contain letters only.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (lastname.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Last name is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (lastname.length() < 2 || lastname.length() > 15) 
        {
            JOptionPane.showMessageDialog(this, "Last name must be between 2 and 15 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!lastname.matches("[a-zA-Z]+")) 
        {
            JOptionPane.showMessageDialog(this, "Last name must contain letters only.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (address.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Address is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (address.length() < 10 || address.length() > 70) 
        {
            JOptionPane.showMessageDialog(this, "Address must be between 10 and 70 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (phone.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Phone is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!phone.matches("\\d{7,15}")) 
        {
            JOptionPane.showMessageDialog(this, "Phone must contain between 7 and 15 digits only.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (email.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Email is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (email.length() < 5 || email.length() > 30) 
        {
            JOptionPane.showMessageDialog(this, "Email must be between 5 and 30 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) 
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Customer customer = new Customer(0, firstname, lastname, address, phone, email);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this customer?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        customerDAO.addCustomer(customer);
        loadCustomers();
        clearFields();
        JOptionPane.showMessageDialog(this, "Customer added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateCustomer() 
    {
        int row = table.getSelectedRow();
        if (row == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a customer to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String firstname = firstnameField.getText().trim();
        String lastname = lastnameField.getText().trim();
        String address = addressField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        if (firstname.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "First name is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (firstname.length() < 2 || firstname.length() > 15) 
        {
            JOptionPane.showMessageDialog(this, "First name must be between 2 and 15 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!firstname.matches("[a-zA-Z]+")) 
        {
            JOptionPane.showMessageDialog(this, "First name must contain letters only.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (lastname.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Last name is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (lastname.length() < 2 || lastname.length() > 15) 
        {
            JOptionPane.showMessageDialog(this, "Last name must be between 2 and 15 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!lastname.matches("[a-zA-Z]+")) 
        {
            JOptionPane.showMessageDialog(this, "Last name must contain letters only.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (address.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Address is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (address.length() < 10 || address.length() > 70) 
        {
            JOptionPane.showMessageDialog(this, "Address must be between 10 and 70 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (phone.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Phone is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!phone.matches("\\d{7,15}")) 
        {
            JOptionPane.showMessageDialog(this, "Phone must contain between 7 and 15 digits only.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (email.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Email is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (email.length() < 5 || email.length() > 30) 
        {
            JOptionPane.showMessageDialog(this, "Email must be between 5 and 30 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) 
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(row, 0);
        Customer customer = new Customer(id, firstname, lastname, address, phone, email);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this customer?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        customerDAO.updateCustomer(customer);
        loadCustomers();
        clearFields();
        JOptionPane.showMessageDialog(this, "Customer updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteCustomer() 
    {
        int row = table.getSelectedRow();
        if (row == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a customer to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this customer?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) 
        {
            customerDAO.deleteCustomer(id);
            loadCustomers();
            clearFields();
            JOptionPane.showMessageDialog(this, "Customer deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearFields() 
    {
        firstnameField.setText("");
        lastnameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        emailField.setText("");
        table.clearSelection();
    }
}