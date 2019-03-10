public class Employment2016 {

    private int totalLaborForce;
    private int employedLaborForce;
    private int unemployedLabotForce;
    private double unemployedPercent;


    public int getTotalLaborForce() {
        return totalLaborForce;
    }

    public void setTotalLaborForce(int totalLaborForce) {
        this.totalLaborForce = totalLaborForce;
    }

    public int getEmployedLaborForce() {
        return employedLaborForce;
    }

    public void setEmployedLaborForce(int employedLaborForce) {
        this.employedLaborForce = employedLaborForce;
    }

    public int getUnemployedLabotForce() {
        return unemployedLabotForce;
    }

    public void setUnemployedLabotForce(int unemployedLabotForce) {
        this.unemployedLabotForce = unemployedLabotForce;
    }

    public double getUnemployedPercent() {
        return unemployedPercent;
    }

    public void setUnemployedPercent(double unemployedPercent) {
        this.unemployedPercent = unemployedPercent;
    }

    public Employment2016(int totalLaborForce, int employedLaborForce, int unemployedLabotForce, double unemployedPercent) {

        this.totalLaborForce = totalLaborForce;
        this.employedLaborForce = employedLaborForce;
        this.unemployedLabotForce = unemployedLabotForce;
        this.unemployedPercent = unemployedPercent;
    }


}
