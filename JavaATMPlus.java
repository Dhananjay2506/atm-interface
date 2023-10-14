import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JavaATMPlus extends JFrame {
    private double balance = 1000.00;
    private List<String> transactionHistory = new ArrayList<>();
    private JTextArea historyTextArea;

    public JavaATMPlus() {

        
        setTitle("JAVA ATM PLUS");
        setSize(400, 400); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLUE); 
        JLabel titleLabel = new JLabel("JAVA ATM PLUS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE); 
        titlePanel.add(titleLabel);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2)); 
        panel.setVisible(true);
    
        


        JLabel balanceLabel = new JLabel("Balance: $");
        JTextField balanceField = new JTextField(String.valueOf(balance));
        balanceField.setEditable(false);

        JLabel amountLabel = new JLabel("Enter Amount: ");
        JTextField amountField = new JTextField();
        amountField.setSize(6,3);
    

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton transferButton = new JButton("Transfer");
        JButton historyButton = new JButton("Transaction History");
        JButton exitButton = new JButton("Exit");

        historyTextArea = new JTextArea(10, 30);
        historyTextArea.setEditable(false);

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0 || amount > balance) {
                    JOptionPane.showMessageDialog(null, "Invalid withdrawal amount!");
                } else {
                    balance -= amount;
                    updateBalance(balanceField);
                    transactionHistory.add("Withdraw: $" + amount);
                    updateHistory();
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid deposit amount!");
                } else {
                    balance += amount;
                    updateBalance(balanceField);
                    transactionHistory.add("Deposit: $" + amount);
                    updateHistory();
                }
            }
        });

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0 || amount > balance) {
                    JOptionPane.showMessageDialog(null, "Invalid transfer amount!");
                } else {
                    String recipientAccount = JOptionPane.showInputDialog("Enter recipient's account number:");
                    if (recipientAccount != null && !recipientAccount.isEmpty()) {
                        
                        balance -= amount;
                        transactionHistory.add("Transfer to " + recipientAccount + ": $" + amount);
                        updateBalance(balanceField);
                        updateHistory();
                    } else {
                        JOptionPane.showMessageDialog(null, "Recipient's account number cannot be empty!");
                    }
                }
            }
        });

        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayTransactionHistory();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(balanceLabel);
        panel.add(balanceField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(transferButton);
        panel.add(historyButton);
        panel.add(exitButton);

    
         add(titlePanel, BorderLayout.NORTH);
         add(panel, BorderLayout.CENTER);
         
    }

    private void updateBalance(JTextField balanceField) {
        balanceField.setText(String.valueOf(balance));
    }

    private void updateHistory() {
        historyTextArea.setText("");
        for (String transaction : transactionHistory) {
            historyTextArea.append(transaction + "\n");
        }
    }

    private void displayTransactionHistory() {
        updateHistory();
        JOptionPane.showMessageDialog(this, new JScrollPane(historyTextArea), "Transaction History", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JavaATMPlus().setVisible(true);
            }
        });
    }
}
