package it.sevenbits.formatter.sm;

import java.util.HashMap;
import java.util.Map;

public class StateMapFormatter {
    private final State defaultState = new State("FORMATTING");
    private final Map<Pair<State, String>, State> states;

    public StateMapFormatter() {
        states = new HashMap<>();
        //State state = new State(); state brackets {} ; "" ; (skip any inside while not this -> ); //comment (suspicion and full)
        //states.put(new Pair(currentState, ""), nextState);
    }

    public State getStartState() {
        return defaultState;
    }

    public State getNextState(final State state, final String stateName) {
        return states.getOrDefault(new Pair<>(state, stateName), defaultState);
    }
}
