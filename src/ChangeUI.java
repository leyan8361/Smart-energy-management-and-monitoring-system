import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author group 66
 * This is a change user interface
 */
public class ChangeUI extends JFrame implements ActionListener {
    JLabel label1, label2;
    JButton button, button1, button2;
    JTextField jtf1, jtf2 = null;
    JPanel jp0, jp1, jp2, jp3, jp4;

    public ChangeUI() throws HeadlessException {
        this.setLayout(new GridLayout(0, 1));
        this.setTitle("Smart Energy Management and Monitor system");
        label1 = new JLabel("Enter the gas bugget");
        jtf1 = new JTextField(10);
        label2 = new JLabel("Enter the elec bugget");
        jtf2 = new JTextField(10);
        button = new JButton("Back");
        button1 = new JButton("Change");
        button2 = new JButton("Clear");
        jp0 = new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp1.add(label1);
        jp1.add(jtf1);
        jp4.add(label2);
        jp4.add(jtf2);
        jp2.add(button1);
        jp2.add(button2);
        jp2.add(button);
        this.add(jp3);
        this.add(jp1);
        this.add(jp4);
        this.add(jp2);
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        this.setSize(400, 200);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = this.getSize();
        int wei = (screenSize.width - size.width) / 2;
        int hei = (screenSize.height - size.height) / 2;
        this.setLocation(wei, hei);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            new UserUI();
            this.dispose();
        } else if (e.getSource() == button1) {
            String str = jtf1.getText();
            if(!MotifyUser.isNumeric3(str)){
                JOptionPane.showMessageDialog(null, "Please input a number", "Warning",JOptionPane.WARNING_MESSAGE);
                return;
            }
            int num = Integer.parseInt(str);
            String str2 = jtf1.getText();
            if(!MotifyUser.isNumeric3(str2)){
                JOptionPane.showMessageDialog(null, "Please input a number", "Warning",JOptionPane.WARNING_MESSAGE);
                return;
            }
            int num2 = Integer.parseInt(str2);
            GlobalVar.user.setGasBudget(num);
            GlobalVar.user.setElecBudget(num2);
            JOptionPane.showMessageDialog(null, "Change successfully!", "Successful", JOptionPane.PLAIN_MESSAGE);
            new BudgetUI();
            this.dispose();
        } else {
            jtf1.setText("");
            jtf2.setText("");
        }
    }
}
