public class Main {

    public static void main(String[] args) {

        Data data = Utils.parseAllData();

        Utils.saveData(data, "data.csv");
    }

}
