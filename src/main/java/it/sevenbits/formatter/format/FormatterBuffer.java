package it.sevenbits.formatter.format;

/**
 * class with buffer for formatter
 */
public final class FormatterBuffer {
    private static String previousLexeme;
    private static StringBuilder buffer = new StringBuilder();
    private static int currentNesting = 0;


    private FormatterBuffer() {
    }

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
     * method to clearBuffer buffer
     */
    public static void clearBuffer() {
        buffer.delete(0, buffer.length());
    }

    /**
     * method to get full buffer
     *
     * @return buffer as string
     */
    public static String getBuffer() {
        if (buffer.length() == 0) {
            return null;
        }
        return buffer.toString();
    }

    public static void setBuffer(final StringBuilder buffer) {
        FormatterBuffer.buffer = buffer;
    }

    public static int getCurrentNesting() {
        return currentNesting;
    }

    /**
     * method to increase current nesting
     */
    public static void increaseNesting() {
        currentNesting++;
    }

    /**
     * method to decrease current nesting
     */
    public static void decreaseNesting() {
        currentNesting--;
    }

    public static void setPreviousLexeme(final String previousLexeme) {
        FormatterBuffer.previousLexeme = previousLexeme;
    }

    public static String getPreviousLexeme() {
        return previousLexeme;
    }
}
