public class County {

    private String name;
    private int fips;
    private String Unemployment; //high employment is True, high unemployment is False
    private String incomeLevel; //high income level is True, low income level is False
    private double DEMvotesPercent;
    private double REPvotesPercent;

    public String getUnemployment() {
        return Unemployment;
    }

    public String getIncomeLevel() {
        return incomeLevel;
    }

    public County(String name, int fips) {
        this.name = name;
        this.fips = fips;
    }

    public void setEmployment(String Unemployment) {
        this.Unemployment = Unemployment;
    }


    public void setIncomeLevel(String incomeLevel) {
        this.incomeLevel = incomeLevel;
    }

    public double getDEMvotesPercent() {
        return DEMvotesPercent;
    }

    public void setDEMvotesPercent(double DEMvotesPercent) {
        this.DEMvotesPercent = DEMvotesPercent;
    }

    public double getREPvotesPercent() {
        return REPvotesPercent;
    }

    public void setREPvotesPercent(double REPvotesPercent) {
        this.REPvotesPercent = REPvotesPercent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFips() {
        return fips;
    }

    public void setFips(int fips) {
        this.fips = fips;
    }


    public void setPoliticalInfo(int votes_dem, int votes_gop, int total_votes) {
        this.DEMvotesPercent = (double)votes_dem/(double)total_votes;
        this.REPvotesPercent = (double)votes_gop/(double)total_votes;
    }

    @Override
    public String toString() {
        String line = this.getFips() + "," + this.getUnemployment() + "," + this.getIncomeLevel()
                + "," + this.getDEMvotesPercent() + "," + this.getREPvotesPercent() + "\n";
        return line;
    }
}
