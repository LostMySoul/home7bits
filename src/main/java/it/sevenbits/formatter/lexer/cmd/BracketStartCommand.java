package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exceptions.FormatterException;
import it.sevenbits.formatter.lexer.LexerBuffer;

/**
 * command for lexer for bracket start
 */
public class BracketStartCommand implements ICommand {
    @Override
    public void execute() throws FormatterException {
        LexerBuffer.append(LexerBuffer.getCurrent());
        LexerBuffer.setLexemeReady(true);
    }
}
