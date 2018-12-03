import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author group 66
 * This is a pre interface
 */
public class UserBillShowUI extends JFrame implements ActionListener {
    JButton jb3 = null;
    JPanel jp0, jp1, jp2, jp3, jp4;
    JLabel j1, jlabel3, j4;
    int y, m, d, y1, m1, d1;
    JTextArea showText = new JTextArea(10, 22);
    JScrollPane scroller = new JScrollPane(showText);
    String id;

    public UserBillShowUI(String id, int y, int m, int d, int y1, int m1, int d1) {
        showText.setLineWrap(true);
        showText.setEditable(false);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.id = id;
        this.y = y;
        this.m = m;
        this.d = d;
        this.y1 = y1;
        this.m1 = m1;
        this.d1 = d1;
        jb3 = new JButton("Return");
        j4 = new JLabel("");
        //j5 = new JLabel();
        jb3.addActionListener(this);
        jp0 = new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jlabel3 = new JLabel();
        jlabel3.setText("Your total bill is");
        jlabel3.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        FileFunction res = new FileFunction();
        this.setSize(400, 400);
        j1 = new JLabel();
        j1.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        jp0.add(jlabel3);
        jp2.add(j1);
        jp4.add(jb3);
        //jp3.add(j5);
        jp3.add(scroller);
        ReadBill read = new ReadBill();
        showText.append(read.readBill(id, String.valueOf(y), String.valueOf(m), String.valueOf(y1), String.valueOf(m1)));
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, jp0);
        this.add(BorderLayout.CENTER, jp3);
        //this.add(jp1);
        //this.add(jp2);
        this.add(BorderLayout.SOUTH, jp4);


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
            new UserUI();
            this.dispose();
        }
    }

}