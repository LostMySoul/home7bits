package it.sevenbits.formatter.format;

import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.ILexerFactory;
import it.sevenbits.formatter.reader.IReader;
import it.sevenbits.formatter.writer.IWriter;

/**
 * class for code formatting
 */
public class Formatter implements IFormatter {
    private final ILexerFactory lexerFactory;

    /**
     * constructor for Formatter
     *
     * @param lexerFactory - assigns lexerFactory for Formatter
     */
    public Formatter(final ILexerFactory lexerFactory) {
        this.lexerFactory = lexerFactory;
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
        while (lexer.hasMoreTokens()) {
            write(writer, lexer.readToken().getLexeme());
        }
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
        //commandhandler and get lexeme from LexerBuffer to return new Token
    } //TODO: add checks if inside "" or comment

    private void write(final IWriter writer, final String str) throws FormatterException {
        for (int i = 0; i < str.length(); i++) {
            writer.write(str.charAt(i));
        }
    }
}