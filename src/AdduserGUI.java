import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author group 66
 * This is a adduser interface
 */
public class AdduserGUI extends JFrame implements ActionListener {
    JButton jb1, jb2, jb3, jb4 = null;
    JTextField jtf1, jtf2, jtf3 = null;
    JPanel jp0, jp1, jp2, jp3, jp4, jp5;
    JLabel jlabel1, jlabel2, jlabel3, j4, j5, j6;


    public AdduserGUI() {
        jb1 = new JButton("Adduser");
        jb2 = new JButton("Clear");
        jb3 = new JButton("Return");
        j4 = new JLabel("");
        j5 = new JLabel();
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jp0 = new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jlabel1 = new JLabel("     ID       :");
        jlabel2 = new JLabel("Password:");
        jlabel3 = new JLabel("Please enter your ID and Password");
        j6 = new JLabel("   Address:");
        jlabel3.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        jtf1 = new JTextField(10);
        jtf2 = new JTextField(10);
        jtf3 = new JTextField(10);
        jp0.add(jlabel3);
        jp1.add(jlabel1);
        jp1.add(jtf1);
        jp3.add(j5);
        jp2.add(jlabel2);
        jp2.add(jtf3);
        jp4.add(jb1);
        jp4.add(jb2);
        jp4.add(jb3);
        jp5.add(j6);
        jp5.add(jtf2);
        this.add(j4);
        this.add(jp0);
        this.add(jp3);
        this.add(jp1);
        this.add(jp2);
        this.add(jp5);
        this.add(jp4);
        this.setLayout(new GridLayout(0, 1));
        this.setTitle("Smart Energy Management and Monitor system");
        this.setSize(400, 300);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = this.getSize();
        int wei = (screenSize.width - size.width) / 2;
        int hei = (screenSize.height - size.height) / 2;
        this.setLocation(wei, hei);
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            String id = jtf1.getText();
            String pass = jtf3.getText();
            String email = jtf2.getText();
            if (id.length() > 8) {
                j5.setText("id too long");
                return;
            }
            if (pass.length() > 8) {
                j5.setText("password too long");
                return;
            }
            if (MotifyUser.isNumeric3(id)) {
                int a = 1;
            } else {
                j5.setText("You should input a number");
                return;
            }
            if (MotifyUser.isNumeric3(pass)) {
                int a = 1;
            } else {
                j5.setText("You should input a number");
                return;
            }
            if (id.length() <= 0 || id == null) {
                j5.setText("Please enter the id");

            } else if (pass.length() <= 0 || pass == null) {
                j5.setText("Please enter the password");

            } else if (id.length() > 8) {
                j5.setText("id too long");

            } else if (email.length() <= 0 || pass == null) {
                j5.setText("Please enter your email");
            } else if (!MotifyUser.checkEmail(email)) {
                j5.setText("Please enter your email correctly");
                return;
            } else {
                File file = new File(id);
                if (!file.exists() || !file.isDirectory()) {
                    MotifyUser add = new MotifyUser();
                    add.createUserDir(id, pass, email);
                    j5.setText("successful!!!");
                    JOptionPane.showMessageDialog(null, "Successfully add a user!", "Successful", JOptionPane.PLAIN_MESSAGE);
                    new ProviderUI();
                    this.dispose();
                } else {
                    j5.setText("User already exits!!!");
                }
            }
        } else if (e.getSource() == jb2) {
            clearall();
        } else if (e.getSource() == jb3) {
            this.dispose();
            ProviderUI frame1 = new ProviderUI();
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setVisible(true);
        }
    }


    public void clearall() {
        jtf1.setText("");
        jtf2.setText("");
        jtf3.setText("");
    }
}

