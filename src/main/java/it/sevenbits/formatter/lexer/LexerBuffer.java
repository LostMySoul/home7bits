package it.sevenbits.formatter.lexer;

public class LexerBuffer {
    private static StringBuilder buffer = new StringBuilder();

    public static void append(final String str) {
        buffer.append(str);
    }

    public static void append(final char ch) {
        buffer.append(ch);
    }

    public static void clear() {
        buffer.delete(0, buffer.length() - 1);
    }

    public static String getBuffer() {
        return buffer.toString();
    }
}
