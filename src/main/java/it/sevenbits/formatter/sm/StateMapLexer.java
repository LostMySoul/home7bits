package it.sevenbits.formatter.sm;

import it.sevenbits.formatter.cfg.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * class with states for lexer
 */
public class StateMapLexer {
    private final State defaultState = new State("READING");
    private final Map<Pair<State, Character>, State> states;

    /**
     * constructor that sets map for Lexer states
     */
    public StateMapLexer() {
        states = new HashMap<>();
        //State state = new State();
        State commentSuspicion = new State("COMMENT_SUSPICION");
        State comment = new State("COMMENT");
        State bracketStart = new State("BRACKET_START");
        State bracketEnd = new State("BRACKET_END");
        State regLine = new State("REGULAR_LINE");
        State stringLiteral = new State("STRING_LITERAL");
        states.put(new Pair<>(defaultState, Config.WRAP_START), bracketStart);
        states.put(new Pair<>(bracketStart, Config.WRAP_START), bracketStart);
        states.put(new Pair<>(defaultState, Config.WRAP_END), bracketEnd);
        states.put(new Pair<>(bracketEnd, Config.WRAP_END), bracketEnd);
        states.put(new Pair<>(defaultState, Config.LINE_BREAKER), regLine);
        states.put(new Pair<>(stringLiteral, Config.LINE_BREAKER), regLine);
        states.put(new Pair<>(defaultState, Config.SINGLE_SLASH), commentSuspicion);
        states.put(new Pair<>(commentSuspicion, Config.SINGLE_SLASH), comment);
        states.put(new Pair<>(defaultState, Config.STRING_LITER), stringLiteral);
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
     * @param state - get from this state
     * @param ch    - to state assigned to current char in map
     * @return next state
     */
    public State getNextState(final State state, final char ch) {
        return states.getOrDefault(new Pair<>(state, ch), defaultState);
    }
}
