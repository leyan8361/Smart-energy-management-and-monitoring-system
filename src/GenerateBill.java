import java.io.*;

public class GenerateBill {
    String elec;
    String gas;
    String month;
    String year;
    float price_gas = 0;
    float price_elec = 0;
    float total_gas = 0;
    float total_elec = 0;
    String path;
    String GUI_path;
    String YY, MM;

    GenerateBill(String user, String Year, String Month) {
        elec = user + "/elechistory.txt";
        gas = user + "/gashistory.txt";
        year = Year;
        if (Integer.parseInt(Month) < 10) {
            month = "0" + Month;
        } else if (9 < Integer.parseInt(Month) && Integer.parseInt(Month) < 13) {
            month = Month;
        }

        path = user + "/" + "MonthlyConsumption.txt"; //path

        //GUI_path = user + "\\" + "ForGUI.txt";

    }

    public boolean billCaculate() {
        String gas_date, elec_date;
        String[] number_gas, gas_split_date, elec_split_date;
        String line_gas;
        String[] number_elec;
        String line_elec;
        File file_gas = new File(gas);
        try {
            BufferedReader reader_gas = new BufferedReader(new FileReader(file_gas));
            while ((line_gas = reader_gas.readLine()) != null) {
                number_gas = line_gas.split(",");
                if (number_gas.length != 2) {
                    continue;
                }
                gas_date = number_gas[1];
                gas_split_date = gas_date.split("-");
                YY = gas_split_date[0];
                MM = gas_split_date[1];
                if (year.equals(YY)) {
                    if (month.equals(MM)) {
                        total_gas = total_gas + Integer.valueOf(number_gas[0]).intValue();
                    }
                }
            }
            reader_gas.close();

            File file_elec = new File(elec);
            BufferedReader reader_elec = new BufferedReader(new FileReader(file_elec));
            while ((line_elec = reader_elec.readLine()) != null) {
                number_elec = line_elec.split(",");
                if (number_elec.length != 2) {
                    continue;
                }
                elec_date = number_elec[1];
                elec_split_date = elec_date.split("-");
                YY = elec_split_date[0];
                MM = elec_split_date[1];

                if (year.equals(YY)) {
                    if (month.equals(MM)) {
                        total_elec = total_elec + Integer.valueOf(number_elec[0]).intValue();
                    }
                }
            }
            reader_elec.close();
            FileFunction.chaseWrite(path, year + "-" + month + "-" + "gas-" + String.valueOf(total_gas) + "-elec-" + String.valueOf(total_elec));
				/*
				FileFunction.writeFile(GUI_path,month + " " + "gas_price: " + String.valueOf(total_gas) + " elec_price " + String.valueOf(total_elec) + "\n");
				if(total_elec == 0 || total_gas == 0){
					FileFunction.writeFile(GUI_path,"There is no such month!");
					return false;
				}
				else {return true;}
				*/
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}