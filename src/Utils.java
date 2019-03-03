import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Utils {

    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }


    public static ArrayList<ElectionResult> parse2016PresidentialResults(){
        ArrayList<ElectionResult> results = new ArrayList<>();

        String dataFromCSV = readFileAsString("data/2016_Presidential_Results.csv");

        String[] lines = dataFromCSV.split("\n");

        String auxStr;
        String[] line;
        for (String l : lines) {
            auxStr = removeBadChars(l);
            System.out.println(auxStr);

            line = auxStr.split(",");
            ///System.out.println(Arrays.toString(line));
            //int votes_dem = Integer.parseInt(line[1]);
            //int votes_gop = Integer.parseInt(line[2]);
        }
        return results;
    }

    private static String removeBadChars(String l) {
        int indexPercent = l.indexOf("%");
        if(indexPercent != -1){
            l = l.substring(0, indexPercent) + l.substring(indexPercent + 1);
        }

        int index1 = l.indexOf("\"");
        if(index1 == -1){
            return l;
        }
        int indexComma = l.indexOf(",", index1);
        int index2 = l.indexOf("\"", index1 + 1);

        return l.substring(0, index1) + l.substring(index1 + 1, indexComma) +
                l.substring(indexComma + 1, index2) + l.substring(index2 + 1);
    }

}
