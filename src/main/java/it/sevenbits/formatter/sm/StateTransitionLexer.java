package it.sevenbits.formatter.sm;

/**
 * class for transitions between Lexer states
 */
public class StateTransitionLexer {

    private final StateMapLexer stateMap;

    /**
     * constructor that sets map with states
     */
    public StateTransitionLexer() {
        this.stateMap = new StateMapLexer();
    }

    /**
     * method to transit to next state
     *
     * @param state - old state
     * @param ch    - char that is a key for next state in map
     */
    public State nextState(final State state, final char ch) {
        return stateMap.getNextState(state, ch);
    }

    public State getStartState() {
        return this.stateMap.getStartState();
    }
}
