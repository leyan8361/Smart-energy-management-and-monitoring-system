import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.io.*;
import java.util.ArrayList;

/**
 * @author group 66
 * This is a provider interface
 */
public class ProviderUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    JLabel jlabel1, jlabel2, jlabel3;
    JButton button, button2, button3, button4, button5, button6;
    JPanel buttonPanel, buttonPanel1, buttonPanel2, p3;

    @SuppressWarnings("null")
    public ProviderUI() {
        this.setTitle("Welcom to the management interface");
        this.setSize(400, 400);
        this.setLayout(new GridLayout(0, 1));
        jlabel1 = new JLabel("Hello Provider");
        jlabel2 = new JLabel("");
        jlabel3 = new JLabel("");
        jlabel1.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        button = new JButton("ADD USER");
        button2 = new JButton("REMOVE USER");
        button3 = new JButton("VIEW HISTORY");
        button4 = new JButton("SET TARIFF");
        button5 = new JButton("RECEIVE BILL");
        button6 = new JButton("BACK TO SIGN IN");
        buttonPanel = new JPanel();
        buttonPanel1 = new JPanel(null);
        buttonPanel2 = new JPanel(null);
        p3 = new JPanel(null);
        buttonPanel.add(jlabel1);
        buttonPanel1.add(button);
        buttonPanel1.add(button2);
        buttonPanel2.add(button3);
        buttonPanel2.add(button4);
        button.setBounds(50, 10, 150, 30);
        button2.setBounds(200, 10, 150, 30);
        button3.setBounds(50, 10, 150, 30);
        button4.setBounds(200, 10, 150, 30);
        button5.setBounds(50, 10, 150, 30);
        button6.setBounds(200, 10, 150, 30);
        p3.add(button5);
        p3.add(button6);
        this.add(jlabel2);
        this.add(buttonPanel);
        this.add(buttonPanel1);
        this.add(buttonPanel2);
        this.add(p3);
        this.add(jlabel3);
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

        //****************************
        new Thread(new Time()).start();
    }

    //*************************
    class Time implements Runnable {
        public void run() {
            while (true) {
                ArrayList list = new ArrayList();
                String path = "upassword.txt";
                File file = new File(path);
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        String item[] = line.split(" ");
                        list.add(item[0]);
                    }
                    reader.close();
                } catch (Exception e) {
                }
                Calendar C = Calendar.getInstance();
                C.setTime(new Date());
                int year = C.get(Calendar.YEAR);
                int month = C.get(Calendar.MONTH);
                int hour = C.get(Calendar.HOUR_OF_DAY);
                int minute = C.get(Calendar.MINUTE);
                int date = C.get(Calendar.DATE);
                int sec = C.get(Calendar.SECOND);
                if (month == 0) {
                    month = 12;
                    year = year - 1;
                }
                if (date == 1 && hour == 0 && minute == 0 && sec == 30) {
                    System.out.println("sending bill");
                    JOptionPane.showMessageDialog(null, "Bill is sending by user's email", "processing", JOptionPane.PLAIN_MESSAGE);
                    int a = list.size();
                    ProviderBill[] provider = new ProviderBill[a];
                    for (int i = 0; i < list.size(); i++) {
                        String name = (String) list.get(i);
                        provider[i] = new ProviderBill(name, String.valueOf(year), String.valueOf(month));
                        provider[i].SendBill();
                    }
                }
                list = null;
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("a")) {
            //this.setVisible(false);
            new AdduserGUI();
            this.setVisible(false);
        }
        if (e.getActionCommand().equals("b")) {
            //this.setVisible(false);
            new RemoveGUI();
            this.setVisible(false);
        }
        if (e.getActionCommand().equals("c")) {
            //this.setVisible(false);
            new PreGUI();
            this.setVisible(false);
        }
        if (e.getActionCommand().equals("d")) {
            //this.setVisible(false);
            new TariffGUI();
            this.setVisible(false);
        }
        if (e.getActionCommand().equals("e")) {
            //this.setVisible(false);
            new Pre2();
            this.setVisible(false);
        }
        if (e.getActionCommand().equals("f")) {
            //this.setVisible(false);
            new MainUI();
            this.setVisible(false);
        }
    }
}