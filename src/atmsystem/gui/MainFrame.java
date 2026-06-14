package atmsystem.gui;

import atmsystem.model.Card;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame 
{
    private Card loggedInCard;
    private JPanel contentPanel;
    private CustomerPanel customerPanel;
    private AccountPanel accountPanel;
    private CardPanel cardPanel;
    private TransactionPanel transactionPanel;

    public MainFrame(Card card) 
    {
        this.loggedInCard = card;

        setTitle("ATM System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create panels
        customerPanel = new CustomerPanel();
        accountPanel = new AccountPanel();
        cardPanel = new CardPanel();
        transactionPanel = new TransactionPanel(loggedInCard);

        // Top bar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(34, 85, 34));

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(34, 85, 34));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Verdana", Font.BOLD, 15));
        logoutButton.setPreferredSize(new Dimension(200, 60));
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(e -> 
        {
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        // Tab buttons
        JPanel tabButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabButtonsPanel.setBackground(new Color(34, 85, 34));

        String[] tabNames = {"Customers", "Accounts", "Cards", "Transactions"};
        JButton[] tabButtons = new JButton[tabNames.length];

        for (int i = 0; i < tabNames.length; i++) 
        {
            final int index = i;
            tabButtons[i] = new JButton(tabNames[i]);
            tabButtons[i].setBackground(new Color(34, 85, 34));
            tabButtons[i].setForeground(Color.WHITE);
            tabButtons[i].setFont(new Font("Verdana", Font.BOLD, 15));
            tabButtons[i].setPreferredSize(new Dimension(200, 60));
            tabButtons[i].setBorderPainted(false);
            tabButtons[i].setFocusPainted(false);
            tabButtons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            tabButtons[i].addActionListener(e -> showPanel(index));
            tabButtonsPanel.add(tabButtons[i]);
        }

        topBar.add(tabButtonsPanel, BorderLayout.WEST);
        topBar.add(logoutButton, BorderLayout.EAST);

        // Content panel using CardLayout
        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(customerPanel, "Customers");
        contentPanel.add(accountPanel, "Accounts");
        contentPanel.add(cardPanel, "Cards");
        contentPanel.add(transactionPanel, "Transactions");

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topBar, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void showPanel(int index) 
    {
        String[] names = {"Customers", "Accounts", "Cards", "Transactions"};
        if (index == 1) accountPanel.refreshCustomerCombo();
        if (index == 2) cardPanel.refreshAccountCombo();
        if (index == 3) transactionPanel.refreshAccountCombo();
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, names[index]);
    }
}