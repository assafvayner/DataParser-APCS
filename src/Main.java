public class Main {

    public static void main(String[] args) {

        Data data = Utils.parseAllData();

        String str = Utils.stringifyTheData(data);

        Utils.writeDataToFile("data.csv", str);
    }

}
