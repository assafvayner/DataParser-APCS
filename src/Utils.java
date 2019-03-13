import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    public static Data parseAllData() {
        Data data = new Data();

        String electionData = readFileAsString("data/2016_Presidential_Results.csv");
        String[] electionDataLines = electionData.split("\n");

        initializeCountiesAndInsertElectionData(data, electionDataLines);

        String unemploymentData = readFileAsString("data/Unemployment.csv");
        String[] unemploymentDataLines = unemploymentData.split("\n");

        InsertUnemploymentData(data, unemploymentDataLines);

        return data;
    }

    private static void InsertUnemploymentData(Data data, String[] unemploymentDataLines) {
        String auxStr;
        String[] lineArr;
        for (int i = 5; i < unemploymentDataLines.length; i++) {
            auxStr = removeBadChars(unemploymentDataLines[i]);//make method
            lineArr = auxStr.split(",");

            String state_abbr = lineArr[1];

            if (data.contains(state_abbr)) {
                int FIPS = Integer.parseInt(lineArr[0]);
                if(data.getStateByName(state_abbr).contains(FIPS)){

                    //insert unemployment boolean value into counties
                }
            }
        }
    }
    private static String removeBadChars(String line) {
        if (line.contains("%")) {
            line = line.replace("%", "");
        }

        while (line.contains("\"")) {
            int index1 = line.indexOf("\"");
            int index2 = line.indexOf("\"", index1 + 1);
            String noMiddleCommas = line.substring(index1 + 1, index2).replace(",", "");
            line = line.substring(0, index1) + noMiddleCommas + line.substring(index2 + 1);
        }
        return line;
    }

    private static void initializeCountiesAndInsertElectionData(Data data, String[] electionDataLines) {
        String auxStr;
        String[] lineArr;
        for (int i = 1; i < electionDataLines.length; i++) {
            auxStr = removeBadChars(electionDataLines[i]);
            lineArr = auxStr.split(",");

            String state_abbr = lineArr[8];

            if (!data.contains(state_abbr)) {
                data.add(new State(state_abbr));
            }

            String county_name = lineArr[9];
            int combined_fips = Integer.parseInt(lineArr[10]);
            int total_votes = (int) Double.parseDouble(lineArr[3]);
            int votes_dem = (int) Double.parseDouble(lineArr[1]);
            int votes_gop = (int) Double.parseDouble(lineArr[2]);

            County c = new County(county_name, combined_fips);
            c.setPoliticalInfo(votes_dem,votes_gop,total_votes);
            data.getStateByName(state_abbr).add(c);
        }
    }

}
