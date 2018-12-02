package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.lexer.LexerBuffer;
import it.sevenbits.formatter.reader.IReader;

/**
 * Lexer command for comment
 */
public class CommentCommand implements ICommand {
    private IReader reader;

    /**
     * constructor for comment command
     * @param reader - set reader for cmd
     */
    public CommentCommand(final IReader reader) {
        this.reader = reader;
    }

    @Override
    public void execute() throws FormatterException {
        char current;
        while (reader.hasNext()) {
            current = reader.read();
            if (current == Config.BACK_CARET_CHAR || current == Config.LINE_JUMP_CHAR) {
                break;
            }
            LexerBuffer.append(current);
        }
    }
}
