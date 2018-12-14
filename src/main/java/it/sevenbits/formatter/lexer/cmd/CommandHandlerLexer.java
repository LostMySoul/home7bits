package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.reader.IReader;
import it.sevenbits.formatter.sm.State;

import java.util.HashMap;
import java.util.Map;

/**
 * class for getting commands for Lexer for current State
 */
public class CommandHandlerLexer {
    private final Map<State, ICommand> commandMap;
    /**
     * constructor for cmdHandler that sets map with commands
     *
     * @param reader - sets reader for each command
     */
    public CommandHandlerLexer(final IReader reader) {
        commandMap = new HashMap<>();
        State comment = new State("COMMENT");
        State stringLiteral = new State("STRING_LITERAL");
        State normalString = new State("NORMAL_STRING"); //TODO:add append cmd just to append w\out jump char
        commandMap.put(comment, new CommentCommand(reader));
        commandMap.put(stringLiteral, new LiteralCommand(reader));
    }

    /**
     * method to get command
     *
     * @param state - to get command for this state
     * @return needed command
     */
    public ICommand getCommand(final State state) {
        return commandMap.getOrDefault(state, null);
    }
}
