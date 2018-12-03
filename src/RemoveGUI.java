import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author group 66
 * This is a removing user interface
 */
public class RemoveGUI extends JFrame implements ActionListener {
    JButton jb1, jb2, jb3 = null;
    JTextField jtf1 = null;//ID
    JPanel jp0, jp1, jp2, jp3, jp4;
    JLabel jlabel1, jlabel2, jlabel3, j4, j5;


    public RemoveGUI() {
        jb1 = new JButton("Remove");
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

        jlabel1 = new JLabel("     ID       :");
        jlabel3 = new JLabel("Tap in the ID and Remove");
        jlabel3.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        jtf1 = new JTextField(10);

        jp0.add(jlabel3);
        jp1.add(jlabel1);
        jp1.add(jtf1);
        jp2.add(j5);
        jp4.add(jb1);
        jp4.add(jb2);
        jp4.add(jb3);
        this.add(j4);
        this.add(jp0);
        this.add(jp3);
        this.add(jp1);
        this.add(jp2);
        this.add(jp4);

        this.setLayout(new GridLayout(6, 1));
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
            if (e.getSource() == jb1) {
                String id = jtf1.getText();
                File file = new File(id);
                if (!file.exists() || !file.isDirectory()) {
                    j5.setText("no user found");
                } else {
                    MotifyUser.delUserFolder(id);
                    MotifyUser.delAllUserFile(id);
                    MotifyUser.delUserAccount(id);
                    j5.setText("User has already been removed");
                    JOptionPane.showMessageDialog(null, "Successfully delete the user", "Successful", JOptionPane.PLAIN_MESSAGE);
                    new ProviderUI();
                    this.dispose();
                }
            }

        } else if (e.getSource() == jb2) {
            clearall();
        } else if (e.getSource() == jb3) {
            new ProviderUI();
            this.dispose();
        }
    }

    public void clearall() {
        jtf1.setText("");
    }
}

