package atmsystem.gui;

import atmsystem.dao.CardDAO;
import atmsystem.model.Card;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame 
{
    private JTextField cardField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JButton clearButton;

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
        field.setPreferredSize(new Dimension(400, 60));
        field.setFont(new Font("Verdana", Font.PLAIN, 20));
        return field;
    }

    private JPasswordField createStyledPasswordField() 
    {
        JPasswordField field = new JPasswordField(25) 
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
        field.setPreferredSize(new Dimension(400, 60));
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

    public LoginFrame() 
    {
        setTitle("ATM System - Login");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(230, 230, 230));

        // Top bar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(34, 85, 34));
        topBar.setPreferredSize(new Dimension(0, 60));
        JLabel titleLabel = new JLabel("ATM System");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        topBar.add(titleLabel, BorderLayout.WEST);
        mainPanel.add(topBar, BorderLayout.NORTH);

        // Center form panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(230, 230, 230));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(230, 230, 230));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;

        // Login title
        JLabel loginTitle = new JLabel("Welcome to the ATM System");
        loginTitle.setFont(new Font("Verdana", Font.BOLD, 36));
        loginTitle.setForeground(new Color(34, 85, 34));
        loginTitle.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(loginTitle, gbc);

        // Subtitle
        JLabel subTitle = new JLabel("Please enter your card number and PIN to continue");
        subTitle.setFont(new Font("Verdana", Font.PLAIN, 16));
        subTitle.setForeground(new Color(100, 100, 100));
        subTitle.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(subTitle, gbc);

        // Card number
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel cardLabel = new JLabel("Card Number:");
        cardLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        cardLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(cardLabel, gbc);
        cardField = createStyledField();
        gbc.gridx = 1; gbc.gridy = 2;
        formPanel.add(cardField, gbc);

        // PIN
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        pinLabel.setForeground(new Color(34, 85, 34));
        formPanel.add(pinLabel, gbc);
        pinField = createStyledPasswordField();
        gbc.gridx = 1; gbc.gridy = 3;
        formPanel.add(pinField, gbc);

        // Buttons
        loginButton = createStyledButton("Login");
        clearButton = createStyledButton("Clear");
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBackground(new Color(230, 230, 230));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(loginButton);
        buttonPanel.add(clearButton);
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        centerPanel.add(formPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);

        loginButton.addActionListener(e -> handleLogin());
        clearButton.addActionListener(e -> 
        {
            cardField.setText("");
            pinField.setText("");
        });
    }

    private void handleLogin() 
    {
        String cardInput = cardField.getText().trim();
        String pinInput = new String(pinField.getPassword()).trim();

        if (cardInput.isEmpty() || pinInput.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Please enter both card number and PIN.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try 
        {
            long cardNumber = Long.parseLong(cardInput);
            CardDAO cardDAO = new CardDAO();
            Card card = cardDAO.login(cardNumber, pinInput);

            if (card != null) 
            {
                new MainFrame(card).setVisible(true);
                this.dispose();
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Invalid card number or PIN.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this, "Card number must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}