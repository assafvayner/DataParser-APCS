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

        String educationData = readFileAsString("data/Education.csv");
        String[] educationDataLines = educationData.split("\n");

        InsertEducationData(data, educationDataLines);

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


                String county_name = lineArr[2];
                county_name = county_name.substring(0, county_name.length() - 3);

                if (data.getStateByName(state_abbr).contains(county_name)) {

                    int employedLaborForce = Integer.parseInt(lineArr[44]);
                    int unemployedLabotForce = Integer.parseInt(lineArr[44]);
                    double unemployedPercent = Double.parseDouble(lineArr[46]);
                    int totalLaborForce = employedLaborForce + unemployedLabotForce;


                    data.getStateByName(state_abbr).getCountyByName(county_name).setEmploy2016(new Employment2016(totalLaborForce, employedLaborForce, unemployedLabotForce, unemployedPercent));
                }
            }
        }
    }

    private static void InsertEducationData(Data data, String[] educationDataLines) {
        String auxStr;
        String[] lineArr;
        for (int i = 5; i < educationDataLines.length; i++) {
            auxStr = removeBadChars(educationDataLines[i]);//make method
            lineArr = auxStr.split(",");

            String state_abbr = lineArr[1];

            if (data.contains(state_abbr)) {
                String county_name = lineArr[2];

                if (data.getStateByName(state_abbr).contains(county_name)) {
                    double noHighSchool = Double.parseDouble(lineArr[38]);
                    double onlyHighSchool = Double.parseDouble(lineArr[39]);
                    double someCollege = Double.parseDouble(lineArr[40]);
                    double bachelorsOrMore = Double.parseDouble(lineArr[41]);

                    data.getStateByName(state_abbr).getCountyByName(county_name).setEduc2016(new Education2016(noHighSchool, onlyHighSchool, someCollege, bachelorsOrMore));
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
            int votes_dem = (int) Double.parseDouble(lineArr[1]);
            int votes_gop = (int) Double.parseDouble(lineArr[2]);
            int total_votes = (int) Double.parseDouble(lineArr[3]);

            data.getStateByName(state_abbr).add(new County(county_name, combined_fips));
            data.getStateByName(state_abbr).getCountyByName(county_name).setVote2016(new Election2016(votes_dem, votes_gop, total_votes));
        }
    }

//    public static ArrayList<ElectionResult> parse2016PresidentialResults() {
//        ArrayList<ElectionResult> results = new ArrayList<>();
//
//        String dataFromCSV = readFileAsString("data/2016_Presidential_Results.csv");
//
//        String[] lines = dataFromCSV.split("\n");
//
//        String auxStr;
//        String[] lineArr;
//        for (int i = 1; i < lines.length; i++) {
//            auxStr = removeBadChars(lines[i]);
//            lineArr = auxStr.split(",");
//            results.add(makeElectionResultObj(lineArr));
//        }
//        return results;
//    }
//
//    private static ElectionResult makeElectionResultObj(String[] lineArr) {
//        int votes_dem = (int) Double.parseDouble(lineArr[1]);
//        int votes_gop = (int) Double.parseDouble(lineArr[2]);
//        int total_votes = (int) Double.parseDouble(lineArr[3]);
//        double per_dem = Double.parseDouble(lineArr[4]) / 100;
//        double per_gop = Double.parseDouble(lineArr[5]) / 100;
//        int diff = Integer.parseInt(lineArr[6]);
//        double per_point_diff = Double.parseDouble(lineArr[7]) / 100;
//        ;
//        String state_abbr = lineArr[8];
//        String county_name = lineArr[9];
//        int combined_fips = Integer.parseInt(lineArr[10]);
//
//        return new ElectionResult(votes_dem, votes_gop, total_votes, per_dem, per_gop,
//                diff, per_point_diff, state_abbr, county_name, combined_fips);
//    }

}
