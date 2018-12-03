import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is a control class consists of functions needed to modify users
 */
public class MotifyUser {

    public static void delUserAccount(String id) {
        try {
            File file = new File("upassword.txt");
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "gbk");
            BufferedReader bufferedReader = new BufferedReader(reader);
            FileFunction.writeFile("upassword1.txt", "");
            String s = null;
            String[] splits;
            while ((s = bufferedReader.readLine()) != null) {
                splits = s.split(" ");
                if (!splits[0].equals(id)) {
                    FileFunction.chaseWrite("upassword1.txt", s);
                }
            }
            bufferedReader.close();
            reader.close();
            if (file.exists())
                file.delete();
            File newFile = new File("upassword1.txt");
            if (newFile.exists())
                newFile.renameTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function is used to delete user folder
     *
     * @param folderPath The name of the folder you want to delete
     */
    public static void delUserFolder(String folderPath) {
        try {
            delAllUserFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //delete the empty folder

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function is used to delete all user files
     *
     * @param path The file path you want to delete
     * @return Whether the operation is successfully done
     */
    public static boolean delAllUserFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllUserFile(path + "/" + tempList[i]);
                delUserFolder(path + "/" + tempList[i]);
                flag = true;
            }
        }
        return flag;
    }


    /**
     * This function is used to create a folder to new user
     *
     * @param userName user name
     * @return Whether the operation is successfully done
     */
    public static boolean createFolder(String userName) {
        File dirFile;
        boolean flag = false;
        try {
            dirFile = new File(userName);
            flag = dirFile.exists();

            if (flag == true) {
                return false;
            } else {
                flag = dirFile.mkdirs();
                if (flag == true) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This function is used to create files for new user
     *
     * @param userName user name
     * @param password password
     * @param email    email address
     * @return Whether the operation is successfully done
     */
    public static boolean createFile(String userName, String password, String email) {
        String budgetElecFileName = userName + "/budgetElec.txt";
        String budgetGasFileName = userName + "/budgetGas.txt";
        String electricityCostFileName = userName + "/eleccost.txt";
        String electricityFileName = userName + "/electricity.txt";
        String gasFileName = userName + "/gas.txt";
        String gasCostFileName = userName + "/gascost.txt";
        String gasHistoryFileName = "gashistory.txt";
        String elecHistoryFileName = "elechistory.txt";
        String gCostHistoryFileName = "gcosthistory.txt";
        String eCostHistoryFileName = "ecosthistory.txt";
        String userPasswordFileName = "upassword.txt";
        String MonthlyBill = "BillMonthly.txt";
        String MonthlyConsumption = "MonthlyConsumption.txt";
        try {
            File path = new File(userName);
            File dir = null;
            dir = new File(path, budgetElecFileName);
            if (!dir.exists()) {
                //dir.createNewFile();
                FileFunction.writeFile(budgetElecFileName, "0");
            }
            dir = new File(path, budgetGasFileName);
            if (!dir.exists()) {
                //dir.createNewFile();
                FileFunction.writeFile(budgetGasFileName, "0");
            }
            dir = new File(path, electricityCostFileName);
            if (!dir.exists()) {
                //dir.createNewFile();
                FileFunction.writeFile(electricityCostFileName, "0");
            }
            dir = new File(path, electricityFileName);
            if (!dir.exists()) {
                //dir.createNewFile();
                FileFunction.writeFile(electricityFileName, "0");
            }
            dir = new File(path, gasFileName);
            if (!dir.exists()) {
                //dir.createNewFile();
                FileFunction.writeFile(gasFileName, "0");
            }
            dir = new File(path, gasCostFileName);
            if (!dir.exists()) {
                //dir.createNewFile();
                FileFunction.writeFile(gasCostFileName, "0");
            }
            dir = new File(path, gasHistoryFileName);
            if (!dir.exists()) {
                dir.createNewFile();
                //FileFunction.writeFile(budgetFileName, "0");
            }
            dir = new File(path, elecHistoryFileName);
            if (!dir.exists()) {
                dir.createNewFile();
                //FileFunction.writeFile(budgetFileName, "0");
            }
            dir = new File(path, gCostHistoryFileName);
            if (!dir.exists()) {
                dir.createNewFile();
                //FileFunction.writeFile(budgetFileName, "0");
            }
            dir = new File(path, eCostHistoryFileName);
            if (!dir.exists()) {
                dir.createNewFile();
                //FileFunction.writeFile(budgetFileName, "0");
            }
            dir = new File(path, MonthlyBill);
            if (!dir.exists()) {
                dir.createNewFile();
                //FileFunction.writeFile(budgetFileName, "0");
            }
            dir = new File(userPasswordFileName);
            if (!dir.exists()) {
                dir.createNewFile();
            }
            dir = new File(path, MonthlyConsumption);
            if (!dir.exists()) {
                dir.createNewFile();
            }

            FileFunction.chaseWrite(userPasswordFileName, userName + " " + password + " " + email);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This function is a combination of create file and create folder
     *
     * @param userName user name
     * @param password password
     * @param email    email address
     * @return Whether the operation is successfully done
     */
    public static boolean createUserDir(String userName, String password, String email) {
        if (!createFolder(userName))
            return false;
        else if (!createFile(userName, password, email)) {
            return false;
        } else
            return true;
    }

    /**
     * This function is used to return the user history in a string
     *
     * @param userName  user name
     * @param startDate start date
     * @param endDate   end date
     * @return user history
     */
    public static String viewUserHistory(String userName, String startDate, String endDate) {
        String path_bill = userName + "/BillMonthly.txt";
        String path_gas = userName + "/gas.txt";
        String path_elec = userName + "/electricity.txt";
        String line_bill;
        String bill = "";
        String view_String = null;
        String number_bill[] = null;
        String number_month[] = null;
        String line_month = null;
        String line_int = null;
        File file_bill = new File(path_bill);
        try {
            BufferedReader reader_bill = new BufferedReader(new FileReader(file_bill));
            while ((line_bill = reader_bill.readLine()) != null) {
                number_bill = line_bill.split(" ");
                if (number_bill.length < 2) {
                    continue;
                }
                line_month = number_bill[0];
                if (line_month.indexOf("-0") != -1) {
                    number_month = line_month.split("-0");
                    line_int = number_month[1];
                } else {
                    number_month = line_month.split("-");
                    line_int = number_month[1];
                }
                if ((Integer.parseInt(line_int)) >= (Integer.parseInt(startDate)) && (Integer.parseInt(line_int)) <= (Integer.parseInt(endDate))) {
                    bill = bill + line_bill + "\r\n";
                }
            }
            reader_bill.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        view_String = "<html>history bill:<br>" + bill + "gas reading: " + FileFunction.readFile(path_gas) + "<br>" + "electricity reading: " + FileFunction.readFile(path_elec) + "</html>";
        return view_String;
    }

    public static boolean checkEmail(String email) {
        if (email == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(email);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static boolean isNumeric3(String str) {
        final String number = "0123456789";
        for (int i = 0; i < str.length(); i++) {
            if (number.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
}