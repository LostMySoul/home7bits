package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.lexer.LexerBuffer;

public class LineEndCommand implements ICommand {
    @Override
    public void execute() throws FormatterException {
        LexerBuffer.append(LexerBuffer.getCurrent());
        LexerBuffer.setLexemeReady(true);
    }
}
