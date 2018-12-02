package it.sevenbits.formatter.sm;

import it.sevenbits.formatter.cfg.Config;

import java.util.HashMap;
import java.util.Map;

public class StateMapFormatter {
    private final State defaultState = new State("FORMATTING");
    private final Map<Pair<State, String>, State> states;

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
        states.put(new Pair<>(defaultState, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(bracketEnd, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(regLine, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(comment, "BRACKET_END"), bracketEnd);
        states.put(new Pair<>(bracketEnd, "REGULAR_LINE"), regLine);
        states.put(new Pair<>(defaultState, "COMMENT"), comment);
        states.put(new Pair<>(defaultState, "REGULAR_LINE"), regLine);
        states.put(new Pair<>(regLine, "COMMENT"), comment);
    }

    public State getStartState() {
        return defaultState;
    }

    public State getNextState(final State state, final String stateName) {
        return states.getOrDefault(new Pair<>(state, stateName), defaultState);
    }
}
