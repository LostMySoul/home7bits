package it.sevenbits.formatter.sm;

import java.util.HashMap;
import java.util.Map;

public class StateMapFormatter {
    private final State defaultState = new State("default");
    private final Map<Pair<State, String>, State> states;

    public StateMapFormatter() {
        states = new HashMap<>();
        //State state = new State();
        //states.put(new Pair(currentState, ""), nextState);
    }

    public State getStartState() {
        return defaultState;
    }

    public State getNextState(final State state, final String lexeme) {
        return states.getOrDefault(new Pair<>(state, lexeme), defaultState);
    }
}
