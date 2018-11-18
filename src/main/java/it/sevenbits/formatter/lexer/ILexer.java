package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.exception.FormatterException;

/**
 * interface for text analyzer
 */
public interface ILexer {
    /**
     * method analyzes some text lexeme
     *
     * @return analyzed and formatted lexeme in token
     * @throws FormatterException if any ex-s occurs
     */
    IToken readToken() throws FormatterException;

    /**
     * method to check if some tokens still exists in lexer
     *
     * @return - true if in lexer remained tokens
     * @throws FormatterException if any ex-s occurs
     */
    boolean hasMoreTokens() throws FormatterException;
}
