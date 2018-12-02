package it.sevenbits.formatter.lexer;

/**
 * class with buffer for lexer
 */
public final class LexerBuffer {
    private LexerBuffer() {
    }

    private static StringBuilder buffer = new StringBuilder();

    /**
     * method to append to buffer string
     *
     * @param str - string to append
     */
    public static void append(final String str) {
        buffer.append(str);
    }

    /**
     * method to append to buffer char
     *
     * @param ch - char to append
     */
    public static void append(final char ch) {
        buffer.append(ch);
    }

    /**
     * method to clear buffer
     */
    public static void clear() {
        buffer.delete(0, buffer.length());
    }

    /**
     * method to get full buffer
     *
     * @return buffer as string
     */
    public static String getBuffer() {
        return buffer.toString();
    }
}
