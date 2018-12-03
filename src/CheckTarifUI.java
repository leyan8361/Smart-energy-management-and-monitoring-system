import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckTarifUI extends JFrame implements ActionListener {
    JLabel gasLabel;
    JLabel elecLabel;
    JPanel jp1;
    JButton button;

    public CheckTarifUI() throws HeadlessException {
        this.setLayout(new GridLayout(0, 1));
        gasLabel = new JLabel("Gas tariff is: " + GlobalVar.user.getGasPrice());
        elecLabel = new JLabel("Electricity tariff is: " + GlobalVar.user.getElectricityPrice());
        button = new JButton("Back");
        jp1 = new JPanel();
        this.add(gasLabel);
        this.add(elecLabel);
        this.add(jp1);
        this.setTitle("Smart Energy Management and Monitor system");
        button.addActionListener(this);
        this.setSize(400, 150);
        jp1.add(button);
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
        new UserUI();
        this.dispose();
    }
}

