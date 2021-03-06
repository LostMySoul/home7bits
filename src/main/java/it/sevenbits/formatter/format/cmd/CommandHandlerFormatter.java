package it.sevenbits.formatter.format.cmd;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.sm.State;

import java.util.HashMap;
import java.util.Map;

/**
 * class for getting commands for Formatter for current State
 */
public class CommandHandlerFormatter {
    private final Map<State, ICommand> commandMap;
    /**
     * constructor for cmdHandler that sets map with commands
     */
    public CommandHandlerFormatter() {
        commandMap = new HashMap<>();
        State comment = new State("COMMENT");
        State bracketStart = new State("BRACKET_START");
        State bracketEnd = new State("BRACKET_END");
        State regLine = new State("REGULAR_LINE");
        commandMap.put(comment, new CommentCommand());
        commandMap.put(bracketStart, new BracketStartCommand());
        commandMap.put(bracketEnd, new BracketEndCommand());
        commandMap.put(regLine, new RegLineCommand());
    }

    /**
     * method to get command
     *
     * @param state - to get command for this state
     * @return needed command
     */
    public ICommand getCommand(final State state) {
        return commandMap.getOrDefault(state, null); //TODO: set get BY CURRENT STATE AND NEXT TOKEN
                                                               //TODO: name like Pair<>(state, token.name)
    }
}
