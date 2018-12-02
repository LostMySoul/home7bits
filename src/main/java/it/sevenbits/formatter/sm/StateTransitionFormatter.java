package it.sevenbits.formatter.sm;

/**
 * class for transitions between Formatter states
 */
public class StateTransitionFormatter {

    private final StateMapFormatter stateMap;

    /**
     * constructor that sets map with states
     */
    public StateTransitionFormatter() {
        this.stateMap = new StateMapFormatter();
    }

    /**
     * method to transit to next state
     *
     * @param state      - old state
     * @param lexemeName - string that is a key for next state in map
     */
    public State nextState(final State state, final String lexemeName) {
        return stateMap.getNextState(state, lexemeName);
    }

    public State getStartState() {
        return this.stateMap.getStartState();
    }
}
