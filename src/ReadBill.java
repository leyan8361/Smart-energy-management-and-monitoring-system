import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadBill {
    public String readBill(String User, String Year_start, String Month_start, String Year_end, String Month_end) {
        String readpath = User + "/BillMonthly.txt";
        String start_year, start_month, end_year, end_month;
        String read = "";
        start_year = Year_start;
        end_year = Year_end;
        start_month = null;
        end_month = null;
        String[] number_bill;
        if (Integer.parseInt(Month_start) < 10) {
            start_month = "0" + Month_start;
        } else if (9 < Integer.parseInt(Month_start) && Integer.parseInt(Month_start) < 13) {
            start_month = Month_start;
        }

        if (Integer.parseInt(Month_end) < 10) {
            end_month = "0" + Month_end;
        } else if (9 < Integer.parseInt(Month_end) && Integer.parseInt(Month_end) < 13) {
            end_month = Month_end;
        }

        File file_bill = new File(readpath);
        BufferedReader reader_bill = null;
        try {
            reader_bill = new BufferedReader(new FileReader(file_bill));
            String line_bill = null;
            while ((line_bill = reader_bill.readLine()) != null) {
                number_bill = line_bill.split("-");
                if (number_bill.length != 6) {
                    continue;
                }
                if (Integer.valueOf(start_year).intValue() < Integer.valueOf(number_bill[0]).intValue()
                        && Integer.valueOf(number_bill[0]).intValue() < Integer.valueOf(end_year).intValue()) {
                    read = read + line_bill + "\r\n";
                } else if (Integer.valueOf(start_year).intValue() == Integer.valueOf(number_bill[0]).intValue()
                        && Integer.valueOf(number_bill[0]).intValue() < Integer.valueOf(end_year).intValue()) {
                    if (Integer.valueOf(start_month).intValue() <= Integer.valueOf(number_bill[1]).intValue()) {
                        read = read + line_bill + "\r\n";
                    }
                } else if (Integer.valueOf(start_year).intValue() < Integer.valueOf(number_bill[0]).intValue()
                        && Integer.valueOf(number_bill[0]).intValue() == Integer.valueOf(end_year).intValue()) {
                    if (Integer.valueOf(end_month).intValue() >= Integer.valueOf(number_bill[1]).intValue()) {
                        read = read + line_bill + "\r\n";
                    }
                } else {
                    if (Integer.valueOf(start_month).intValue() <= Integer.valueOf(number_bill[1]).intValue()
                            && Integer.valueOf(end_month).intValue() >= Integer.valueOf(number_bill[1]).intValue()) {
                        read = read + line_bill + "\r\n";
                    }
                }
            }
            reader_bill.close();
            if (read == "") {
                return "There is no such month!";
            } else {
                return read;
            }

        } catch (IOException e) {
            return "There is error";
        }
    }
}
