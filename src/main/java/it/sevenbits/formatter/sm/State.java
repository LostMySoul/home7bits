package it.sevenbits.formatter.sm;

import java.util.Objects;

/**
 * simple class for states
 */
public class State {
    private final String currentState;

    /**
     * constructor that sets state
     *
     * @param currentState - state to set
     */
    public State(final String currentState) {
        this.currentState = currentState;
    }

    /**
     * method to get current state
     *
     * @return current state as string
     */
    public String toString() {
        return currentState;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return Objects.equals(currentState, state.currentState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentState);
    }
}