package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.lexer.LexerBuffer;
import it.sevenbits.formatter.reader.IReader;

/**
 * command for whitespace appending
 */
public class AppendWhitespaceCommand implements ICommand {
    private IReader reader;

    /**
     * constructor with reader to read next char after whitespace
     *
     * @param reader - reader
     */
    public AppendWhitespaceCommand(final IReader reader) {
        this.reader = reader;
    }

    @Override
    public void execute() throws FormatterException {
        LexerBuffer.append(Config.WHITESPACE);
        LexerBuffer.setCurrent(reader.read());
    }
}
