package it.sevenbits.formatter.cfg;

/**
 * class with configuration constants
 */
public final class Config {
    private Config() {
    }

    /**
     * constant for number of spaces added for new string xtimes(depends on {})
     */
    public static final int INDENT_NUM = 4;
    /**
     * line breaker symbol
     */
    public static final char LINE_BREAKER = ';';
    /**
     * bracket start symbol
     */
    public static final char BRACKET_START = '{';
    /**
     * bracket end symbol
     */
    public static final char BRACKET_END = '}';
    /**
     * indent char (space)
     */
    public static final char WHITESPACE = ' ';
    /**
     * char that transfer to new string
     */
    public static final char LINE_JUMP_CHAR = '\n';
    /**
     * char that transfer to new string
     * */
    public static final char BACK_CARET_CHAR = '\r';
    /**
     * char that transfer to new string
     * */
    public static final char TAB_CHAR = '\t';
    /**
     * slash char
     * */
    public static final char SINGLE_SLASH = '/';
    /**
     * char for string liter
     * */
    public static final char STRING_LITER = '"';
}
