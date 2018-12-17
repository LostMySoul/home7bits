package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.lexer.LexerBuffer;
import it.sevenbits.formatter.reader.IReader;

/**
 * Lexer command for string literal
 */
public class AppendLiteralCommand implements ICommand {
    private IReader reader;

    /**
     * constructor for append string literal command
     * @param reader - set reader for cmd
     */
    public AppendLiteralCommand(final IReader reader) {
        this.reader = reader;
    }

    @Override
    public void execute() throws FormatterException {
        char current;
        LexerBuffer.append(Config.STRING_LITER);
        while (reader.hasNext()) {
            current = reader.read();
            if (current == Config.STRING_LITER) {
                LexerBuffer.append(current);
                break;
            }
            LexerBuffer.append(current);
        }
        LexerBuffer.setCurrent(reader.read());
    }
}
