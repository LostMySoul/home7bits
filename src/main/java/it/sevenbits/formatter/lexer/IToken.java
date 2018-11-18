package it.sevenbits.formatter.lexer;

/**
 * interface for lexer's tokens
 */
public interface IToken {

    /**
     * method to specify which lexeme token contains (comment, loop and etc.)
     *
     * @return - name of lexeme type
     */
    String getName();

    /**
     * method to get lexeme
     *
     * @return - lexeme which has to be writed
     */
    String getLexeme();
}
