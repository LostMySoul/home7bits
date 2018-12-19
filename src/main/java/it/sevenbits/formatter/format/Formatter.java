package it.sevenbits.formatter.format;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exceptions.FormatterException;
import it.sevenbits.formatter.format.cmd.CommandHandlerFormatter;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.ILexerFactory;
import it.sevenbits.formatter.lexer.IToken;
import it.sevenbits.formatter.io.reader.IReader;
import it.sevenbits.formatter.sm.State;
import it.sevenbits.formatter.format.sm.StateTransitionFormatter;
import it.sevenbits.formatter.io.writer.IWriter;
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
     * @throws FormatterException - throws exceptions if any problem with formatting appears
     */
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        ILexer lexer = lexerFactory.createLexer(reader);
        currentState = stateTransition.getStartState();
        logger.debug("FORMATTER STATE: " + currentState.toString());
        ICommand command;
        IToken token;
        while (lexer.hasMoreTokens()) {
            token = lexer.readToken();
            FormatterBuffer.clearBuffer();
            FormatterBuffer.append(token.getLexeme());
            command = commandHandler.getCommand(currentState, token.getName());
            command.execute();
            logger.debug("command executed and now buffer:\n" + FormatterBuffer.getBuffer());
            currentState = stateTransition.nextState(currentState, token.getName());
            String out = FormatterBuffer.getBuffer();
            if (out != null) {
                write(writer, out);
            }
            logger.debug("FORMATTER STATE: " + currentState.toString());
        }
        FormatterBuffer.clearBuffer();
    }

    private void write(final IWriter writer, final String str) throws FormatterException {
        for (int i = 0; i < str.length(); i++) {
            writer.write(str.charAt(i));
        }
    }
}