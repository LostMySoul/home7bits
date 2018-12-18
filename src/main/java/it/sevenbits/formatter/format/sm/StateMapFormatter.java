package it.sevenbits.formatter.format.sm;


import it.sevenbits.formatter.sm.Pair;
import it.sevenbits.formatter.sm.State;

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
        State bracketStart = new State("BRACKET_START");
        State bracketEnd = new State("BRACKET_END");
        State endLine = new State("LINE_END");
        State comment = new State("COMMENT");

        states.put(new Pair<>(defaultState, "BRACKET_START"), bracketStart);
        states.put(new Pair<>(defaultState, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(defaultState, "LINE_END"), endLine);
        states.put(new Pair<>(defaultState, "COMMENT"), comment);

        states.put(new Pair<>(bracketStart, "BRACKET_START"), bracketStart);
        states.put(new Pair<>(bracketStart, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(bracketStart, "LINE_END"), endLine);
        states.put(new Pair<>(bracketStart, "COMMENT"), comment);

        states.put(new Pair<>(bracketEnd, "BRACKET_START"), bracketStart);
        states.put(new Pair<>(bracketEnd, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(bracketEnd, "LINE_END"), endLine);
        states.put(new Pair<>(bracketEnd, "COMMENT"), comment);

        states.put(new Pair<>(endLine, "BRACKET_START"), bracketStart);
        states.put(new Pair<>(endLine, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(endLine, "LINE_END"), endLine);
        states.put(new Pair<>(endLine, "COMMENT"), comment);

        states.put(new Pair<>(comment, "BRACKET_START"), bracketStart);
        states.put(new Pair<>(comment, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(comment, "LINE_END"), endLine);
        states.put(new Pair<>(comment, "COMMENT"), comment);

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
     * @param tokenName - token name assigned to current string in map
     * @return next state
     */
    public State getNextState(final State state, final String tokenName) {
        return states.getOrDefault(new Pair<>(state, tokenName), defaultState);
    }


}
