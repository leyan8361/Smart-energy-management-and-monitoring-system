public class ProviderBill {
    String month, year, path, user;
    float total_gas = 0;
    float total_elec = 0;
    float price_gas, price_elec;
    String[] number_bill;

    ProviderBill(String User, String Year, String Month) {
        path = User + "/" + "MonthlyConsumption.txt";
        user = User;
        year = Year;
        if (Integer.parseInt(Month) < 10) {
            month = "0" + Month;
        } else if (9 < Integer.parseInt(Month) && Integer.parseInt(Month) < 13) {
            month = Month;
        }

        ProviderEntity provider = new ProviderEntity();
        price_gas = provider.getGasprice();
        price_elec = provider.getElecprice();
        if (provider.getGasmonth(User, Year, month) != -1 && provider.getElecmonth(User, Year, month) != -1) {
            total_gas = total_gas + provider.getGasmonth(User, Year, month) * price_gas;
            total_elec = total_elec + provider.getElecmonth(User, Year, month) * price_elec;
        } else {
            total_gas = 0;
            total_elec = 0;
        }
    }

    public void SendBill() {
        String sendpath = user + "/" + "BillMonthly.txt";
        FileFunction.chaseWrite(sendpath, year + "-" + month + "-" + "gas-" + String.valueOf(total_gas) + "-elec-" + String.valueOf(total_elec));
    }
}
