import java.io.*;
import java.util.ArrayList;

/**
 * receive date to query
 * view gas and electricity consumption and cost
 *
 * @author group 66
 */
public class UserBill {
    String elec;
    String gas;
    String elec_price;
    String gas_price;
    String year_start, month_start, day_start;
    String year_end, month_end, day_end;
    float price_gas = 0;
    float price_elec = 0;
    float total_gas = 0;
    float total_elec = 0;
    String path;
    String GUI_path;
    String YY, MM, DD;

    UserBill(String user, String Year_start, String Month_start, String Day_start, String Year_end, String Month_end, String Day_end) {
        elec = user + "/elechistory.txt";
        gas = user + "/gashistory.txt";
        elec_price = user + "/ecosthistory.txt";
        gas_price = user + "/gcosthistory.txt";
        year_start = Year_start;
        year_end = Year_end;

        if (Integer.parseInt(Month_start) < 10) {
            month_start = '0' + Month_start;
        } else if (9 < Integer.parseInt(Month_start) && Integer.parseInt(Month_start) < 13) {
            month_start = Month_start;
        }

        if (Integer.parseInt(Day_start) < 10) {
            day_start = '0' + Day_start;
        } else if (9 < Integer.parseInt(Day_start) && Integer.parseInt(Day_start) < 32) {
            day_start = Day_start;
        }

        if (Integer.parseInt(Month_end) < 10) {
            month_end = '0' + Month_end;
        } else if (9 < Integer.parseInt(Month_end) && Integer.parseInt(Month_end) < 13) {
            month_end = Month_end;
        }

        if (Integer.parseInt(Day_end) < 10) {
            day_end = '0' + Day_end;
        } else if (9 < Integer.parseInt(Day_end) && Integer.parseInt(Day_end) < 32) {
            day_end = Day_end;
        }

        File file_gas = new File("price/gprice.txt");
        BufferedReader reader_gas = null;
        try {
            reader_gas = new BufferedReader(new FileReader(file_gas));
            String line_gas = null;
            while ((line_gas = reader_gas.readLine()) != null) {
                price_gas = Float.parseFloat(line_gas);
            }
            reader_gas.close();
        } catch (IOException e) {
        }

        File file_elec = new File("price/elecprice.txt");
        BufferedReader reader_elec = null;
        try {
            reader_elec = new BufferedReader(new FileReader(file_elec));
            String line_elec = null;
            while ((line_elec = reader_elec.readLine()) != null) {
                price_elec = Float.parseFloat(line_elec);
            }
            reader_elec.close();
        } catch (IOException e) {
        }

    }

