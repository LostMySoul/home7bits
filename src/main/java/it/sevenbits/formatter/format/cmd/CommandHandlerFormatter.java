package it.sevenbits.formatter.format.cmd;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.sm.Pair;
import it.sevenbits.formatter.sm.State;

import java.util.HashMap;
import java.util.Map;

/**
 * class for getting commands for Formatter for current State
 */
public class CommandHandlerFormatter {
    private final Map<Pair<State, String>, ICommand> commandMap;

    /**
     * constructor for cmdHandler that sets map with commands
     */
    public CommandHandlerFormatter() {
        commandMap = new HashMap<>();
        State def = new State("WAITING");
        State endLine = new State("LINE_END");
        State bracketStart = new State("BRACKET_START");
        State bracketEnd = new State("BRACKET_END");
        State comment = new State("COMMENT");
        setForAllCmds(def);
        setForAllCmds(endLine);
        setForAllCmds(bracketStart);
        setForAllCmds(bracketEnd);
        setForAllCmds(comment);
    }

    /**
     * method to get command
     *
     * @param state - to get command for this state
     * @param tokenName - token name
     * @return needed command
     */
    public ICommand getCommand(final State state, final String tokenName) {
        return commandMap.getOrDefault(new Pair<>(state, tokenName), new IgnoreCommand());
    }

    private void setForAllCmds(final State state) {
        commandMap.put(new Pair<>(state, "LINE_END"), new EndLineCommand());
        commandMap.put(new Pair<>(state, "BRACKET_START"), new BracketStartCommand());
        commandMap.put(new Pair<>(state, "BRACKET_END"), new BracketEndCommand());
        commandMap.put(new Pair<>(state, "COMMENT"), new CommentCommand());
    }
}
