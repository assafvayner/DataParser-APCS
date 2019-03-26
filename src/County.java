public class County {

    private String name;
    private int fips;
    private String Unemployment;
    private String incomeLevel;
    private double DEMvotesPercent;
    private double REPvotesPercent;
    private double latitude;

    public void setUnemployment(String unemployment) {
        Unemployment = unemployment;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private double longitude;

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
        return fips + "," + Unemployment + "," + incomeLevel + "," + DEMvotesPercent + ","
                + REPvotesPercent + "," + latitude + "," + longitude+ "\n";
    }

    public void setLocationData(double lat, double lon) {
        setLatitude(lat);
        setLongitude(lon);
    }
}
