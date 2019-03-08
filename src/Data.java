import java.util.ArrayList;

public class Data {

    public Data(){
        this.states = new ArrayList<>();
    }

    public Data(ArrayList<State> states) {
        this.states = states;
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public void setStates(ArrayList<State> states) {
        this.states = states;
    }

    private ArrayList<State> states;

}
