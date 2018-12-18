package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exception.FormatterErrorCode;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.lexer.cmd.CommandHandlerLexer;
import it.sevenbits.formatter.reader.IReader;
import it.sevenbits.formatter.sm.State;
import it.sevenbits.formatter.lexer.sm.StateTransitionLexer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * lexer class (analyze text for formatting)
 */
public class Lexer implements ILexer {
    private IReader reader;
    private final CommandHandlerLexer commandHandler;
    private final StateTransitionLexer stateTransition = new StateTransitionLexer();
    private State currentState = stateTransition.getStartState();
    private final Logger logger = LoggerFactory.getLogger(Lexer.class);

    /**
     * constructor for Lexer
     *
     * @param reader - reader to work with
     */
    public Lexer(final IReader reader) {
        this.reader = reader;
        commandHandler = new CommandHandlerLexer(reader);
    }

    /**
     * read text from reader and analyze
     *
     * @return formatted lexeme in token
     * @throws FormatterException if any ex-s occurs
     */
    public IToken readToken() throws FormatterException {
        StringBuilder lexeme = new StringBuilder();
        ICommand command;
        if (hasMoreTokens()) {
            LexerBuffer.setLexemeReady(false);
            LexerBuffer.setCurrent(reader.read());
            while ((reader.hasNext() || LexerBuffer.getCurrent() != null) && !LexerBuffer.isLexemeReady()) {
                logger.debug("Current State: " + currentState.toString());
                command = commandHandler.getCommand(currentState, LexerBuffer.getCurrent());
                currentState = stateTransition.nextState(currentState, LexerBuffer.getCurrent());
                command.execute();
                logger.debug("command executed");

            }
        } else {
            throw new FormatterException(FormatterErrorCode.NO_TOKENS);
        }
        lexeme.append(LexerBuffer.getBuffer());
        logger.debug("sent token: \n" + lexeme.toString() + "\ntoken sent with State: " + currentState.toString());
        LexerBuffer.clear();
        return new Token(currentState.toString(), lexeme.toString());
    }

    /**
     * check if some tokens still exists in lexer
     *
     * @return - true if in lexer remained tokens
     * @throws FormatterException if any ex-s occurs
     */
    public boolean hasMoreTokens() throws FormatterException {
        return reader.hasNext();
    }
}
