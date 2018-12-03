import java.util.ArrayList;

public class Qintest {
    public static void main(String[] args) {
        UserBill bill = new UserBill("22222", "2016", "1", "1", "2020", "1", "1");
        ArrayList list = bill.HistoryConsumption();
        //System.out.println(list.toString());
        System.out.println(list.get(1).toString());

    }
}
