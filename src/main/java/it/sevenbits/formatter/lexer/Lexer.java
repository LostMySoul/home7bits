package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.exception.FormatterErrorCode;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.lexer.cmd.CommandHandlerLexer;
import it.sevenbits.formatter.reader.IReader;
import it.sevenbits.formatter.sm.State;
import it.sevenbits.formatter.sm.StateTransitionLexer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * lexer class (analyze text for formatting)
 */
public class Lexer implements ILexer {
    private IReader reader;
    private int nestCntr = 0;
    private char current;
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
        ICommand command = null;
        if (hasMoreTokens()) {
            current = reader.read();
            if (current != Config.STRING_LITER && current != Config.SINGLE_SLASH) {
                currentState = stateTransition.nextState(currentState, current);
            }
            while (reader.hasNext() || (int) current != -1) {
                logger.info("Current State: " + currentState.toString());
                LexerBuffer.append(current);
                if (current == Config.WRAP_START
                        || current == Config.WRAP_END
                        || current == Config.LINE_BREAKER) {
                    //TODO: add CMD to formatter for line with STRING LITER START then
                    //TODO: STRING LITER END and then u just append to out regular line exc moments when code is wrong
                    //TODO: DO same with comment like regular line with comment susp and then when comment susp
                    //TODO: was wrong u will add comment(when comment) or add another line (cause u didnt jump to next
                    //TODO: when it was just susp) YEAH!
                    currentState = stateTransition.nextState(currentState, current);
                    break;
                } else if (current == Config.STRING_LITER
                        || current == Config.SINGLE_SLASH) {
                    //ALWAYS SENT CURRENT STATE AND READER TO HANDLER
                    //IF JUST COMMENT SUSP RETURN NULL
                    //IF COMMENT MAKE IT FOR LINE END AND APPEND TO BUFFER
                    //IF COMMENT IT BREAKS CURRENT WHILE
                    //IF STRING LITER ITS NOT
                    //FORMATTER WILL HOLD PREVIOUS LEXEME AND IF NEXT LEXEME HAS COMMENT IT WILL APPEND IT
                    //AND ONLY AFTER APPENDING WILL ADD LINE INTENT
                    nestCntr++;
                    currentState = stateTransition.nextState(currentState, current);
                    command = commandHandler.getCommand(currentState);
                    if (command != null) {
                        command.execute();
                    }
                    if (currentState.toString().equals("COMMENT") || currentState.toString().equals("STRING_LITERAL")) {
                        break;
                    }
                    current = reader.read();
                    //cmdhandler(reader, state) if state comment or string literal returns
                    //if cmd!=null return new Token....
                    //if comment susp return null command and do NOTHING
                    //if full comment returns TOKEN with part like "/LEXEME" NO JUMP\CARET_BACK CHAR AT END
                    //cmd for string literal and comment
                    //TODO: rework with cmd
                } else {
                    current = reader.read();
                }
            }
        } else {
            throw new FormatterException(FormatterErrorCode.NO_TOKENS);
        }
        logger.info("token sent with State: " + currentState.toString());
        lexeme.append(LexerBuffer.getBuffer());
        logger.info("current token: " + lexeme.toString());
        LexerBuffer.clear();
        return new Token(currentState.toString(), lexeme.toString());
    }
    //TODO: Fix when after wrap end char comes line jump char
    //TODO: and then comes another wrap end (now we have 2 jump chars) ?
    //TODO: improve private methods for same code;

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
