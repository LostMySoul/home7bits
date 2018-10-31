package it.sevenbits.formatter.reader;

/**
 * interface for reading
 */
public interface IReader {
    /**
     * method which allows to check is next symbol exists
     *
     * @return true/false if next symbol exist/not exist
     */
    boolean hasNext();

    /**
     * method which reads char from specified stream(string)
     *
     * @return readed char
     */
    char read();
}
