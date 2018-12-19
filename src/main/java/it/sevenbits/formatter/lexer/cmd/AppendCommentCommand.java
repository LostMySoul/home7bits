package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exceptions.FormatterException;
import it.sevenbits.formatter.lexer.LexerBuffer;
import it.sevenbits.formatter.io.reader.IReader;

/**
 * Lexer command for comment
 */
public class AppendCommentCommand implements ICommand {
    private IReader reader;

    /**
     * constructor for appendComment command
     *
     * @param reader - set reader for cmd
     */
    public AppendCommentCommand(final IReader reader) {
        this.reader = reader;
    }

    @Override
    public void execute() throws FormatterException {
        char current;
        LexerBuffer.append(Config.SINGLE_SLASH);
        while (reader.hasNext()) {
            current = reader.read();
            if (current == Config.BACK_CARET_CHAR || current == Config.LINE_JUMP_CHAR) {
                break;
            }
            LexerBuffer.append(current);
        }
        LexerBuffer.setLexemeReady(true);
    }
}
