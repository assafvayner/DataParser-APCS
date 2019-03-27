import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utils {

    final private static int highIncome = 90000;
    final private static int lowIncome = 60000;
    final private static double highUnemployment = 6;


    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line).append("\n");
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

        String locationData = readFileAsString("data/location.csv");
        String[] locationDataLines = locationData.split("\n");

        InsertLocationData(data, locationDataLines);

        return data;
    }

    private static void InsertLocationData(Data data, String[] locationDataLines) {
        for (int i = 1; i < locationDataLines.length; i++) {
            String clean = removeBadChars(locationDataLines[i]);
            String[] line = clean.split(",");

            String state_abbr = line[2];
            int FIPS = Integer.parseInt(line[4]);

            for (State s : data.getStates()) {
                if (state_abbr.equals(s.getName())) {
                    for (County c : s.getCounties()) {
                        if (c.getFips() == FIPS) {
                            double lat = Double.parseDouble(line[6]);
                            double lon = Double.parseDouble(line[7]);
                            c.setLocationData(lat, lon);
                            break;
                        }
                    }
                    break;
                }
            }

        }

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
                if (data.getStateByName(state_abbr).contains(FIPS)) {
                    double unemployment = Double.parseDouble(lineArr[45].trim());
                    Integer income = Integer.parseInt(lineArr[50].trim());

                    County c = data.getStateByName(state_abbr).getCountyByFIPS(FIPS);

                    c.setUnemployment(chooseUnemploymentLevel(unemployment));
                    c.setIncomeLevel(chooseIncomeLevel(income));
                }
            }
        }
    }

    private static String chooseUnemploymentLevel(double unemploymentRate) {
        if (unemploymentRate >= highUnemployment) {
            return "HIGH";
        } else {
            return "LOW";
        }
    }

    private static String chooseIncomeLevel(int income) {
        if (income < lowIncome) {
            return "LOW";
        } else if (income > highIncome) {
            return "HIGH";
        } else {
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
            c.setPoliticalInfo(votes_dem, votes_gop, total_votes);
            data.getStateByName(state_abbr).add(c);
        }
    }

    public static String stringifyTheData(Data data) {
        StringBuilder str = new StringBuilder("state,FIPS,unemployment,incomeLevel,votes Dem percent, votes Rep percent,latitude,longitude" + "\n");
        for (State s : data.getStates()) {
            for (County c : s.getCounties()) {
                if (hasAllVals(c)) {
                    str.append(s.getName()).append(",").append(c.toString());
                }
            }
        }
        return str.toString();
    }

    public static void writeDataToFile(String filePath, String dataStr) {
        File outFile = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write(dataStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean hasAllVals(County c) {
        return c.getIncomeLevel() != null &&
                c.getUnemployment() != null &&
                c.getREPvotesPercent() != 0 &&
                c.getDEMvotesPercent() != 0;
    }

    public static void saveData(Data data, String filepath) {
        writeDataToFile(filepath, stringifyTheData(data));
    }

}