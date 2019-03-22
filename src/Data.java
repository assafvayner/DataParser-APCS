import java.util.ArrayList;

public class Data {

    private ArrayList<State> states;

    public Data(){
        this.states = new ArrayList<>();
    }

    public boolean contains(String stateName){
        for (int i = 0; i < this.states.size(); i++) {
            if (this.states.get(i).getName().equals(stateName)){
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
        for (int i = 0; i < this.states.size(); i++) {
            if (this.states.get(i).getName().equals(name)){
                return this.states.get(i);
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
