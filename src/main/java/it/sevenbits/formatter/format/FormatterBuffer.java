package it.sevenbits.formatter.format;

public class FormatterBuffer {
    private static StringBuilder buffer = new StringBuilder();
    private static int currentNesting = 0;

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

    public static int getCurrentNesting() {
        return currentNesting;
    }

    public static void increaseNesting() {
        currentNesting++;
    }

    public static void decreaseNesting() {
        currentNesting--;
    }
}
