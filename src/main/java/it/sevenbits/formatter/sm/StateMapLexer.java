package it.sevenbits.formatter.sm;

import it.sevenbits.formatter.cfg.Config;

import java.util.HashMap;
import java.util.Map;

public class StateMapLexer {
    private final State defaultState = new State("READING");
    private final Map<Pair<State, Character>, State> states;

    public StateMapLexer() {
        states = new HashMap<>();
        //State state = new State();
        State bracketStart = new State("BRACKET_START");
        State bracketEnd = new State("BRACKET_END");
        State lineEnd = new State("LINE_END");
        State commenSuspicion = new State("COMMENT_SUSPICION");
        State comment = new State("COMMENT");
        State stringLiteral = new State("STRING_LITERAL");
//        State stringLiteralEnd = new State("STRING_LITERAL_END");
        //states.put(new Pair(currentState, ""), nextState);
        states.put(new Pair<>(defaultState, Config.WRAP_START), bracketStart);
        states.put(new Pair<>(bracketStart, Config.WRAP_START), bracketStart);
        states.put(new Pair<>(defaultState, Config.WRAP_END), bracketEnd);
        states.put(new Pair<>(bracketEnd, Config.WRAP_END), bracketEnd);
        states.put(new Pair<>(defaultState, Config.LINE_JUMP_CHAR), lineEnd);
        states.put(new Pair<>(defaultState, Config.SINGLE_SLASH), commenSuspicion);
        states.put(new Pair<>(commenSuspicion, Config.SINGLE_SLASH), comment);
        states.put(new Pair<>(defaultState, Config.STRING_LITER), stringLiteral);
//        states.put(new Pair<>(stringLiteralStart, Config.STRING_LITER), stringLiteralEnd);
    }

    public State getStartState() {
        return defaultState;
    }

    public State getNextState(final State state, final char ch) {
        return states.getOrDefault(new Pair<>(state, ch), defaultState);
    }
}
