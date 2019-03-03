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
        String[] lineArr;
        for (int i = 1; i < lines.length; i++) {
            auxStr = removeBadChars(lines[i]);
            lineArr = auxStr.split(",");
            results.add(makeElectionResultObj(lineArr));
            }
        return results;
    }

    private static ElectionResult makeElectionResultObj(String[] lineArr) {
        int votes_dem = (int)Double.parseDouble(lineArr[1]);
        int votes_gop = (int)Double.parseDouble(lineArr[2]);
        int total_votes = (int)Double.parseDouble(lineArr[3]);
        double per_dem = Double.parseDouble(lineArr[4]) / 100;
        double per_gop = Double.parseDouble(lineArr[5]) / 100;
        int diff = Integer.parseInt(lineArr[6]);
        double per_point_diff = Double.parseDouble(lineArr[7]) / 100;;
        String state_abbr = lineArr[8];
        String county_name = lineArr[9];
        int combined_fips = Integer.parseInt(lineArr[10]);

        return new ElectionResult(votes_dem, votes_gop, total_votes, per_dem, per_gop,
                diff, per_point_diff, state_abbr,county_name,combined_fips);
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
        int index2 = l.indexOf("\"", index1 + 1);
        String noMiddleCommas = l.substring(index1 + 1, index2).replace(",", "");

        return l.substring(0, index1) + noMiddleCommas + l.substring(index2 + 1);
    }

}
