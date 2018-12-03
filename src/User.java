import java.util.Calendar;

/**
 * @author group 66
 * This is the entity class of user
 */
public class User {
    private int gasBudget;
    private int elecBudget;
    private int electricity;
    private int gas;
    private float electricityCost;
    private float gasCost;
    private float gPrice;
    private float ePrice;
    private String userName;
    private String budgetGasFileName;
    private String budgetElecFileName;
    private String electricityFileName;
    private String electricityCostFileName;
    private String gasFileName;
    private String gasCostFileName;
    private String gasHistoryFileName;
    private String elecHistoryFileName;
    private String gCostHistoryFileName;
    private String eCostHistoryFileName;
    private String gasPriceFileName = "price/gasprice.txt";
    private String elecPriceFileName = "price/elecprice.txt";
//    private File budgetFile;
//    private File gasFile;
//    private File gpriceFile;
//    private File gcostFile;
//    private File elecFile;
//    private File epriceFile;
//    private File ecostFile;
//    private File gasHistoryFile;
//    private File elecHistoryFile;
//    private File gCostHistoryFile;
//    private File eCostHistoryFile;

    public User(String userName) {
        this.userName = userName;
        this.budgetGasFileName = userName + "/budgetGas.txt";
        this.budgetElecFileName = userName + "/budgetElec.txt";
        this.electricityCostFileName = userName + "/eleccost.txt";
        this.electricityFileName = userName + "/electricity.txt";
        this.gasFileName = userName + "/gas.txt";
        this.gasCostFileName = userName + "/gascost.txt";
        this.gasHistoryFileName = userName + "/gashistory.txt";
        this.elecHistoryFileName = userName + "/elechistory.txt";
        this.gCostHistoryFileName = userName + "/gcosthistory.txt";
        this.eCostHistoryFileName = userName + "/ecosthistory.txt";
//        this.budgetFile = new File(budgetFileName);
//        this.gasFile = new File(gasFileName);
//        this.elecFile = new File(electricityFileName);
//        this.gpriceFile = new File(gasPriceFileName);
//        this.gcostFile = new File(gasCostFileName);
//        this.ecostFile = new File(electricityCostFileName);
//        this.epriceFile = new File(elecPriceFileName);
        this.gasBudget = getGasBudget();
        this.elecBudget = getElecBudget();
        this.electricity = getElectricityFromFile();
        this.gas = getGasFromFile();
        this.electricityCost = getElectricityCostFromFile();
        this.gasCost = getGasCostFromFile();
    }

    public int getGasBudget() {
        gasBudget = Integer.parseInt(FileFunction.readFile(budgetGasFileName));
        return gasBudget;
    }

    public void setGasBudget(int budget) {
        this.gasBudget = budget;
        String budgetString = Integer.toString(budget);
        FileFunction.writeFile(budgetGasFileName, budgetString);
    }

    public int getElecBudget() {
        elecBudget = Integer.parseInt(FileFunction.readFile(budgetElecFileName));
        return elecBudget;
    }

    public void setElecBudget(int budget) {
        this.elecBudget = budget;
        String budgetString = Integer.toString(budget);
        FileFunction.writeFile(budgetElecFileName, budgetString);
    }

    public int getElectricityFromFile() {
        electricity = Integer.parseInt(FileFunction.readFile(electricityFileName));
        return electricity;
    }

    public int getElectricity() {
        return electricity;
    }

    public void setElectricity(int electricity) {
        this.electricity = electricity;
        String electricityString = Integer.toString(electricity);
        FileFunction.writeFile(electricityFileName, electricityString);
    }

    public int getGasFromFile() {
        gas = Integer.parseInt(FileFunction.readFile(gasFileName));
        return gas;
    }

    public int getGas() {
        return gas;
    }

    public void setGas(int gas) {
        this.gas = gas;
        String gasString = Integer.toString(gas);
        FileFunction.writeFile(gasFileName, gasString);
    }

