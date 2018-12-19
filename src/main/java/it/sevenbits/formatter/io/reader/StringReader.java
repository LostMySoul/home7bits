package it.sevenbits.formatter.io.reader;

/**
 * Implementation of IReader for Strings
 */
public class StringReader implements IReader {
    private String str;
    private int index = 0;

    /**
     * constructor for reader
     *
     * @param str - string for reading
     */
    public StringReader(final String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.str = str;
    }

    /**
     * method to check if next element exist
     *
     * @return true if exist/ false if not
     */
    public boolean hasNext() {
        return index < str.length();
    }

    /**
     * reads char at current index of string
     *
     * @return readed char
     * @throws IndexOutOfBoundsException - if next symbol not exist
     */
    public char read() {
        char out = str.charAt(index);
        index++;
        return out;
    }
}
