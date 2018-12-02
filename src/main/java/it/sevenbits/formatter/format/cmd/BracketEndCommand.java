package it.sevenbits.formatter.format.cmd;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.format.FormatterBuffer;

/**
 * Formatter command for bracket end
 */
public class BracketEndCommand implements ICommand {
    /**
     * constructor for bracket end command
     */
    public BracketEndCommand() {
    }

    @Override
    public void execute() throws FormatterException {
        FormatterBuffer.decreaseNesting();
    }
}
