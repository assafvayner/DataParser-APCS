public class County {

    private String name;
    private int fips;
    private boolean employment; //high employment is True, high unemployment is False
    private boolean incomeLevel; //high income level is True, low income level is False
    private double DEMvotesPercent;
    private double REPvotesPercent;


    public County(String name, int fips) {
        this.name = name;
        this.fips = fips;
    }

    public boolean isEmployment() {
        return employment;
    }

    public void setEmployment(boolean employment) {
        this.employment = employment;
    }

    public boolean isIncomeLevel() {
        return incomeLevel;
    }

    public void setIncomeLevel(boolean incomeLevel) {
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
}
