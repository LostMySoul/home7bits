package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.lexer.LexerBuffer;
import it.sevenbits.formatter.reader.IReader;

/**
 * command for lexer to append char to lexeme
 */
public class AppendCommand implements ICommand {
    private IReader reader;
    /**
     * constructor with reader to read next char for next work
     * @param reader - reader
     * */
    public AppendCommand(final IReader reader) {
        this.reader = reader;
    }

    @Override
    public void execute() throws FormatterException {
        LexerBuffer.append(LexerBuffer.getCurrent());
        LexerBuffer.setCurrent(reader.read());
    }
}
