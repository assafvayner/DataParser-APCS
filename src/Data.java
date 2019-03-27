import java.util.ArrayList;

public class Data {

    private ArrayList<State> states;

    public Data(){
        this.states = new ArrayList<>();
    }

    public boolean contains(String stateName){
        for (State state : this.states) {
            if (state.getName().equals(stateName)) {
                return true;
            }
        }
        return false;
    }

    public void add(State s){
        states.add(s);
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

    public State getStateByName(String name){
        for (State state : this.states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }

    public void print(){
        for (State s : states) {
            s.print();
        }
    }



}
