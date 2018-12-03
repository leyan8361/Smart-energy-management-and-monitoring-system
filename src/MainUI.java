import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.*;

public class MainUI extends JFrame implements ActionListener {
    JButton jb1, jb2, jb3 = null;
    JTextField jtf1 = null;//ID
    JPasswordField jpf1 = null;//password
    JRadioButton jrbUser, jrbProvider = null;//to choose the entrance
    JPanel jp0, jp1, jp2, jp3, jp4;
    JLabel jlabel1, jlabel2, jlabel3;
    ButtonGroup chooseEntrance;


    public MainUI() {


        jb1 = new JButton("Login");
        jb2 = new JButton("Clear");

        jb1.addActionListener(this);
        jb2.addActionListener(this);


        jrbUser = new JRadioButton("Consumer");
        jrbProvider = new JRadioButton("Provider");

        chooseEntrance = new ButtonGroup();
        chooseEntrance.add(jrbProvider);
        chooseEntrance.add(jrbUser);
        jrbUser.addActionListener(this);
        jrbProvider.addActionListener(this);
        jrbUser.setSelected(true);


        jp0 = new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();

        jlabel1 = new JLabel("     ID       :");
        jlabel2 = new JLabel("Password:");
        jlabel3 = new JLabel("Welcome!");

        jtf1 = new JTextField(10);
        jpf1 = new JPasswordField(10);

        jp0.add(jlabel3);


        jp1.add(jlabel1);
        jp1.add(jtf1);

        jp2.add(jlabel2);
        jp2.add(jpf1);

        jp3.add(jrbUser);
        jp3.add(jrbProvider);

        jp4.add(jb1);
        jp4.add(jb2);


        this.add(jp0);
        this.add(jp3);
        this.add(jp1);
        this.add(jp2);
        this.add(jp4);

        this.setLayout(new GridLayout(5, 1));
        this.setTitle("Smart Energy Management and Monitor system");
        this.setSize(400, 200);
        this.setLocation(400, 200);
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
        DefaultButtonModel model = (DefaultButtonModel) jrbUser.getModel();
        String usernameinSt = jtf1.getText();
        String passwordinSt = new String(jpf1.getPassword());
        if (e.getSource() == jb1) {
            if (usernameinSt.length() > 8) {
                jlabel3.setText("id too long");
                return;
            }
            if (passwordinSt.length() > 8) {
                jlabel3.setText("password too long");
                return;
            }
            if(!MotifyUser.isNumeric3(usernameinSt)){
                jlabel3.setText("Please input a number");
                return;
            }
            if(!MotifyUser.isNumeric3(passwordinSt)){
                jlabel3.setText("Please input a number");
                return;
            }
            if (model.getGroup().isSelected(model)) {
                user();
            } else {
                provider();
            }
        } else if (e.getSource() == jb2) {
            clearall();
        }
    }


    public void user() {

        String usernameinSt = jtf1.getText();
        String passwordinSt = new String(jpf1.getPassword());
        int usernamein = 0;
        int passwordin = 0;
        usernamein = Integer.valueOf(usernameinSt);
        passwordin = Integer.valueOf(passwordinSt);
        int checkN = 0;
        BufferedReader ur = null;
        try {
            FileReader userfile = new FileReader("upassword.txt");
            ur = new BufferedReader(userfile);
            String line = ur.readLine();

            int[] username = new int[100];
            int[] password = new int[100];
            int i = 0;
            while (line != null) {
                String[] numbers = line.split("\\s+");
                username[i] = Integer.valueOf(numbers[0]);
                password[i] = Integer.valueOf(numbers[1]);
                i++;
                line = ur.readLine();
            }

            while (i >= 0) {
                if (usernamein == username[i]) {
                    if (passwordin == password[i]) {
                        GlobalVar.userName = usernameinSt;
                        jlabel3.setText("You have logged in successfully!");
                        clearall();
                        UserUI userUI = new UserUI();
                        this.dispose();
                        checkN = 1;
                        return;
                    }
                }
                i = i - 1;
            }

            ur.close();

        } catch (FileNotFoundException e) {
            jlabel3.setText("You have enter a wrong username or password, please try again.");
            clearall();
        } catch (IOException e) {
            jlabel3.setText("You have enter a wrong username or password, please try again.");
            clearall();
        } catch (Exception except) {
            jlabel3.setText("You have enter a wrong username or password, please try again.");
            clearall();
        }
        if (checkN == 0) {
            jlabel3.setText("You have enter a wrong username or password, please try again.");
            clearall();

        }
    }

    public void provider() {
        String usernameinSt = jtf1.getText();
        String passwordinSt = new String(jpf1.getPassword());
        int usernamein = 0;
        int passwordin = 0;
        usernamein = Integer.valueOf(usernameinSt);
        passwordin = Integer.valueOf(passwordinSt);
        int checkN = 0;
        BufferedReader ur = null;
        try {
            FileReader userfile = new FileReader("ppassword.txt");
            ur = new BufferedReader(userfile);
            String line = ur.readLine();

            int[] username = new int[100];
            int[] password = new int[100];
            int i = 0;
            while (line != null) {
                String[] numbers = line.split("\\s+");
                username[i] = Integer.valueOf(numbers[0]);
                password[i] = Integer.valueOf(numbers[1]);
                i++;
                line = ur.readLine();
            }

            while (i >= 0) {
                if (usernamein == username[i]) {
                    if (passwordin == password[i]) {
                        jlabel3.setText("You have logged in successfully!");
                        clearall();
                        //this.setVisible(false);
                        ProviderUI providerUI = new ProviderUI();
                        this.dispose();
                        //providerUI.setVisible(true);
                        checkN = 1;
                    }
                }
                i = i - 1;
            }
            ur.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (checkN == 0) {
            jlabel3.setText("You have enteren wrong username or password, please try again.");
            clearall();
        }
    }

    public void clearall() {
        jtf1.setText("");
        jpf1.setText("");
    }
}