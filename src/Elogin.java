// change in 52

import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Elogin extends JFrame {
    Elogin() {
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel title = new JLabel("Login", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        JLabel l1 = new JLabel("Enter Username");
        l1.setForeground(Color.WHITE);
        JTextField t1 = new RoundTextField(10);
        JLabel l2 = new JLabel("Enter Password");
        l2.setForeground(Color.WHITE);
        JPasswordField t2 = new RoundPasswordField(10);
        JButton b1 = new RoundButton("Submit");
        JButton b2 = new RoundButton("Back");

        title.setFont(f);
        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        t2.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);



        Container c = getContentPane();
        c.setLayout(null);

        title.setBounds(250, 30, 300, 50);
        l1.setBounds(250, 100, 300, 30);
        t1.setBounds(250, 140, 300, 30);
        l2.setBounds(250, 200, 300, 30);
        t2.setBounds(250, 240, 300, 30);
        b1.setBounds(300, 300, 200, 40);
        b2.setBounds(300, 360, 200, 40);

        JPanel backgroundPanel = new JPanel() {
            private Image bg = new ImageIcon(getClass().getResource("elogin1.jpg")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        backgroundPanel.add(title);
        backgroundPanel.add(l1);
        backgroundPanel.add(t1);
        backgroundPanel.add(l2);
        backgroundPanel.add(t2);
        backgroundPanel.add(b1);
        backgroundPanel.add(b2);

        setContentPane(backgroundPanel);

        b1.addActionListener(
                a->{
// -------------------------------------------------------------------------------------
                    String url = "jdbc:mysql://localhost:3306/name"; // write name of database
                    try (Connection con = DriverManager.getConnection(url, "root", "0214")) // write your password inside ""
                    {
                        String sql = "select * from users where username=? and password=?";
                        try(PreparedStatement pst = con.prepareStatement(sql)){
                            pst.setString(1,t1.getText());
                            String s1 = new String(t2.getPassword());
                            pst.setString(2,s1);
//                            pst.setString(2,t2.getText());

                            ResultSet rs = pst.executeQuery();
                            if (rs.next()){
                                JOptionPane.showMessageDialog(null,"successfull");
                                new Home(t1.getText());
                                dispose();
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"User doest exist");
                            }
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
        setTitle("Login");
    }

    public static void main(String[] args) {
        new Elogin();
    }
}