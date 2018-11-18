package it.sevenbits.formatter.lexer;

/**
 * lexer token class
 */
public class Token implements IToken {
    private String name;
    private String lexeme;

    /**
     * token constructor
     *
     * @param name   - lexeme type name
     * @param lexeme - lexeme
     */
    public Token(final String name, final String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    /**
     * method specifies which lexeme this token contains(comment, loop and etc.)
     *
     * @return - name of lexeme type
     */
    public String getName() {
        return name;
    }

    /**
     * method to get lexeme
     *
     * @return - lexeme in string which has to be writed
     */
    public String getLexeme() {
        return lexeme;
    }

}
