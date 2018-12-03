public class SetTariff {
    String path = "price/";

    public boolean ChangeTariff(String name, String price) {
        if (name.equals("elecprice")) {
            ProviderEntity provider = new ProviderEntity();
            provider.setElecprice(Float.parseFloat(price));
            return true;
        } else if (name.equals("gasprice")) {
            ProviderEntity provider = new ProviderEntity();
            provider.setGasprice(Float.parseFloat(price));
            return true;
        } else {
            return false;
        }
    }
}