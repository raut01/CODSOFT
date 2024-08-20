package Taask2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI extends JFrame {
    private ATM atm;
    private JTextField inputField;
    private JTextArea outputArea;

    public ATMGUI(ATM atm) {
        this.atm = atm;
        initUI();
    }

    private void initUI() {
        // Frame settings
        setTitle("ATM Machine");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create components
        inputField = new JTextField(10);
        inputField.setBounds(122, 6, 86, 20);
        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(10, 111, 101, 23);
        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 53, 69, 23);
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(191, 53, 79, 23);

        // Panel for input and buttons
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel label = new JLabel("Amount: ");
        label.setBounds(71, 9, 44, 14);
        panel.add(label);
        panel.add(inputField);
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(191, 111, 51, 23);
        panel.add(exitButton);
        
                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(checkBalanceButton);

        // Add components to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(scrollPane, BorderLayout.SOUTH);

        // Button actions
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText(atm.checkBalance());
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTransaction("deposit");
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTransaction("withdraw");
            }
        });
    }

    private void handleTransaction(String type) {
        try {
            double amount = Double.parseDouble(inputField.getText());
            String result = "";
            if (type.equals("deposit")) {
                result = atm.deposit(amount);
            } else if (type.equals("withdraw")) {
                result = atm.withdraw(amount);
            }
            outputArea.setText(result);
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input. Please enter a numerical value.");
        }
    }

    public static void main(String[] args) {
        // Create a bank account with an initial balance of $1000
        BankAccount account = new BankAccount(1000.00);
        ATM atm = new ATM(account);

        // Create and display the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ATMGUI atmGui = new ATMGUI(atm);
                atmGui.setVisible(true);
            }
        });
    }
}
