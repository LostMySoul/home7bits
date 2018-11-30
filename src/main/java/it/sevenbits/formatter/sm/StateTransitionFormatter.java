package it.sevenbits.formatter.sm;

public class StateTransitionFormatter {

    private final StateMapFormatter stateMap;

    public StateTransitionFormatter() {
        this.stateMap = new StateMapFormatter();
    }

    public State nextState(final State state, final String lexemeName) {
        return stateMap.getNextState(state, lexemeName);
    }

    public State getStartState() {
        return this.stateMap.getStartState();
    }
}