    /**
     * generate gas and electricity history consumption
     *
     * @return history consumption in arraylist form
     */
    public ArrayList HistoryConsumption() {
        String gas_date, elec_date;
        String[] gas_split_date, elec_split_date;
        String[] number_gas;
        String line_gas;
        String[] number_elec;
        String line_elec;
        File file_gas = new File(gas);
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> List_gas = new ArrayList<String>();
            ArrayList<String> List_elec = new ArrayList<String>();
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
                DD = gas_split_date[2];
                if (Integer.valueOf(year_start).intValue() < Integer.valueOf(YY).intValue()
                        && Integer.valueOf(YY).intValue() < Integer.valueOf(year_end).intValue()) {
                    List_gas.add(line_gas);
                } else if (Integer.valueOf(year_start).intValue() == Integer.valueOf(YY).intValue()
                        && Integer.valueOf(YY).intValue() < Integer.valueOf(year_end).intValue()) {
                    if (Integer.valueOf(month_start).intValue() < Integer.valueOf(MM).intValue()) {
                        List_gas.add(line_gas);
                    } else if (Integer.valueOf(month_start).intValue() == Integer.valueOf(MM).intValue()) {
                        if (Integer.valueOf(DD).intValue() >= Integer.valueOf(day_start).intValue()) {
                            List_gas.add(line_gas);
                        }
                    }
                } else if (Integer.valueOf(year_start).intValue() < Integer.valueOf(YY).intValue()
                        && Integer.valueOf(YY).intValue() == Integer.valueOf(year_end).intValue()) {
                    if (Integer.valueOf(month_end).intValue() > Integer.valueOf(MM).intValue()) {
                        List_gas.add(line_gas);
                    } else if (Integer.valueOf(month_end).intValue() == Integer.valueOf(MM).intValue()) {
                        if (Integer.valueOf(DD).intValue() <= Integer.valueOf(day_end).intValue()) {
                            List_gas.add(line_gas);
                        }
                    }
                } else {
                    if (Integer.valueOf(month_start).intValue() < Integer.valueOf(MM).intValue()
                            && Integer.valueOf(MM).intValue() < Integer.valueOf(month_end).intValue()) {
                        List_gas.add(line_gas);
                    } else if (Integer.valueOf(month_start).intValue() == Integer.valueOf(MM).intValue()
                            && Integer.valueOf(MM).intValue() < Integer.valueOf(month_end).intValue()) {
                        if (Integer.valueOf(DD).intValue() >= Integer.valueOf(day_start).intValue()) {
                            List_gas.add(line_gas);
                        }
                    } else if (Integer.valueOf(month_start).intValue() < Integer.valueOf(MM).intValue()
                            && Integer.valueOf(MM).intValue() == Integer.valueOf(month_end).intValue()) {
                        if (Integer.valueOf(DD).intValue() <= Integer.valueOf(day_end).intValue()) {
                            List_gas.add(line_gas);
                        }
                    } else {
                        if (Integer.valueOf(DD).intValue() >= Integer.valueOf(day_start).intValue()
                                && Integer.valueOf(DD).intValue() <= Integer.valueOf(day_end).intValue()) {
                            List_gas.add(line_gas);
                        }
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
                DD = elec_split_date[2];

                if (Integer.valueOf(year_start).intValue() < Integer.valueOf(YY).intValue()
                        && Integer.valueOf(YY).intValue() < Integer.valueOf(year_end).intValue()) {
                    List_elec.add(line_elec);
                } else if (Integer.valueOf(year_start).intValue() == Integer.valueOf(YY).intValue()
                        && Integer.valueOf(YY).intValue() < Integer.valueOf(year_end).intValue()) {
                    if (Integer.valueOf(month_start).intValue() < Integer.valueOf(MM).intValue()) {
                        List_elec.add(line_elec);
                    } else if (Integer.valueOf(month_start).intValue() == Integer.valueOf(MM).intValue()) {
                        if (Integer.valueOf(DD).intValue() >= Integer.valueOf(day_start).intValue()) {
                            List_elec.add(line_elec);
                        }
                    }
                } else if (Integer.valueOf(year_start).intValue() < Integer.valueOf(YY).intValue()
                        && Integer.valueOf(YY).intValue() == Integer.valueOf(year_end).intValue()) {
                    if (Integer.valueOf(month_end).intValue() > Integer.valueOf(MM).intValue()) {
                        List_elec.add(line_elec);
                    } else if (Integer.valueOf(month_end).intValue() == Integer.valueOf(MM).intValue()) {
                        if (Integer.valueOf(DD).intValue() <= Integer.valueOf(day_end).intValue()) {
                            List_elec.add(line_elec);
                        }
                    }
                } else {
                    if (Integer.valueOf(month_start).intValue() < Integer.valueOf(MM).intValue()
                            && Integer.valueOf(MM).intValue() < Integer.valueOf(month_end).intValue()) {
                        List_elec.add(line_elec);
                    } else if (Integer.valueOf(month_start).intValue() == Integer.valueOf(MM).intValue()
                            && Integer.valueOf(MM).intValue() < Integer.valueOf(month_end).intValue()) {
                        if (Integer.valueOf(DD).intValue() >= Integer.valueOf(day_start).intValue()) {
                            List_elec.add(line_elec);
                        }
                    } else if (Integer.valueOf(month_start).intValue() < Integer.valueOf(MM).intValue()
                            && Integer.valueOf(MM).intValue() == Integer.valueOf(month_end).intValue()) {
                        if (Integer.valueOf(DD).intValue() <= Integer.valueOf(day_end).intValue()) {
                            List_elec.add(line_elec);
                        }
                    } else {
                        if (Integer.valueOf(DD).intValue() >= Integer.valueOf(day_start).intValue()
                                && Integer.valueOf(DD).intValue() <= Integer.valueOf(day_end).intValue()) {
                            List_elec.add(line_elec);
                        }
                    }
                }
            }
            reader_elec.close();
            if (List_gas.size() == 0 && List_elec.size() == 0) {
                arrayList = null;
            } else {
                arrayList.add("gas: ");
                arrayList.add(List_gas);
                arrayList.add(" elec: ");
                arrayList.add(List_elec);
            }
            return arrayList;
        } catch (IOException e) {
            return null;
        }

    }

