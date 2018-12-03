import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author group 66
 * This is a sending bill interface
 */

public class CheckSendingUI extends JFrame implements ActionListener {
    JButton jb3 = null;
    JPanel jp0, jp1, jp2, jp3, jp4;
    JLabel j1, jlabel3, j4, j5;
    String id;

    public CheckSendingUI() {
        jb3 = new JButton("Return");
        j4 = new JLabel("");
        ReadBill read = new ReadBill();
        jb3.addActionListener(this);
        jp0 = new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jlabel3 = new JLabel();
        jlabel3.setText("Your bill has been sent!!!!!");
        jlabel3.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        FileFunction res = new FileFunction();
        j1 = new JLabel();
        j1.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        jp0.add(jlabel3);
        jp2.add(j1);
        jp4.add(jb3);
        this.add(j4);
        this.add(jp0);
        this.add(jp3);
        this.add(jp1);
        this.add(jp2);
        this.add(jp4);

        this.setLayout(new GridLayout(6, 1));
        this.setTitle("Smart Energy Management and Monitor system");
        //this.setSize(600, 800);
        //this.setLocation(400, 200);
        this.pack();
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
        if (e.getSource() == jb3) {
            this.dispose();
            ProviderUI frame1 = new ProviderUI();
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setVisible(true);
        }
    }

}