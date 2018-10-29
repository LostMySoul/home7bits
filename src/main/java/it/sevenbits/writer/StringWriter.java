package it.sevenbits.writer;

/**
 * Implementation of IWriter for Strings
 */
public class StringWriter implements IWriter {

    private StringBuilder sb;

    /**
     * constructor for Writer
     *
     * @param str - string to attach new formatted string
     */
    public StringWriter(final String str) {
        if (str == null) {
            sb = new StringBuilder();
        } else {
            sb = new StringBuilder(str);
        }
    }

    /**
     * writes char at end of current
     *
     * @param c - char to write
     */
    public void write(final char c) {
        sb.append(c);
    }

    public String getString() {
        return sb.toString();
    }
}
