// Change in 55,79,105

import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Deposit extends JFrame
{
    Deposit(String username)
    {

        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JPanel backgroundPanel = new JPanel() {
            private Image bg = new ImageIcon(getClass().getResource("deposit.jpg")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        JLabel title = new JLabel("Deposit Money", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        JLabel label = new JLabel("Enter Amount:");
        label.setForeground(Color.WHITE);
        JTextField t1 = new RoundTextField(10);
        JButton b1 = new RoundButton("Deposit");
        JButton b2 = new RoundButton("Back");

        title.setFont(f);
        label.setFont(f2);
        t1.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);

        title.setBounds(200, 30, 400, 50);
        label.setBounds(250, 120, 300, 30);
        t1.setBounds(250, 160, 300, 30);
        b1.setBounds(300, 220, 200, 40);
        b2.setBounds(300, 280, 200, 40);

        backgroundPanel.add(title);
        backgroundPanel.add(label);
        backgroundPanel.add(t1);
        backgroundPanel.add(b1);
        backgroundPanel.add(b2);

        setContentPane(backgroundPanel);

        b2.addActionListener(
                a->
                {
                    new Home(username);
                    dispose();
                }
        );

        b1.addActionListener(
                a->{
                    double balance=0.0;
                    double total = 0.0;
                    double amount = 0.0;
//------------------------------------------------------------------------------------------------------------------------------------
                    String url = "jdbc:mysql://localhost:3306/name"; // write name of database
                    try (Connection con = DriverManager.getConnection(url, "root", "0214")) // write your password inside ""
                    {
                        String sql = "select balance from users where username=?";
                        try(PreparedStatement pst = con.prepareStatement(sql)){
                            pst.setString(1,username);
                            ResultSet rs = pst.executeQuery();
                            if(rs.next()){
                                balance = rs.getDouble("balance");
                            }
                        }
                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }
                    String s1 = t1.getText();
                    if(s1.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Enter value");
                    }
                    else {
                        amount = Double.parseDouble(s1);
                        total=amount + balance;
                    }
                    //------------------------------------------------------------------------------------------------------------------------------------

                    try (Connection con = DriverManager.getConnection(url, "root", "0214")) // write your password inside ""
                    {
                        String sql = "update users set balance=? where username=?";
                        try(PreparedStatement pst = con.prepareStatement(sql)){
                            pst.setDouble(1,total);
                            pst.setString(2,username);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null,"Successfully deposited");
                            t1.setText("");
                            updatePassbook(username,"Deposit",amount,balance+amount);
                        }

                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }

                }
        );
        b2.addActionListener(
                a->
                {
                    new Landing();
                    dispose();
                }
        );

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Deposit Money");
    }
    void updatePassbook(String username,String desc, double amount,double total){
//------------------------------------------------------------------------------------------------------------------------------------
        String url = "jdbc:mysql://localhost:3306/name"; // write name of database
        try (Connection con = DriverManager.getConnection(url, "root", "0214")) // write your password inside ""
        {
            String sql = "insert into transactions(username,description,amount,balance) value(?,?,?,?)";
            try(PreparedStatement pst = con.prepareStatement(sql)){
                pst.setString(1,username);
                pst.setString(2,desc);
                pst.setDouble(3,amount);
                pst.setDouble(4,total);
                pst.executeUpdate();
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    public static void main(String[] args) {
        new Deposit("Mayur6969");
    }
}