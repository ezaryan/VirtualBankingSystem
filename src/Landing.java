//No Change

import javax.swing.*;
import java.awt.*;

class Landing extends JFrame {
    Landing() {
        // Custom JPanel for background image
        JPanel backgroundPanel = new JPanel() {
            private Image bg = new ImageIcon(getClass().getResource("landing.jpg")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        Font f = new Font("futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel l1 = new JLabel("Virtual Banking System", JLabel.CENTER );
        l1.setForeground(Color.WHITE);
        JButton b1 = new RoundButton("Admin");
        JButton b2 = new RoundButton("Existing Customer");
        JButton b3 = new RoundButton("New Customer");

        l1.setFont(f);
        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);

        l1.setBounds(150, 50, 500, 50);
        b1.setBounds(300, 150, 200, 50);
        b2.setBounds(300, 230, 200, 50);
        b3.setBounds(300, 310, 200, 50);

        backgroundPanel.add(l1);
        backgroundPanel.add(b1);
        backgroundPanel.add(b2);
        backgroundPanel.add(b3);

        b1.addActionListener(
                a->{
                    new Alogin();
                    dispose();
                }
        );

        b2.addActionListener(
                a->{
                    new Elogin();
                    dispose();
                }
        );
        b3.addActionListener(
                a->{
                    new Nlogin();
                    dispose();
                }
        );

        setContentPane(backgroundPanel);
        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Landing Page");
    }

    public static void main(String[] args) {
        new Landing();
    }
}