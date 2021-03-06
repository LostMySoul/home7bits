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
     * wrap start symbol
     */
    public static final char WRAP_START = '{';
    /**
     * wrap end symbol
     */
    public static final char WRAP_END = '}';
    /**
     * indent char (space)
     */
    public static final char INDENT_CHAR = ' ';
    /**
     * char that transfer to new string
     */
    public static final char LINE_JUMP_CHAR = '\n';
    /**
     * char that transfer to new string
     * */
    public static final char BACK_CARET_CHAR = '\r';
    /**
     * slash char
     * */
    public static final char SINGLE_SLASH = '/';
    /**
     * char for string liter
     * */
    public static final char STRING_LITER = '"';
}
