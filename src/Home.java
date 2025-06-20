// change in 93

import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Home extends JFrame {
    Home(String username) {
        JPanel backgroundPanel = new JPanel() {
            private Image bg = new ImageIcon(getClass().getResource("homepage.jpg")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        double balance = 0.0;
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel title = new JLabel("Welcome " + username, JLabel.CENTER);
        title.setForeground(Color.WHITE);
        JLabel balanceLabel = new JLabel("Balance: ₹0.00", JLabel.CENTER);
        balanceLabel.setForeground(Color.WHITE);
        JButton b1 = new RoundButton("Deposit");
        JButton b2 = new RoundButton("Withdraw");
        JButton b3 = new RoundButton("Profile Settings");
        JButton b4 = new RoundButton("Transfer");
        JButton b5 = new RoundButton("Passbook");
        JButton b6 = new RoundButton("Logout");

        title.setFont(f);
        balanceLabel.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);
        b4.setFont(f2);
        b5.setFont(f2);
        b6.setFont(f2);

        JPanel c = (JPanel) getContentPane();
        c.setLayout(null);

        title.setBounds(100, 30, 600, 50);
        balanceLabel.setBounds(100, 100, 600, 30);

        b1.setBounds(100, 150, 200, 40);
        b2.setBounds(400, 150, 200, 40);

        b3.setBounds(100, 220, 200, 40);
        b4.setBounds(400, 220, 200, 40);

        b5.setBounds(100, 290, 200, 40);
        b6.setBounds(400, 290, 200, 40);

        backgroundPanel.add(title);
        backgroundPanel.add(balanceLabel);
        backgroundPanel.add(b1);
        backgroundPanel.add(b2);
        backgroundPanel.add(b3);
        backgroundPanel.add(b4);
        backgroundPanel.add(b5);
        backgroundPanel.add(b6);

        setContentPane(backgroundPanel);
        b4.addActionListener(
                a->{
                    new Transfer(username);
                    dispose();
                }
        );
        b5.addActionListener(
                a->{
                    new Passbook(username);
                    dispose();
                }
        );
        b3.addActionListener(
                a->
                {
                    new Profile(username);
                    dispose();
                }
        );

        b1.addActionListener(
                a -> {
                    new Deposit(username);
                    dispose();
                }
        );
        b6.addActionListener(
                a->
                {
                    new Landing();
                    dispose();
                }
        );
        b2.addActionListener(
                a->{
                    new Withdraw(username);
                    dispose();
                }
        );
//------------------------------------------------------------------------------------------------------------------------------------
        String url = "jdbc:mysql://localhost:3306/name"; // write name of database
        try (Connection con = DriverManager.getConnection(url, "root", "0214")) // write your password inside ""
        {
            String sql = "select balance from users where username=?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, username);

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    balance = rs.getDouble("balance");
                }
                balanceLabel.setText("Balance: ₹" + balance);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Home");
    }

    public static void main(String[] args) {
        new Home("Mayur6969");
    }
}