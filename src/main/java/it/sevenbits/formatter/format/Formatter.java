package it.sevenbits.formatter.format;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.format.cmd.CommandHandlerFormatter;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.ILexerFactory;
import it.sevenbits.formatter.lexer.IToken;
import it.sevenbits.formatter.reader.IReader;
import it.sevenbits.formatter.sm.State;
import it.sevenbits.formatter.sm.StateTransitionFormatter;
import it.sevenbits.formatter.writer.IWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * class for code formatting
 */
public class Formatter implements IFormatter {
    private final ILexerFactory lexerFactory;
    private final CommandHandlerFormatter commandHandler;
    private final StateTransitionFormatter stateTransition = new StateTransitionFormatter();
    private State currentState;
    private final Logger logger = LoggerFactory.getLogger(Formatter.class);


    /**
     * constructor for Formatter
     *
     * @param lexerFactory - assigns lexerFactory for Formatter
     */
    public Formatter(final ILexerFactory lexerFactory) {
        this.lexerFactory = lexerFactory;
        commandHandler = new CommandHandlerFormatter();
    }

    /**
     * main format method(only for objects which implements specified Interfaces)
     *
     * @param reader - reader object which implements IReader
     * @param writer - writer object which implements IWriter
     * @throws FormatterException - throws exception if any problem with formatting appears
     */
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        ILexer lexer = lexerFactory.createLexer(reader);
        currentState = stateTransition.getStartState();
        logger.debug("FORMATTER STATE: " + currentState.toString());
        ICommand command;
        IToken token;
        String oldBuffer;
        while (lexer.hasMoreTokens()) {
            token = lexer.readToken();
            oldBuffer = FormatterBuffer.getBuffer();
            FormatterBuffer.setPreviousLexeme(oldBuffer);

            FormatterBuffer.clearBuffer();
            FormatterBuffer.append(token.getLexeme());
            currentState = stateTransition.nextState(currentState, token.getName());
            logger.info("FORMATTER STATE: " + currentState.toString());
            command = commandHandler.getCommand(currentState);
            if (command != null) {
                command.execute();
                logger.debug("command executed");
            }
            String previous = FormatterBuffer.getPreviousLexeme();
            if (previous != null) {
                write(writer, FormatterBuffer.getPreviousLexeme());
            }
        }
        oldBuffer = FormatterBuffer.getBuffer();
        if (oldBuffer != null) {
            write(writer, oldBuffer);
        }
    } //TODO: add checks if inside "" or comment

    private void write(final IWriter writer, final String str) throws FormatterException {
        for (int i = 0; i < str.length(); i++) {
            writer.write(str.charAt(i));
        }
    }
}