package it.sevenbits.formatter.lexer.sm;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.sm.Pair;
import it.sevenbits.formatter.sm.State;

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
        State endLine = new State("LINE_END");
        State stringLiteral = new State("STRING_LITERAL");

        State bracketStart = new State("BRACKET_START");
        State bracketEnd = new State("BRACKET_END");

        State whitespace = new State("WHITESPACE");


        State commentSuspicion = new State("COMMENT_SUSPICION");
        State comment = new State("COMMENT");

        states.put(new Pair<>(commentSuspicion, Config.SINGLE_SLASH), comment);
        states.put(new Pair<>(comment, Config.SINGLE_SLASH), commentSuspicion);
        states.put(new Pair<>(defaultState, Config.SINGLE_SLASH), commentSuspicion);
        putAfterWhiteSpace(commentSuspicion, whitespace, Config.SINGLE_SLASH);
        putBeforeWhiteSpace(commentSuspicion, whitespace);

        states.put(new Pair<>(whitespace, Config.WHITESPACE), whitespace);
        states.put(new Pair<>(whitespace, Config.BACK_CARET_CHAR), whitespace);
        states.put(new Pair<>(whitespace, Config.LINE_JUMP_CHAR), whitespace);
        states.put(new Pair<>(whitespace, Config.TAB_CHAR), whitespace);
        putBeforeWhiteSpace(defaultState, whitespace);

        states.put(new Pair<>(defaultState, Config.LINE_BREAKER), endLine);
        states.put(new Pair<>(endLine, Config.LINE_BREAKER), endLine);
        states.put(new Pair<>(endLine, Config.SINGLE_SLASH), commentSuspicion);
        putBeforeWhiteSpace(endLine, whitespace);
        putAfterWhiteSpace(endLine, whitespace, Config.LINE_BREAKER);

        states.put(new Pair<>(defaultState, Config.STRING_LITER), stringLiteral);
        states.put(new Pair<>(stringLiteral, Config.LINE_BREAKER), endLine);
        putAfterWhiteSpace(stringLiteral, whitespace, Config.STRING_LITER);
        putBeforeWhiteSpace(stringLiteral, whitespace);



        states.put(new Pair<>(defaultState, Config.BRACKET_START), bracketStart);
        states.put(new Pair<>(endLine, Config.BRACKET_START), bracketStart);
        states.put(new Pair<>(bracketStart, Config.BRACKET_START), bracketStart);
        states.put(new Pair<>(bracketEnd, Config.BRACKET_START), bracketStart);
        states.put(new Pair<>(comment, Config.BRACKET_START), bracketStart);
        putBeforeWhiteSpace(bracketStart, whitespace);
        putAfterWhiteSpace(bracketStart, whitespace, Config.BRACKET_START);

        states.put(new Pair<>(defaultState, Config.BRACKET_END), bracketEnd);
        states.put(new Pair<>(endLine, Config.BRACKET_END), bracketEnd);
        states.put(new Pair<>(bracketEnd, Config.BRACKET_END), bracketEnd);
        states.put(new Pair<>(bracketStart, Config.BRACKET_END), bracketEnd);
        states.put(new Pair<>(comment, Config.BRACKET_END), bracketEnd);
        putBeforeWhiteSpace(bracketEnd, whitespace);
        putAfterWhiteSpace(bracketEnd, whitespace, Config.BRACKET_END);
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

    private void putBeforeWhiteSpace(final State state, final State whitespace) {
        states.put(new Pair<>(state, Config.WHITESPACE), whitespace);
        states.put(new Pair<>(state, Config.BACK_CARET_CHAR), whitespace);
        states.put(new Pair<>(state, Config.LINE_JUMP_CHAR), whitespace);
        states.put(new Pair<>(state, Config.TAB_CHAR), whitespace);
    }

    private void putAfterWhiteSpace(final State state, final State whitespace, final char ch) {
        states.put(new Pair<>(whitespace, ch), state);
        states.put(new Pair<>(whitespace, ch), state);
        states.put(new Pair<>(whitespace, ch), state);
        states.put(new Pair<>(whitespace, ch), state);
    }
}
