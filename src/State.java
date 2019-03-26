import java.util.ArrayList;

public class State {

    private String name;
    private ArrayList<County> counties;

    public void add(County c){
        this.counties.add(c);
    }

    public State(String name, ArrayList<County> counties) {
        this.name = name;
        this.counties = counties;
    }

    public State(String name) {
        this.name = name;
        this.counties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<County> getCounties() {
        return counties;
    }

    public void setCounties(ArrayList<County> counties) {
        this.counties = counties;
    }

    public County getCountyByName(String county_name) {
        for (int i = 0; i < this.counties.size(); i++) {
            if(this.counties.get(i).getName().equals(county_name)){
                return this.counties.get(i);
            }
        }
        return null;
    }

    public boolean contains(int fips){
        for (int i = 0; i < this.counties.size(); i++) {
            if (this.counties.get(i).getFips() == fips){
                return true;
            }
        }
        return false;
    }

    public County getCounyByFIPS(int fips) {
        for (County county : this.counties) {
            if (county.getFips() == fips) {
                return county;
            }
        }
        return null;
    }

    public void print() {
        for (County c : counties) {
            System.out.print(c.toString());
        }
    }
}
