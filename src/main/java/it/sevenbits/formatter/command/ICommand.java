package it.sevenbits.formatter.command;

import it.sevenbits.formatter.exception.FormatterException;

/**
 * interface for commands
 */
public interface ICommand {
    //TODO: all commands has reader
    //and work with buffers to make just execute method!
    /**
     * main command method
     */
    void execute() throws FormatterException;
}
