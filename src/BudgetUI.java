import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author group 66
 * This is a budget interface
 */
public class BudgetUI extends JFrame implements ActionListener {
    JLabel gasBudegtLabel, gasCostLabel, elecBudgetLabel, elecCostLabel;
    JButton button, button2;
    JPanel jp0, jp1, jp2, jp3, jp4, jp5, jp6;
    int color1, color2;
    Timer alarmTimer;
    MyPanel myJPanel, myJPanel2;

    public BudgetUI() throws HeadlessException {
        this.setLayout(new GridLayout(0, 1));
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        gasBudegtLabel = new JLabel("Your gas budget is: " + GlobalVar.user.getGasBudget());
        gasCostLabel = new JLabel("your gas cost is: " + decimalFormat.format(GlobalVar.user.getGasCost()));
        elecBudgetLabel = new JLabel("Your electricity budget is: " + GlobalVar.user.getElecBudget());
        elecCostLabel = new JLabel("your electricty is: " + decimalFormat.format(GlobalVar.user.getElectricityCost()));
        button = new JButton("Back");
        button2 = new JButton("Change");
        if (GlobalVar.user.getGasBudget() > GlobalVar.user.getGasCost()) {
            color1 = 0;
        } else {
            color1 = 1;
        }
        if (GlobalVar.user.getElecBudget() > GlobalVar.user.getElectricityCost()) {
            color2 = 0;
        } else {
            color2 = 1;
        }
        myJPanel = new MyPanel(color1);
        myJPanel2 = new MyPanel(color2);
        this.setTitle("Smart Energy Management and Monitor system");
        jp0 = new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp1.add(gasCostLabel);
        jp2.add(button2);
        jp2.add(button);
        myJPanel2.add(elecBudgetLabel);
        jp5.add(elecCostLabel);
        jp6.add(gasCostLabel);


        this.add(jp4);
        this.add(myJPanel);
        this.add(jp6);
        this.add(jp3);
        this.add(myJPanel2);
        this.add(jp5);
        this.add(jp2);
        myJPanel.add(gasBudegtLabel);
        this.setVisible(true);
        button.addActionListener(this);
        button2.addActionListener(this);
        this.setSize(400, 300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = this.getSize();
        int wei = (screenSize.width - size.width) / 2;
        int hei = (screenSize.height - size.height) / 2;
        this.setLocation(wei, hei);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        alarmTimer = new Timer();
        alarmTimer.schedule(new AlarmTask(GlobalVar.user), 0, 2000);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            alarmTimer.cancel();
            new UserUI();
            this.dispose();
        } else {
            alarmTimer.cancel();
            new ChangeUI();
            this.dispose();
        }
    }

    class MyPanel extends JPanel {
        public int num;

        public MyPanel(int num) {
            this.num = num;
        }

        public void paint(Graphics g) {
            super.paint(g);
            if (num == 0) {
                g.setColor(Color.GREEN);
                g.fillOval(330, 7, 10, 10);
            } else if (num == 1) {
                g.setColor(Color.RED);
                g.fillOval(330, 7, 10, 10);
            }
        }
    }

    class AlarmTask extends TimerTask {
        public User user;

        public AlarmTask(User user) {
            this.user = user;
        }

        public void run() {
            DecimalFormat decimalFormat = new DecimalFormat(".00");
            gasBudegtLabel.setText("Your gas budget is: " + user.getGasBudget());
            gasCostLabel.setText("your gas cost is: " + decimalFormat.format(user.getGasCost()));

            elecBudgetLabel.setText("Your electricity budget is: " + user.getElecBudget());
            elecCostLabel.setText("your electricty is: " + decimalFormat.format(user.getElectricityCost()));

            if (user.getGasBudget() > user.getGasCost()) {
                color1 = 0;
            } else {
                color1 = 1;
            }
            if (user.getElecBudget() > user.getElectricityCost()) {
                color2 = 0;
            } else {
                color2 = 1;
            }

            myJPanel.repaint();
            myJPanel2.repaint();
        }
    }
}
