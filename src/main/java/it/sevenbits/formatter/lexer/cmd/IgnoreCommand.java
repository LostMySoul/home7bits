package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.lexer.LexerBuffer;
import it.sevenbits.formatter.reader.IReader;

/**
 * ignore command for lexer
 */
public class IgnoreCommand implements ICommand {
    private IReader reader;
    /**
     * constructor for ignore symbol cmd
     * @param reader to read next symbol
     * */
    public IgnoreCommand(final IReader reader) {
        this.reader = reader;
    }

    @Override
    public void execute() throws FormatterException {
        LexerBuffer.setCurrent(reader.read());
    }
}
