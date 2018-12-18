package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.reader.IReader;
import it.sevenbits.formatter.sm.Pair;
import it.sevenbits.formatter.sm.State;

import java.util.HashMap;
import java.util.Map;

/**
 * class for getting commands for Lexer for current State
 */
public class CommandHandlerLexer {
    private final Map<Pair<State, Character>, ICommand> commandMap;
    private IReader reader;

    /**
     * constructor for cmdHandler that sets map with commands
     *
     * @param reader - sets reader for each command
     */
    public CommandHandlerLexer(final IReader reader) {
        commandMap = new HashMap<>();
        this.reader = reader;
        State def = new State("READING");
        State comment = new State("COMMENT");
        State commentSuspicion = new State("COMMENT_SUSPICION");
        State stringLiteral = new State("STRING_LITERAL");
        State endLine = new State("LINE_END");
        State bracketStart = new State("BRACKET_START");
        State bracketEnd = new State("BRACKET_END");
        State whitespace = new State("WHITESPACE");

        commandMap.put(new Pair<>(def, Config.BRACKET_START), new BracketStartCommand());
        commandMap.put(new Pair<>(def, Config.BRACKET_END), new BracketEndCommand());
        commandMap.put(new Pair<>(def, Config.WHITESPACE), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(def, Config.LINE_JUMP_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(def, Config.BACK_CARET_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(def, Config.TAB_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(def, Config.LINE_BREAKER), new LineEndCommand());
        commandMap.put(new Pair<>(def, Config.STRING_LITER), new AppendLiteralCommand(reader));
        commandMap.put(new Pair<>(def, Config.SINGLE_SLASH), new AppendCommand(reader));

        commandMap.put(new Pair<>(bracketStart, Config.BRACKET_START), new BracketStartCommand());
        commandMap.put(new Pair<>(bracketStart, Config.BRACKET_END), new BracketEndCommand());
        commandMap.put(new Pair<>(bracketStart, Config.WHITESPACE), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(bracketStart, Config.LINE_JUMP_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(bracketStart, Config.BACK_CARET_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(bracketStart, Config.TAB_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(bracketStart, Config.LINE_BREAKER), new LineEndCommand());
        commandMap.put(new Pair<>(bracketStart, Config.STRING_LITER), new AppendLiteralCommand(reader));
        commandMap.put(new Pair<>(bracketStart, Config.SINGLE_SLASH), new AppendCommand(reader));

        commandMap.put(new Pair<>(bracketEnd, Config.BRACKET_START), new BracketStartCommand());
        commandMap.put(new Pair<>(bracketEnd, Config.BRACKET_END), new BracketEndCommand());
        commandMap.put(new Pair<>(bracketEnd, Config.WHITESPACE), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(bracketEnd, Config.LINE_JUMP_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(bracketEnd, Config.BACK_CARET_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(bracketEnd, Config.TAB_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(bracketEnd, Config.LINE_BREAKER), new LineEndCommand());
        commandMap.put(new Pair<>(bracketEnd, Config.STRING_LITER), new AppendLiteralCommand(reader));
        commandMap.put(new Pair<>(bracketEnd, Config.SINGLE_SLASH), new AppendCommand(reader));

        commandMap.put(new Pair<>(endLine, Config.BRACKET_START), new BracketStartCommand());
        commandMap.put(new Pair<>(endLine, Config.BRACKET_END), new BracketEndCommand());
        commandMap.put(new Pair<>(endLine, Config.WHITESPACE), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(endLine, Config.LINE_JUMP_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(endLine, Config.BACK_CARET_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(endLine, Config.TAB_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(endLine, Config.LINE_BREAKER), new LineEndCommand());
        commandMap.put(new Pair<>(endLine, Config.STRING_LITER), new AppendLiteralCommand(reader));
        commandMap.put(new Pair<>(endLine, Config.SINGLE_SLASH), new AppendCommand(reader));

        commandMap.put(new Pair<>(whitespace, Config.BRACKET_START), new BracketStartCommand());
        commandMap.put(new Pair<>(whitespace, Config.BRACKET_END), new BracketEndCommand());
        commandMap.put(new Pair<>(whitespace, Config.WHITESPACE), new IgnoreCommand(reader));
        commandMap.put(new Pair<>(whitespace, Config.BACK_CARET_CHAR), new IgnoreCommand(reader));
        commandMap.put(new Pair<>(whitespace, Config.LINE_JUMP_CHAR), new IgnoreCommand(reader));
        commandMap.put(new Pair<>(whitespace, Config.TAB_CHAR), new IgnoreCommand(reader));
        commandMap.put(new Pair<>(whitespace, Config.LINE_BREAKER), new LineEndCommand());
        commandMap.put(new Pair<>(whitespace, Config.STRING_LITER), new AppendLiteralCommand(reader));
        commandMap.put(new Pair<>(whitespace, Config.SINGLE_SLASH), new AppendCommand(reader));

        commandMap.put(new Pair<>(commentSuspicion, Config.BRACKET_START), new BracketStartCommand());
        commandMap.put(new Pair<>(commentSuspicion, Config.BRACKET_END), new BracketEndCommand());
        commandMap.put(new Pair<>(commentSuspicion, Config.WHITESPACE), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(commentSuspicion, Config.LINE_JUMP_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(commentSuspicion, Config.BACK_CARET_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(commentSuspicion, Config.TAB_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(commentSuspicion, Config.LINE_BREAKER), new LineEndCommand());
        commandMap.put(new Pair<>(commentSuspicion, Config.STRING_LITER), new AppendLiteralCommand(reader));
        commandMap.put(new Pair<>(commentSuspicion, Config.SINGLE_SLASH), new AppendCommentCommand(reader));

        commandMap.put(new Pair<>(comment, Config.BRACKET_START), new BracketStartCommand());
        commandMap.put(new Pair<>(comment, Config.BRACKET_END), new BracketEndCommand());
        commandMap.put(new Pair<>(comment, Config.WHITESPACE), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(comment, Config.LINE_JUMP_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(comment, Config.BACK_CARET_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(comment, Config.TAB_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(comment, Config.LINE_BREAKER), new LineEndCommand());
        commandMap.put(new Pair<>(comment, Config.STRING_LITER), new AppendLiteralCommand(reader));
        commandMap.put(new Pair<>(comment, Config.SINGLE_SLASH), new AppendCommand(reader));

        commandMap.put(new Pair<>(stringLiteral, Config.BRACKET_START), new BracketStartCommand());
        commandMap.put(new Pair<>(stringLiteral, Config.BRACKET_END), new BracketEndCommand());
        commandMap.put(new Pair<>(stringLiteral, Config.WHITESPACE), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(stringLiteral, Config.LINE_JUMP_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(stringLiteral, Config.BACK_CARET_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(stringLiteral, Config.TAB_CHAR), new AppendWhitespaceCommand(reader));
        commandMap.put(new Pair<>(stringLiteral, Config.LINE_BREAKER), new LineEndCommand());
        commandMap.put(new Pair<>(stringLiteral, Config.STRING_LITER), new AppendLiteralCommand(reader)); //TODO: needed?
        commandMap.put(new Pair<>(stringLiteral, Config.SINGLE_SLASH), new AppendCommand(reader));
    }

    /**
     * method to get command
     *
     * @param state - to get command for this state
     * @param ch    - current char
     * @return needed command
     */
    public ICommand getCommand(final State state, final char ch) {
        return commandMap.getOrDefault(new Pair<>(state, ch), new AppendCommand(reader));
    }
}
