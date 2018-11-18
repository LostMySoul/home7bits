package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.reader.IReader;

/**
 * lexer factory class
 */
public class LexerFactory implements ILexerFactory {
    /**
     * method to create required lexer
     *
     * @param reader - reader for current lexer
     * @return needed lexer
     */
    public ILexer createLexer(final IReader reader) {
        return new Lexer(reader);
    }
}
