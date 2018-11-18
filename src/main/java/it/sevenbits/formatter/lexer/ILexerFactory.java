package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.reader.IReader;

/**
 * interface for lexer factory
 */
public interface ILexerFactory {
    /**
     * factory method for creating lexers
     *
     * @param reader - to set reader for needed lexer
     * @return - returns required lexer
     */
    ILexer createLexer(IReader reader);
}
