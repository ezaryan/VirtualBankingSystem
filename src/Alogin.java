//No change in the file 

import javax.swing.*;
import java.awt.*;

class Alogin extends JFrame {
    Alogin() {
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel title = new JLabel("Admin Login", JLabel.CENTER);
        JLabel l1 = new JLabel("Enter Username");
        JTextField t1 = new RoundTextField(10);
        JLabel l2 = new JLabel("Enter Password");
        JPasswordField p1 = new RoundPasswordField(10);
        JButton b1 = new RoundButton("Submit");
        JButton b2 = new RoundButton("Back");

        title.setFont(f);
        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        p1.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);

        JPanel backgroundPanel = new JPanel() {
            private Image bg = new ImageIcon(getClass().getResource("alogin.jpg")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        title.setBounds(250, 30, 300, 50);
        l1.setBounds(250, 100, 300, 30);
        t1.setBounds(250, 140, 300, 30);
        l2.setBounds(250, 200, 300, 30);
        p1.setBounds(250, 240, 300, 30);
        b1.setBounds(300, 300, 200, 40);
        b2.setBounds(300, 360, 200, 40);

        backgroundPanel.add(title);
        backgroundPanel.add(l1);
        backgroundPanel.add(t1);
        backgroundPanel.add(l2);
        backgroundPanel.add(p1);
        backgroundPanel.add(b1);
        backgroundPanel.add(b2);

        setContentPane(backgroundPanel);

        b1.addActionListener(
                a->{
                    String s1 = new String(p1.getPassword());
                    if(t1.getText().equals("admin") && s1.equals("pass")){
                        new Adashboard();
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Incorrect");
                    }
                }
        );
        b2.addActionListener(
                a->{
                    new Landing();
                    dispose();
                }
        );

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Admin Login");
    }

    public static void main(String[] args) {
        new Alogin();
    }
}