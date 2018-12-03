import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;

/**
 * This is a class consists of method used to do some dynamic tasks
 *
 * @author group 66
 */
public class UserMonitor {
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
    private static final long PERIOD_MONTH = 31 * 24 * 60 * 60 * 1000;
    Timer elecTimer;
    Timer gasTimer;
    Timer historyTimer;
    Timer alarmTimer;
    Timer billTimer;
    User user;
    //MeterUI ui;

    public UserMonitor(User user) {
        this.user = user;
        //this.ui = ui;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * This function is used to create timers for meters and historical recording tasks
     */
    public void startMonitor() {
        elecTimer = new Timer();
        elecTimer.schedule(new ElecTask(user), 0, 2000);

        gasTimer = new Timer();
        gasTimer.schedule(new GasTask(user), 0, 2000);

        historyTimer = startHistoryTaskDay(0, 0, 0);

        billTimer = startHistoryTaskDate();

//        alarmTimer = new Timer();
//        alarmTimer.schedule(new AlarmTask(user), 0, 5000);


//        while (true) {
//            int in = 0;
//            try {
//                in = System.in.read();
//            } catch (IOException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//            }
//            if (in == 's') {
//                closeMonitor();
//                break;
//            }
//        }
    }

    /**
     * This function is used to close the timers
     */
    public void closeMonitor() {
        elecTimer.cancel();
        gasTimer.cancel();
        historyTimer.cancel();
        //alarmTimer.cancel();
        billTimer.cancel();
    }

    /**
     * This function is used to create a timer starting at a specific moment of a day
     *
     * @param hourOfDay what hour
     * @param minute    what minute
     * @param second    what second
     * @return created timer
     */
    public Timer startHistoryTaskDay(int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        Date date = calendar.getTime(); //The first time to operate

        if (date.before(new Date())) {
            date = this.addDay(date, 1);
            //System.out.println(date);
        }

        Timer timer = new Timer();

        HistoryTask historyTimer = new HistoryTask(user);

        timer.schedule(historyTimer, date, PERIOD_DAY);

        return timer;
    }

    /**
     * This function is used to create a timer starting in specific date of every month
     *
     * @return created timer
     */
    public Timer startHistoryTaskDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month++;

        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date date = calendar.getTime();
        //System.out.println(date);

        Timer timer = new Timer();

        BillTask billTimer = new BillTask(user, year, month);

        timer.schedule(billTimer, date, PERIOD_DAY);

        return timer;
    }

    /**
     * add some day to date
     *
     * @param date date
     * @param num  number of day
     * @return modified date
     */
    public Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }

    /**
     * The timer task for electricity
     */
    class ElecTask extends TimerTask {
        public User user;
        private String electricity;
        private String electricityCost;

        public ElecTask(User user) {
            this.user = user;
        }

        public void run() {
            synchronized (user) {
                electricity = user.countElectricityConsumption();
                //ui.eleConsumptionLabel.setText("" + electricity);
                electricityCost = user.countElectricityCost();
                //ui.eleCostLabel.setText("" + electricityCost);
            }
        }
    }

    /**
     * The timer task for gas
     */
    class GasTask extends TimerTask {
        public User user;
        private String gas;
        private String gasCost;

        public GasTask(User user) {
            this.user = user;
        }

        public void run() {
            synchronized (user) {
                gas = user.countGasConsumption();
                //ui.gasConsumptionLabel.setText("" + gas);
                gasCost = user.countGasCost();
                //ui.gasCostLabel.setText("" + gasCost);
            }
        }
    }

    /**
     * The timer task for writing historic information
     */
    class HistoryTask extends TimerTask {
        public User user;

        public HistoryTask(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            //System.out.println("Writing historic information");
            synchronized (user) {
                user.writeGasHistory();
                user.writeElecHistory();

                //set to 0
                user.setGas(0);
                user.setGasCost(0);
                user.setElectricity(0);
                user.setElectricityCost(0);
            }
        }
    }

    /**
     * The timer task for writing bills
     */
    class BillTask extends TimerTask {
        public User user;
        public int year;
        public int month;

        public BillTask(User user, int year, int month) {
            this.user = user;
            this.year = year;
            this.month = month;
        }

        @Override
        public void run() {
            System.out.println("Generating bill...");
            GenerateBill generateBill = new GenerateBill(GlobalVar.userName, "" + year, "" + month);
            generateBill.billCaculate();
        }
    }


}