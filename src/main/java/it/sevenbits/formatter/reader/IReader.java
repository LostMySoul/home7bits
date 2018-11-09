package it.sevenbits.formatter.reader;

import it.sevenbits.formatter.exception.FormatterException;

/**
 * interface for reading
 */
public interface IReader {
    /**
     * method which allows to check is next symbol exists
     *
     * @return true/false if next symbol exist/not exist
     * @throws FormatterException - throws exception if any error with reading
     */
    boolean hasNext() throws FormatterException;

    /**
     * method which reads char from specified stream(string)
     *
     * @return readed char
     * @throws FormatterException - throws exception if any error with reading
     */
    char read() throws FormatterException;
}
