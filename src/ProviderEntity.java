import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProviderEntity {
    private float elecprice;
    private float gasprice;
    private float elecmonth;
    private float gasmonth;
    String name;

    public float getGasprice() {
        String path = "price" + "/" + "gasprice.txt";
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                gasprice = Float.parseFloat(line);
            }
            reader.close();
            return gasprice;
        } catch (IOException e) {
            return -1;
        }
    }

    public void setGasprice(float price) {
        String path = "price" + "/" + "gasprice.txt";
        File file = new File(path);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(String.valueOf(price));
            out.close();
        } catch (IOException e) {
        }
    }

    public float getElecprice() {
        String path = "price" + "/" + "elecprice.txt";
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                elecprice = Float.parseFloat(line);
            }
            reader.close();
            return elecprice;
        } catch (IOException e) {
            return -1;
        }
    }

    public void setElecprice(float price) {
        String path = "price" + "/" + "elecprice.txt";
        File file = new File(path);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(String.valueOf(price));
            out.close();
        } catch (IOException e) {
        }
    }

    public float getElecmonth(String user, String year, String month) {
        String path = user + "/" + "MonthlyConsumption.txt";
        String[] number;
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                number = line.split("-");
                if (number.length != 6) {
                    continue;
                }
                if (year.equals(number[0]) && month.equals(number[1])) {
                    return Float.parseFloat(number[5]);
                }
            }
            reader.close();
        } catch (IOException e) {
        }
        return -1;
    }

    public float getGasmonth(String user, String year, String month) {
        String path = user + "/" + "MonthlyConsumption.txt";
        String[] number;
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                number = line.split("-");
                if (number.length != 6) {
                    continue;
                }
                if (year.equals(number[0]) && month.equals(number[1])) {
                    return Float.parseFloat(number[3]);
                }
            }
            reader.close();
        } catch (IOException e) {
        }
        return -1;
    }
}
