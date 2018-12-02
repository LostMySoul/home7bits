package it.sevenbits.formatter.sm;


import java.util.HashMap;
import java.util.Map;
/**
 * class with states for formatter
 */
public class StateMapFormatter {
    private final State defaultState = new State("WAITING");
    private final Map<Pair<State, String>, State> states;
    /**
     * constructor that sets map for formatter states
     */
    public StateMapFormatter() {
        states = new HashMap<>();
        State comment = new State("COMMENT");
        State bracketStart = new State("BRACKET_START");
        State bracketEnd = new State("BRACKET_END");
        State regLine = new State("REGULAR_LINE");
        states.put(new Pair<>(defaultState, "BRACKET_START"), bracketStart);
        states.put(new Pair<>(bracketStart, "BRACKET_START"), bracketStart);
        states.put(new Pair<>(regLine, "BRACKET_START"), bracketStart);
        states.put(new Pair<>(comment, "BRACKET_START"), bracketStart);
        states.put(new Pair<>(bracketStart, "REGULAR_LINE"), regLine);
        states.put(new Pair<>(bracketStart, "BRACKET_END"), bracketEnd);

        states.put(new Pair<>(defaultState, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(bracketEnd, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(regLine, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(comment, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(bracketEnd, "REGULAR_LINE"), regLine);
        states.put(new Pair<>(bracketEnd, "BRACKET_START"), bracketStart);

        states.put(new Pair<>(defaultState, "COMMENT"), comment);
        states.put(new Pair<>(defaultState, "REGULAR_LINE"), regLine);
        states.put(new Pair<>(regLine, "REGULAR_LINE"), regLine);
        states.put(new Pair<>(regLine, "COMMENT"), comment);
        states.put(new Pair<>(comment, "REGULAR_LINE"), regLine);
    }

    /**
     * method to get start(default) state
     *
     * @return start state
     */
    public State getStartState() {
        return defaultState;
    }

    /**
     * method to get next state
     *
     * @param state     - get from this state
     * @param stateName - to state assigned to current string in map
     * @return next state
     */
    public State getNextState(final State state, final String stateName) {
        return states.getOrDefault(new Pair<>(state, stateName), defaultState);
    }
}
