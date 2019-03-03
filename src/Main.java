import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String str = Utils.readFileAsString("data/Education.csv");

        ArrayList<ElectionResult> results = Utils.parse2016PresidentialResults();
        System.out.println(results);
    }

}
