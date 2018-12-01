package it.sevenbits.formatter.sm;

public class StateTransitionLexer {

    private final StateMapLexer stateMap;

    public StateTransitionLexer() {
        this.stateMap = new StateMapLexer();
    }

    public State nextState(final State state, final char ch) {
        return stateMap.getNextState(state, ch);
    }

    public State getStartState() {
        return this.stateMap.getStartState();
    }
}
