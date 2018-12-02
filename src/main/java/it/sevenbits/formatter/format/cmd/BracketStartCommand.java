package it.sevenbits.formatter.format.cmd;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.format.FormatterBuffer;

/**
 * Formatter command for line with bracket start
 */
public class BracketStartCommand implements ICommand {
    /**
     * constructor for line with bracket start command
     */
    public BracketStartCommand() {
    }

    @Override
    public void execute() throws FormatterException {
        FormatterBuffer.increaseNesting();
    }
}