    public float getElectricityCostFromFile() {
        electricityCost = Float.parseFloat(FileFunction.readFile(electricityCostFileName));
        return electricityCost;
    }

    public float getElectricityCost() {
        return electricityCost;
    }

    public void setElectricityCost(float elecCost) {
        this.electricityCost = elecCost;
        String electricityCostString = Float.toString(electricityCost);
        FileFunction.writeFile(electricityCostFileName, electricityCostString);
    }

    public float getElectricityPrice() {
        ePrice = Float.parseFloat(FileFunction.readFile(elecPriceFileName));
        return ePrice;
    }

    public float getGasCostFromFile() {
        gasCost = Float.parseFloat(FileFunction.readFile(gasCostFileName));
        return gasCost;
    }

    public float getGasCost() {
        return gasCost;
    }

    public void setGasCost(float gasCost) {
        this.gasCost = gasCost;
        String gasCostString = Float.toString(gasCost);
        FileFunction.writeFile(gasCostFileName, gasCostString);
    }

    public float getGasPrice() {
        gPrice = Float.parseFloat(FileFunction.readFile(gasPriceFileName));
        return gPrice;
    }

    /**
     * Count gas consumption
     *
     * @return consumption in string
     */
    public String countGasConsumption() {
        int random = (int) (Math.random() * 10);
        int gasConsumption = getGasFromFile();
        gasConsumption += random;
        setGas(gasConsumption);

        return Integer.toString(gasConsumption);
    }

    /**
     * Count electricity consumption
     *
     * @return consumption in string
     */
    public String countElectricityConsumption() {
        int random = (int) (Math.random() * 10);
        int electricityConsumption = getElectricityFromFile();
        electricityConsumption += random;
        //System.out.println("electricity cost is " + electricityConsumption);
        setElectricity(electricityConsumption);

        return Integer.toString(electricityConsumption);
    }

    /**
     * Count gas cost
     *
     * @return cost in string
     */
    public String countGasCost() {
        float gPrice = getGasPrice();
        float gCost = gPrice * getGasFromFile();
        setGasCost(gCost);

        return Float.toString(gCost);
    }

    /**
     * Count electricity cost
     *
     * @return cost in string
     */
    public String countElectricityCost() {
        float ePrice = getElectricityPrice();
        float eCost = ePrice * getElectricityFromFile();
        setElectricityCost(eCost);

        return Float.toString(eCost);
    }

    /**
     * Write gas history
     */
    public void writeGasHistory() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        String month;
        int monthNum;
        if ((monthNum = c.get(Calendar.MONTH) + 1) < 10) {
            month = "0" + monthNum;
        } else {
            month = "" + monthNum;
        }
        String date = "" + c.get(Calendar.YEAR) + "-" + month + "-" + c.get(Calendar.DAY_OF_MONTH);
        String data1 = FileFunction.readFile(gasFileName);
        String gas = data1 + "," + date;
        String data2 = FileFunction.readFile(gasCostFileName);
        String gasCost = data2 + "," + date;
        FileFunction.chaseWrite(gasHistoryFileName, gas);
        FileFunction.chaseWrite(gCostHistoryFileName, gasCost);
    }

    /**
     * Write electricity history
     */
    public void writeElecHistory() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        String month;
        int monthNum;
        if ((monthNum = c.get(Calendar.MONTH) + 1) < 10) {
            month = "0" + monthNum;
        } else {
            month = "" + monthNum;
        }
        String date = "" + c.get(Calendar.YEAR) + "-" + month + "-" + c.get(Calendar.DAY_OF_MONTH);
        String data1 = FileFunction.readFile(electricityFileName);
        String electricity = data1 + "," + date;
        String data2 = FileFunction.readFile(electricityCostFileName);
        String electricityCost = data2 + "," + date;
        FileFunction.chaseWrite(elecHistoryFileName, electricity);
        FileFunction.chaseWrite(eCostHistoryFileName, electricityCost);
    }
}