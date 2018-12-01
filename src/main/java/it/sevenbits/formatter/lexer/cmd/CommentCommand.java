package it.sevenbits.formatter.lexer.cmd;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.reader.IReader;

public class CommentCommand implements ICommand {
    private IReader reader;

    public CommentCommand(final IReader reader) {
        this.reader = reader;
    }

    @Override
    public void execute() {
        System.out.println("comment");
        //work with LexerBuffer
    }
}
