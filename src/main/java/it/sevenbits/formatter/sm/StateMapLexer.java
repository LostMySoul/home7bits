package it.sevenbits.formatter.sm;

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
        State stringLiteralStart = new State("STRING_LITERAL");
        State stringLiteralEnd = new State("STRING_LITERAL_END");
        //states.put(new Pair(currentState, ""), nextState);
        states.put(new Pair<>(defaultState, '{'), bracketStart);
        states.put(new Pair<>(bracketStart, '{'), bracketStart);
        states.put(new Pair<>(defaultState, '}'), bracketEnd);
        states.put(new Pair<>(bracketEnd, '}'), bracketEnd);
        states.put(new Pair<>(defaultState, ';'), lineEnd);
        states.put(new Pair<>(defaultState, '/'), commenSuspicion);
        states.put(new Pair<>(commenSuspicion, '/'), comment);
        states.put(new Pair<>(defaultState, '"'), stringLiteralStart);
        states.put(new Pair<>(stringLiteralStart, '"'), stringLiteralEnd);
    }

    public State getStartState() {
        return defaultState;
    }

    public State getNextState(final State state, final char ch) {
        return states.getOrDefault(new Pair<>(state, ch), defaultState);
    }
}
