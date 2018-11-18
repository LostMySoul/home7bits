package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.exception.FormatterErrorCode;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.reader.IReader;

/**
 * lexer class (analyze text for formatting)
 */
public class Lexer implements ILexer {
    private IReader reader;
    private int nestCntr = 0;
    private char current;

    /**
     * constructor for Lexer
     *
     * @param reader - reader to work with
     */
    public Lexer(final IReader reader) {
        this.reader = reader;
    }

    /**
     * read text from reader and analyze
     *
     * @return formatted lexeme in token
     * @throws FormatterException if any ex-s occurs
     */
    public IToken readToken() throws FormatterException {
        StringBuilder lexeme = new StringBuilder();
        if (hasMoreTokens()) {
            current = reader.read();
            while ((reader.hasNext() || current != Config.INDENT_CHAR) && current != '\uFFFF') {
                if (current == Config.LINE_BREAKER) {
                    lexeme.append(current);
                    current = nextNotSpaceOrJumpChar();
                    if (current != Config.WRAP_END) {
                        addIntentAndJumpToLexeme(lexeme);
                    }
                } else if (current == Config.WRAP_START) {
                    lexeme.append(current);
                    nestCntr++;
                    current = nextNotSpaceOrJumpChar();
                    addIntentAndJumpToLexeme(lexeme);
                } else if (current == Config.WRAP_END) {
                    nestCntr--;
                    addIntentAndJumpToLexeme(lexeme);
                    lexeme.append(current);
                    current = Config.INDENT_CHAR;
                    if (reader.hasNext()) {
                        current = reader.read();
                    }
                    return new Token("BeforeWrapEndLexeme", lexeme.toString());
                } else {
                    lexeme.append(current);
                    current = Config.INDENT_CHAR;
                    if (reader.hasNext()) {
                        current = reader.read();
                    }
                }
            }
        } else {
            throw new FormatterException(FormatterErrorCode.NO_TOKENS);
        }
        return new Token("FullLexeme", lexeme.toString());
    }
    //TODO: Fix when after wrap end char comes line jump char
    //TODO: and then comes another wrap end (now we have 2 jump chars) ?
    //TODO: improve private methods for same code;

    private char nextNotSpaceOrJumpChar() throws FormatterException {
        char c = ' ';
        while (reader.hasNext()) {
            c = reader.read();
            if (c != Config.INDENT_CHAR && c != Config.LINE_JUMP_CHAR && c != Config.BACK_CARET_CHAR) {
                break;
            }
        }
        return c;
    }

    private void addIntentAndJumpToLexeme(final StringBuilder lexeme) {
        lexeme.append(Config.LINE_JUMP_CHAR);
        for (int i = 0; i < nestCntr; i++) {
            for (int j = 0; j < Config.INDENT_NUM; j++) {
                lexeme.append(Config.INDENT_CHAR);
            }
        }
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
