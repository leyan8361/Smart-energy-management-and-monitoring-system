import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Calendar;

/**
 * @author group 66
 * This is a user bill interface
 */

public class UserBillUI extends JFrame implements ActionListener {
    int y, mo, d, y2, mo2, d2;
    JButton jb1, jb2, jb3 = null;
    JTextField jtf1 = null;//ID
    JPanel jp0, jp1, jp3, jp4, contentPane, contentPane2;
    JLabel jlabel1, jlabel2, jlabel3, j4, j5, j6;


    public UserBillUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());
        final JComboBox year = new JComboBox();
        year.setModel(new DefaultComboBoxModel(getModel(2016, 2020)));
        contentPane.add(year);
        final JComboBox month = new JComboBox();
        month.setModel(new DefaultComboBoxModel(getModel(1, 12)));
        contentPane.add(month);
        final JComboBox day = new JComboBox();
        year.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setDay(year, month, day);
            }
        });
        month.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setDay(year, month, day);
            }
        });
        setDay(year, month, day);
        day.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                d = Integer.parseInt((String) day.getSelectedItem());
            }
        });
        j6 = new JLabel("   to   ");
        contentPane.add(j6);
        final JComboBox year2 = new JComboBox();
        year2.setModel(new DefaultComboBoxModel(getModel(2016, 2020)));
        contentPane.add(year2);
        final JComboBox month2 = new JComboBox();
        month2.setModel(new DefaultComboBoxModel(getModel(1, 12)));
        contentPane.add(month2);
        final JComboBox day2 = new JComboBox();
        year2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setDay2(year2, month2, day2);

            }
        });
        month2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setDay2(year2, month2, day2);
            }
        });
        day2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                d2 = Integer.parseInt((String) day2.getSelectedItem());
            }
        });
        setDay2(year2, month2, day2);
        jb1 = new JButton("Go");
        //jb2 = new JButton("Clear");
        jb3 = new JButton("Return");
        j4 = new JLabel("");
        jb1.addActionListener(this);
        //jb2.addActionListener(this);
        jb3.addActionListener(this);
        jp0 = new JPanel();
        jp1 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();

        //jlabel1 = new JLabel("     ID       :");
        jlabel3 = new JLabel("Choose the month you want to Check");
        jlabel2 = new JLabel("    Mon       :");
        j5 = new JLabel();
        jlabel3.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        //jtf1 = new JTextField(10);
        jp0.add(jlabel3);
        //jp1.add(jlabel1);
        //jp1.add(jtf1);
        jp3.add(j5);
        jp4.add(jb1);
        //jp4.add(jb2);
        jp4.add(jb3);
        this.add(j4);
        this.add(jp0);
        this.add(jp3);
        this.add(jp1);
        this.add(contentPane);
        this.add(jp4);
        this.setLayout(new GridLayout(6, 1));
        this.setTitle("Smart Energy Management and Monitor system");
        //this.setSize(400, 300);
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
        if (e.getSource() == jb1) {
            //String id = jtf1.getText();
            File file = new File(GlobalVar.userName);
            if (!file.exists() || !file.isDirectory()) {
                j5.setText("no such user");
            } else if (y > y2) {
                j5.setText("Please select correct data");
            } else if (mo > mo2) {
                j5.setText("Please select correct data");
            } else if (d > d2) {
                j5.setText("Please select correct data");
            } else {
                new UserBillShowUI(GlobalVar.userName, y, mo, d, y2, mo2, d2);
                this.dispose();
            }
        } else if (e.getSource() == jb3) {
            new UserUI();
            this.dispose();
        }
    }

    /**
     * calculate days in select month & year
     */
    private void setDay(JComboBox year, JComboBox month, JComboBox day) {
        y = Integer.parseInt((String) year.getSelectedItem());
        mo = Integer.parseInt((String) month.getSelectedItem());
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, y);
        c.set(Calendar.MONTH, mo - 1);
        int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        day.setModel(new DefaultComboBoxModel(getModel(1, days)));

    }

    private void setDay2(JComboBox year, JComboBox month, JComboBox day) {
        y2 = Integer.parseInt((String) year.getSelectedItem());
        mo2 = Integer.parseInt((String) month.getSelectedItem());
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, y2);
        c.set(Calendar.MONTH, mo2 - 1);
        int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        day.setModel(new DefaultComboBoxModel(getModel(1, days)));
    }

    /**
     * get String array [start, end]
     */
    private String[] getModel(int start, int end) {
        String[] m = new String[end - start + 1];
        for (int i = 0; i < m.length; i++) {
            m[i] = String.valueOf(i + start);
        }
        return m;
    }


    public void clearall() {
        jtf1.setText("");
    }
}

