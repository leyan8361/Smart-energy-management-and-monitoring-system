import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author group 66
 * This is a viewing history interface
 */
public class WeekHistoricShowUI extends JFrame implements ActionListener {
    JButton backButton;
    JPanel gasPanel, gasCostPanel, elecPanel, elecCostPanel, totalPanel, buttonPanel;
    JLabel gasLabel, gasCostLabel, elecLabel, elecCostLabel, gasInfo, gasCostInfo, elecInfo, elecCostInfo;
    int year1, month1, day1, year2, month2, day2;
    String id, path, path2;
    String path_gas, path_gasCost, path_electricity, path_electricityCost;
    File gasFile, gasCostFile, electricityFile, electricityCostFile;

    public WeekHistoricShowUI() {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        this.setLayout(null);
        this.id = GlobalVar.userName;
        this.year1 = today.get(Calendar.YEAR);
        this.month1 = today.get(Calendar.MONTH) + 1;
        this.day1 = today.get(Calendar.DAY_OF_MONTH);
        today.add(Calendar.DATE, -6);
        this.year2 = today.get(Calendar.YEAR);
        this.month2 = today.get(Calendar.MONTH) + 1;
        this.day2 = today.get(Calendar.DAY_OF_MONTH);

        JTextArea test_elec = new JTextArea(5, 20);
        JTextArea test_gas = new JTextArea(5, 20);
        JTextArea test_elec_cost = new JTextArea(5, 20);
        JTextArea test_gas_cost = new JTextArea(5, 20);

        test_elec.setEditable(false);
        test_gas.setEditable(false);
        test_elec_cost.setEditable(false);
        test_gas_cost.setEditable(false);

        test_elec.setLineWrap(true);
        test_gas.setLineWrap(true);
        test_elec_cost.setLineWrap(true);
        test_gas_cost.setLineWrap(true);

        this.setTitle("Smart Energy Management and Monitor system");
        JScrollPane scroller_elec = new JScrollPane(test_elec);
        JScrollPane scroller_gas = new JScrollPane(test_gas);
        JScrollPane scroller_elec_cost = new JScrollPane(test_elec_cost);
        JScrollPane scroller_gas_cost = new JScrollPane(test_gas_cost);

        scroller_elec.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller_elec.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scroller_gas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller_gas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scroller_elec_cost.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller_elec_cost.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scroller_gas_cost.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller_gas_cost.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        gasPanel = new JPanel();
        gasCostPanel = new JPanel();
        elecPanel = new JPanel();
        elecCostPanel = new JPanel();
        buttonPanel = new JPanel();

        gasPanel.add(scroller_elec);
        gasCostPanel.add(scroller_gas);
        elecPanel.add(scroller_elec_cost);
        elecCostPanel.add(scroller_gas_cost);

        UserBill userBill = new UserBill(GlobalVar.userName, "" + this.year2, "" + this.month2, "" + this.day2, "" + this.year1, "" + this.month1, "" + this.day1);
        ArrayList arrayList1 = userBill.HistoryConsumption();
        if (arrayList1 == null) {
            JOptionPane.showMessageDialog(null, "You don't have historic informaiton of this week yet!", "error", JOptionPane.PLAIN_MESSAGE);
            new UserUI();
            this.dispose();
        } else {
            ArrayList<String> listGas = (ArrayList) arrayList1.get(1);
            ArrayList<String> listElec = (ArrayList) arrayList1.get(3);
            ArrayList arrayList2 = userBill.HistoryCost();
            ArrayList<String> listGasCost = (ArrayList) arrayList2.get(1);
            ArrayList<String> listElecCost = (ArrayList) arrayList2.get(3);

            Font bigFont = new Font("serif", Font.BOLD, 20);
            gasLabel = new JLabel("Your Gas Consumption", JLabel.CENTER);
            gasLabel.setFont(bigFont);

            gasCostLabel = new JLabel("Your Gas Cost", JLabel.CENTER);
            gasCostLabel.setFont(bigFont);

            elecLabel = new JLabel("Your Electricity Consumption", JLabel.CENTER);
            elecLabel.setFont(bigFont);

            elecCostLabel = new JLabel("Your Electricity Cost", JLabel.CENTER);
            elecCostLabel.setFont(bigFont);

            backButton = new JButton("back");
            backButton.addActionListener(this);

            elecLabel.setBounds(10, 10, 270, 35);
            gasLabel.setBounds(330, 10, 250, 35);
            elecCostLabel.setBounds(20, 200, 200, 35);
            gasCostLabel.setBounds(340, 200, 200, 35);
            backButton.setBounds(250, 400, 100, 20);


            this.add(backButton);

            for (String string : listGas) {
                string = string + '\n';
                test_gas.append(string);
            }
            for (String string : listElec) {
                string = string + '\n';
                test_elec.append(string);
            }
            for (String string : listGasCost) {
                string = string + '\n';
                test_gas_cost.append(string);
            }
            for (String string : listElecCost) {
                string = string + '\n';
                test_elec_cost.append(string);
            }

            test_elec.requestFocus();
            test_gas.requestFocus();
            test_elec_cost.requestFocus();
            test_gas_cost.requestFocus();


            gasPanel.setBounds(10, 50, 260, 150);
            gasCostPanel.setBounds(330, 50, 260, 150);
            elecPanel.setBounds(10, 270, 260, 150);
            elecCostPanel.setBounds(330, 270, 260, 150);

            this.add(gasPanel);
            this.add(gasCostPanel);
            this.add(elecPanel);
            this.add(elecCostPanel);

            this.add(gasLabel);
            this.add(gasCostLabel);
            this.add(elecLabel);
            this.add(elecCostLabel);

            this.pack();
            this.setSize(600, 500);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension size = this.getSize();
            int wei = (screenSize.width - size.width) / 2;
            int hei = (screenSize.height - size.height) / 2;
            this.setLocation(wei, hei);
            this.setVisible(true);
        }


    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new UserUI();
            this.dispose();
        }
    }

    public int getLines(File file, ArrayList<String> lines) {
        int lineNumber = 0;
        String line;
        try {
            BufferedReader reader_file = new BufferedReader(new FileReader(file));
            while ((line = reader_file.readLine()) != null) {
                lineNumber++;
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineNumber;
    }

}