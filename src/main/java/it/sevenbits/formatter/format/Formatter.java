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
    //    private final CommandHandlerFormatter commandHandler;
    private final StateTransitionFormatter stateTransition = new StateTransitionFormatter();
    private State currentState = stateTransition.getStartState();
    private final Logger logger = LoggerFactory.getLogger(Formatter.class);


    /**
     * constructor for Formatter
     *
     * @param lexerFactory - assigns lexerFactory for Formatter
     */
    public Formatter(final ILexerFactory lexerFactory) {
        this.lexerFactory = lexerFactory;
//        commandHandler =
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
        ICommand command;
        IToken token;
        while (lexer.hasMoreTokens()) {
            token = lexer.readToken();
            currentState = stateTransition.nextState(currentState, token.getName());
            logger.info("FORMATTER STATE: " + currentState.toString());


            write(writer, token.getLexeme());
        }
        //TODO: before writing store last lexeme in buffer and if next lexeme is comment
        //TODO: you should write lexeme + comment(trimmed) and only after comment jump to next line
        //old format lower (from lexer)

//            current = reader.read();
//            while ((reader.hasNext() || current != Config.INDENT_CHAR) && current != '\uFFFF') {
//                if (current == Config.LINE_BREAKER) {
//                    lexeme.append(current);
//                    current = nextNotSpaceOrJumpChar();
//                    if (current != Config.WRAP_END) {
//                        addIntentAndJumpToLexeme(lexeme);
//                    }
//                } else if (current == Config.WRAP_START) {
//                    lexeme.append(current);
//                    nestCntr++;
//                    current = nextNotSpaceOrJumpChar();
//                    addIntentAndJumpToLexeme(lexeme);
//                } else if (current == Config.WRAP_END) {
//                    nestCntr--;
//                    addIntentAndJumpToLexeme(lexeme);
//                    lexeme.append(current);
//                    current = Config.INDENT_CHAR;
//                    if (reader.hasNext()) {
//                        current = reader.read();
//                    }
//                    return new Token("BeforeWrapEndLexeme", lexeme.toString());
//                } else {
//                    lexeme.append(current);
//                    current = Config.INDENT_CHAR;
//                    if (reader.hasNext()) {
//                        current = reader.read();
//                    }
//                }
//            }
//
//        private char nextNotSpaceOrJumpChar() throws FormatterException {
//            char c = ' ';
//            while (reader.hasNext()) {
//                c = reader.read();
//                if (c != Config.INDENT_CHAR && c != Config.LINE_JUMP_CHAR && c != Config.BACK_CARET_CHAR) {
//                    break;
//                }
//            }
//            return c;
//        }
//
//        private void addIntentAndJumpToLexeme(final StringBuilder lexeme) {
//            lexeme.append(Config.LINE_JUMP_CHAR);
//            for (int i = 0; i < nestCntr; i++) {
//                for (int j = 0; j < Config.INDENT_NUM; j++) {
//                    lexeme.append(Config.INDENT_CHAR);
//                }
//            }
//        }

        //commandhandler and get lexeme from LexerBuffer to return new Token

    } //TODO: add checks if inside "" or comment

    private void write(final IWriter writer, final String str) throws FormatterException {
        for (int i = 0; i < str.length(); i++) {
            writer.write(str.charAt(i));
        }
    }
}