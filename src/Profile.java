// change in 68,89

import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Profile extends JFrame {
    String naya = "";
    Profile(String username) {
        Font f = new Font("Futura", Font.BOLD, 35);
        Font f2 = new Font("Calibri", Font.PLAIN, 20);

        JPanel backgroundPanel = new JPanel() {
            private Image bg = new ImageIcon(getClass().getResource("profilesettings.jpg")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        JLabel title = new JLabel("Profile Settings", JLabel.CENTER);
        title.setFont(f);
        JLabel l1 = new JLabel("Select Field to Update:");
        JComboBox<String> box = new RoundComboBox<>(new String[]{"Username", "Password", "Phone", "Email"});
        JLabel l2 = new JLabel("Enter New Value:");
        JTextField t1 = new RoundTextField(15);
        JButton b1 = new RoundButton("Update");
        JButton b2 = new RoundButton("Back");

        l1.setFont(f2);
        box.setFont(f2);
        l2.setFont(f2);
        t1.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);

        title.setBounds(250, 20, 300, 40);
        l1.setBounds(200, 100, 200, 30);
        box.setBounds(400, 100, 200, 30);
        l2.setBounds(200, 160, 200, 30);
        t1.setBounds(400, 160, 200, 30);
        b1.setBounds(250, 220, 120, 40);
        b2.setBounds(400, 220, 120, 40);

        backgroundPanel.add(title);
        backgroundPanel.add(l1);
        backgroundPanel.add(box);
        backgroundPanel.add(l2);
        backgroundPanel.add(t1);
        backgroundPanel.add(b1);
        backgroundPanel.add(b2);

        setContentPane(backgroundPanel);

        b2.addActionListener(
                a->{
                    new Home(username);
                    dispose();
                }
        );
        b1.addActionListener(
                a->{
                    String s1 = box.getSelectedItem().toString().toLowerCase();
                    String s2 = t1.getText();


                    if(s2.isEmpty()){
                        naya =s2;


                    }
//------------------------------------------------------------------------------------------------------------------------------------
                    String url = "jdbc:mysql://localhost:3306/name"; // write name of database
                    try (Connection con = DriverManager.getConnection(url, "root", "0214")) // write your password inside ""
                    {
                        String sql = "update users set "+s1+"=? where username=?";
                        try(PreparedStatement pst = con.prepareStatement(sql)){
                            pst.setString(1,s2);
                            pst.setString(2,username);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null,"Successfully Updated");
                            t1.setText("");
                        }

                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(null,e.getMessage());
                        return;
                    }
                    if(s1.equals("username")){
                        dispose();
                        new Profile(s2);
//------------------------------------------------------------------------------------------------------------------------------------

                        try (Connection con = DriverManager.getConnection(url, "root", "0214")) // write your password inside ""
                        {
                            String sql = "update transactions set username=? where username=?";
                            try(PreparedStatement pst = con.prepareStatement(sql)){
                                pst.setString(1,s2);
                                pst.setString(2,username);
                                pst.executeUpdate();

                            }

                        }
                        catch (Exception e){
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
                    }
                }
        );


        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Profile Settings");
    }

    public static void main(String[] args) {
        new Profile("Mayur6969");
    }
}