    /**
     * generate gas and electricity history cost
     *
     * @return history cost in arraylist form
     */
    public ArrayList HistoryCost() {
        String gas_date, elec_date;
        String[] gas_split_date, elec_split_date;
        String[] number_gas;
        String line_gas;
        String[] number_elec;
        String line_elec;
        File file_gas = new File(gas_price);
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> List_gas = new ArrayList<String>();
            ArrayList<String> List_elec = new ArrayList<String>();
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
                DD = gas_split_date[2];
                if (Integer.valueOf(year_start).intValue() < Integer.valueOf(YY).intValue()
                        && Integer.valueOf(YY).intValue() < Integer.valueOf(year_end).intValue()) {
                    List_gas.add(line_gas);
                } else if (Integer.valueOf(year_start).intValue() == Integer.valueOf(YY).intValue()
                        && Integer.valueOf(YY).intValue() < Integer.valueOf(year_end).intValue()) {
                    if (Integer.valueOf(month_start).intValue() < Integer.valueOf(MM).intValue()) {
                        List_gas.add(line_gas);
                    } else if (Integer.valueOf(month_start).intValue() == Integer.valueOf(MM).intValue()) {
                        if (Integer.valueOf(DD).intValue() >= Integer.valueOf(day_start).intValue()) {
                            List_gas.add(line_gas);
                        }
                    }
                } else if (Integer.valueOf(year_start).intValue() < Integer.valueOf(YY).intValue()
                        && Integer.valueOf(YY).intValue() == Integer.valueOf(year_end).intValue()) {
                    if (Integer.valueOf(month_end).intValue() > Integer.valueOf(MM).intValue()) {
                        List_gas.add(line_gas);
                    } else if (Integer.valueOf(month_end).intValue() == Integer.valueOf(MM).intValue()) {
                        if (Integer.valueOf(DD).intValue() <= Integer.valueOf(day_end).intValue()) {
                            List_gas.add(line_gas);
                        }
                    }
                } else {
                    if (Integer.valueOf(month_start).intValue() < Integer.valueOf(MM).intValue()
                            && Integer.valueOf(MM).intValue() < Integer.valueOf(month_end).intValue()) {
                        List_gas.add(line_gas);
                    } else if (Integer.valueOf(month_start).intValue() == Integer.valueOf(MM).intValue()
                            && Integer.valueOf(MM).intValue() < Integer.valueOf(month_end).intValue()) {
                        if (Integer.valueOf(DD).intValue() >= Integer.valueOf(day_start).intValue()) {
                            List_gas.add(line_gas);
                        }
                    } else if (Integer.valueOf(month_start).intValue() < Integer.valueOf(MM).intValue()
                            && Integer.valueOf(MM).intValue() == Integer.valueOf(month_end).intValue()) {
                        if (Integer.valueOf(DD).intValue() <= Integer.valueOf(day_end).intValue()) {
                            List_gas.add(line_gas);
                        }
                    } else {
                        if (Integer.valueOf(DD).intValue() >= Integer.valueOf(day_start).intValue()
                                && Integer.valueOf(DD).intValue() <= Integer.valueOf(day_end).intValue()) {
                            List_gas.add(line_gas);
                        }
                    }
                }
            }

            reader_gas.close();

            File file_elec = new File(elec_price);
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
                DD = elec_split_date[2];
                if (Integer.valueOf(year_start).intValue() < Integer.valueOf(YY).intValue()
                        && Integer.valueOf(YY).intValue() < Integer.valueOf(year_end).intValue()) {
                    List_elec.add(line_elec);
                } else if (Integer.valueOf(year_start).intValue() == Integer.valueOf(YY).intValue()
                        && Integer.valueOf(YY).intValue() < Integer.valueOf(year_end).intValue()) {
                    if (Integer.valueOf(month_start).intValue() < Integer.valueOf(MM).intValue()) {
                        List_elec.add(line_elec);
                    } else if (Integer.valueOf(month_start).intValue() == Integer.valueOf(MM).intValue()) {
                        if (Integer.valueOf(DD).intValue() >= Integer.valueOf(day_start).intValue()) {
                            List_elec.add(line_elec);
                        }
                    }
                } else if (Integer.valueOf(year_start).intValue() < Integer.valueOf(YY).intValue()
                        && Integer.valueOf(YY).intValue() == Integer.valueOf(year_end).intValue()) {
                    if (Integer.valueOf(month_end).intValue() > Integer.valueOf(MM).intValue()) {
                        List_elec.add(line_elec);
                    } else if (Integer.valueOf(month_end).intValue() == Integer.valueOf(MM).intValue()) {
                        if (Integer.valueOf(DD).intValue() <= Integer.valueOf(day_end).intValue()) {
                            List_elec.add(line_elec);
                        }
                    }
                } else {
                    if (Integer.valueOf(month_start).intValue() < Integer.valueOf(MM).intValue()
                            && Integer.valueOf(MM).intValue() < Integer.valueOf(month_end).intValue()) {
                        List_elec.add(line_elec);
                    } else if (Integer.valueOf(month_start).intValue() == Integer.valueOf(MM).intValue()
                            && Integer.valueOf(MM).intValue() < Integer.valueOf(month_end).intValue()) {
                        if (Integer.valueOf(DD).intValue() >= Integer.valueOf(day_start).intValue()) {
                            List_elec.add(line_elec);
                        }
                    } else if (Integer.valueOf(month_start).intValue() < Integer.valueOf(MM).intValue()
                            && Integer.valueOf(MM).intValue() == Integer.valueOf(month_end).intValue()) {
                        if (Integer.valueOf(DD).intValue() <= Integer.valueOf(day_end).intValue()) {
                            List_elec.add(line_elec);
                        }
                    } else {
                        if (Integer.valueOf(DD).intValue() >= Integer.valueOf(day_start).intValue()
                                && Integer.valueOf(DD).intValue() <= Integer.valueOf(day_end).intValue()) {
                            List_elec.add(line_elec);
                        }
                    }
                }
            }
            reader_elec.close();
            if (List_gas.size() == 0 && List_elec.size() == 0) {
                arrayList = null;
            } else {
                arrayList.add("gas: ");
                arrayList.add(List_gas);
                arrayList.add(" elec: ");
                arrayList.add(List_elec);
            }
            return arrayList;
        } catch (IOException e) {
            return null;
        }

    }

}
