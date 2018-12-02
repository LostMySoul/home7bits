package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.lexer.LexerBuffer;
import it.sevenbits.formatter.reader.IReader;

/**
 * Lexer command for string literal
 */
public class LiteralCommand implements ICommand {
    private IReader reader;

    /**
     * constructor for string literal command
     * @param reader - set reader for cmd
     */
    public LiteralCommand(final IReader reader) {
        this.reader = reader;
    }

    @Override
    public void execute() throws FormatterException {
        System.out.println("literal");
        char current;
        while (reader.hasNext()) {
            current = reader.read();
            if (current == Config.STRING_LITER) {
                LexerBuffer.append(current);
                break;
            }
            LexerBuffer.append(current);
        }
    }
}
