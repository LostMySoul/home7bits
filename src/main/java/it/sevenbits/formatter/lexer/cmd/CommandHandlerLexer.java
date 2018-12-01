package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.reader.IReader;
import it.sevenbits.formatter.sm.State;

import java.util.HashMap;
import java.util.Map;

public class CommandHandlerLexer {
    private final Map<State, ICommand> commandMap;

    //private map
    public CommandHandlerLexer(final IReader reader) {
        commandMap = new HashMap<>();
        State comment = new State("COMMENT");
        State stringLiteral = new State("STRING_LITERAL");
        commandMap.put(comment, new CommentCommand(reader));
        commandMap.put(stringLiteral, new LiteralCommand(reader));
    }

    public ICommand getCommand(final State state) {
        return commandMap.getOrDefault(state, null);
    }
}
