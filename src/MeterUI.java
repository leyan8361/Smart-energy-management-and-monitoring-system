import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author group 66
 * This is a set meter interface
 */
public class MeterUI extends JFrame implements ActionListener {
    protected JLabel eleConsumptionLabel, eleCostLabel, gasConsumptionLabel, gasCostLabel;
    protected JButton button1;
    protected JPanel panel1, panel2, panel3;
    private int number1 = 20000;
    private int number2 = 20000;
    private int number3 = 20000;
    private int number4 = 20000;
    private String userName;
    private Timer elecTimer;

    public MeterUI() throws HeadlessException {

        this.getContentPane().setLayout(new GridLayout(0, 1));
        eleConsumptionLabel = new JLabel("your electricity consumption is: " + number1);
        eleCostLabel = new JLabel("your electricity cost is: " + number2);
        gasConsumptionLabel = new JLabel("your gas consumption is: " + number3);
        gasCostLabel = new JLabel(("your gas cost is: " + number4));
        button1 = new JButton("Back");
        panel1 = new JPanel(new GridLayout(0, 2));
        panel2 = new JPanel(new GridLayout(0, 2));
        panel3 = new JPanel(new BorderLayout());
        panel1.add(eleConsumptionLabel);
        panel1.add(eleCostLabel);
        panel2.add(gasConsumptionLabel);
        panel2.add(gasCostLabel);
        panel3.add(button1, BorderLayout.CENTER);
        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.getContentPane().add(panel3);
        this.setSize(485, 130);
        this.setTitle("Smart Energy Management and Monitor system");
        button1.addActionListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = this.getSize();
        int wei = (screenSize.width - size.width) / 2;
        int hei = (screenSize.height - size.height) / 2;
        this.setLocation(wei, hei);
        this.setVisible(true);

        elecTimer = new Timer();
        elecTimer.schedule(new MeterTask(GlobalVar.user), 0, 2000);
    }

    public void actionPerformed(ActionEvent e) {
        elecTimer.cancel();
        new UserUI();
        this.dispose();
    }

    class MeterTask extends TimerTask {
        public User user;
        private String gas;
        private String gasCost;

        public MeterTask(User user) {
            this.user = user;
        }

        public void run() {
            DecimalFormat decimalFormat = new DecimalFormat(".00");
            gasConsumptionLabel.setText("your electricity consumption is: " + decimalFormat.format((float) user.getGas()));
            gasCostLabel.setText("your electricity cost is: " + decimalFormat.format(user.getGasCost()));
            eleConsumptionLabel.setText("your gas consumption is: " + decimalFormat.format((float) user.getElectricity()));
            eleCostLabel.setText("your gas cost is: " + decimalFormat.format((float) user.getElectricityCost()));
        }
    }
}
