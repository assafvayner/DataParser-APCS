import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    final private static int highIncome = 90000;
    final private static int lowIncome = 60000;
    final private static double highUnemployment = 6;

    final private static double MAJORITY  = 0.5;


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
        for (int i = 9; i < unemploymentDataLines.length; i++) {
            auxStr = removeBadChars(unemploymentDataLines[i]);//make method
            lineArr = auxStr.split(",");

            String state_abbr = lineArr[1].trim();

            if (data.contains(state_abbr)) {
                int FIPS = Integer.parseInt(lineArr[0].trim());
                if(data.getStateByName(state_abbr).contains(FIPS)){
                    double unemplymentRate = Double.parseDouble(lineArr[45].trim());
                    Integer income = Integer.parseInt(lineArr[50].trim());

                    County c = data.getStateByName(state_abbr).getCounyByFIPS(FIPS);

                    c.setEmployment(chooseUnemploymentLevel(unemplymentRate));
                    c.setIncomeLevel(chooseIncomeLevel(income));
                }
            }
        }
    }

    private static String chooseUnemploymentLevel(double unemplymentRate) {
        if(unemplymentRate >= highUnemployment){
            return "HIGH";
        }else{
            return "LOW";
        }
    }

    private static String chooseIncomeLevel(Integer income) {
        if(income < lowIncome){
            return "LOW";
        }else if(income > highIncome){
            return "HIGH";
        }else{
            return "NORMAL";
        }
    }

    private static String removeBadChars(String line) {
        line = line.replace("%", "");
        line = line.replace("$", "");

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

            String state_abbr = lineArr[8].trim();

            data.add(new State(state_abbr));

            String county_name = lineArr[9].trim();
            int combined_fips = Integer.parseInt(lineArr[10].trim());
            int total_votes = (int) Double.parseDouble(lineArr[3].trim());
            int votes_dem = (int) Double.parseDouble(lineArr[1].trim());
            int votes_gop = (int) Double.parseDouble(lineArr[2].trim());

            County c = new County(county_name, combined_fips);
            c.setPoliticalInfo(votes_dem,votes_gop,total_votes);
            data.getStateByName(state_abbr).add(c);
        }
    }

    public static void getResults(Data data){
        highUnemploymentLowIncome(data);
        LowUnemploymentHighIncome(data);
    }

    private static void LowUnemploymentHighIncome(Data data) {
        int numLUHI = 0;
        int numLUHIvoteREP = 0;
        for (State s: data.getStates()) {
            for (County c : s.getCounties()) {
                if (c.hasUnemploymentInfo()) {
                    if (c.getIncomeLevel().equals("HIGH") && c.getUnemployment().equals("LOW")) {
                        numLUHI++;
                        if (c.getDEMvotesPercent() < c.getREPvotesPercent()) {
                            numLUHIvoteREP++;
                        }
                    }
                }
            }
        }
        double proportion = ((double)numLUHIvoteREP)/numLUHI;
        if(proportion > MAJORITY){
            System.out.println(proportion*100 +
                    "% Counties with LOW unemployment and HIGH income vote for Republicans");
        }
        else{
            System.out.println((1-proportion)*100 +
                    "% Counties with LOW unemployment and HIGH income vote for Democrats");
        }
    }

    private static void highUnemploymentLowIncome(Data data) {
        int numHULI = 0;
        int numHULIvoteDEM = 0;
        for (State s: data.getStates()) {
            for (County c : s.getCounties()) {
                if (c.hasUnemploymentInfo()) {
                    if (c.getIncomeLevel().equals("LOW") && c.getUnemployment().equals("HIGH")) {
                        numHULI++;
                        if (c.getDEMvotesPercent() > c.getREPvotesPercent()) numHULIvoteDEM++;
                    }
                }
            }
        }
        double proportion = ((double)numHULIvoteDEM)/numHULI;
        if(proportion > MAJORITY){
            System.out.println(proportion*100 +
                    "% Counties with HIGH unemployment and LOW income vote for Democrats");
        }
        else{
            System.out.println((1-proportion)*100 +
                    "% Counties with HIGH unemployment and LOW income vote for Republicans");
        }
    }

}
