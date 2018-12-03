import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author group 66
 * This is a user interface
 */
public class UserUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    JLabel jlabel1, jlabel2, jlabel3;
    JButton button, button2, button3, button4, button5, button6;
    JPanel buttonPanel, buttonPanel1, buttonPanel2, buttonPanel3, b4;

    protected String userName;

    @SuppressWarnings("null")
    public UserUI() {
        //System.out.println("user name is " + GlobalVar.userName);
        this.setTitle("Smart Energy Management and Monitor system");
        this.userName = GlobalVar.userName;
        if (GlobalVar.user == null) {
            GlobalVar.user = new User(userName);
        }
        if (GlobalVar.userMonitor == null) {
            GlobalVar.userMonitor = new UserMonitor(GlobalVar.user);
            GlobalVar.userMonitor.startMonitor();
        }
        //GUI operation
        this.setTitle("Welcome to the User interface");
        this.setSize(400, 400);
        this.setLayout(new GridLayout(0, 1));
        jlabel1 = new JLabel("Hello User");
        jlabel2 = new JLabel("");
        jlabel3 = new JLabel("");
        jlabel1.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        button = new JButton("CONSUMPTION");
        button2 = new JButton("BUDGET ALERT");
        button3 = new JButton("HISTORIC INFO");
        button4 = new JButton("CHECK TARIFF");
        button5 = new JButton("RECEIVE BILL");
        button6 = new JButton(("BACK TO SIGN IN"));
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel1 = new JPanel(null);
        buttonPanel2 = new JPanel(null);
        buttonPanel3 = new JPanel(null);
        buttonPanel.add(jlabel1);
        buttonPanel1.add(button);
        buttonPanel1.add(button2);
        buttonPanel2.add(button3);
        buttonPanel2.add(button4);
        buttonPanel3.add(button5);
        buttonPanel3.add(button6);
        this.add(jlabel2);
        this.add(buttonPanel);
        this.add(buttonPanel1);
        this.add(buttonPanel2);
        this.add(buttonPanel3);
        this.add(jlabel3);
        button.setBounds(50, 10, 150, 30);
        button2.setBounds(200, 10, 150, 30);
        button3.setBounds(50, 10, 150, 30);
        button4.setBounds(200, 10, 150, 30);
        button5.setBounds(50, 10, 150, 30);
        button6.setBounds(200, 10, 150, 30);
        button.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button.setActionCommand("a");
        button2.setActionCommand("b");
        button3.setActionCommand("c");
        button4.setActionCommand("d");
        button5.setActionCommand("e");
        button6.setActionCommand("f");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = this.getSize();
        int wei = (screenSize.width - size.width) / 2;
        int hei = (screenSize.height - size.height) / 2;
        this.setLocation(wei, hei);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("a")) {
            //this.setVisible(false);
            new MeterUI();
            this.dispose();
        }
        if (e.getActionCommand().equals("b")) {
            //this.setVisible(false);
            new BudgetUI();
            this.dispose();
        }
        if (e.getActionCommand().equals("c")) {
            //this.setVisible(false);
            String[] options = {"Daily", "The latest week"};
            String option = null;
            try {
                option = JOptionPane.showInputDialog(null, "Please choose the what kind of historic information you want to get", "Choose", JOptionPane.INFORMATION_MESSAGE, null, options, null).toString();
                if (option.equals("Daily")) {
                    new HistoricUI();
                    this.dispose();
                } else {
                    new WeekHistoricShowUI();
                    this.dispose();
                }
            } catch (Exception except) {

            }
        }
        if (e.getActionCommand().equals("d")) {
            //this.setVisible(false);
            new CheckTarifUI();
            this.dispose();
        }
        if (e.getActionCommand().equals("e")) {
            //this.setVisible(false);
            new UserBillUI();
            this.dispose();
        }
        if (e.getActionCommand().equals("f")) {
            //this.setVisible(false);
            GlobalVar.userMonitor.closeMonitor();
            GlobalVar.userMonitor = null;
            GlobalVar.user = null;
            GlobalVar.userName = null;
            new MainUI();
            this.dispose();
        }
    }
}