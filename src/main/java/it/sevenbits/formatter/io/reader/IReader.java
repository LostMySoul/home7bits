package it.sevenbits.formatter.io.reader;

import it.sevenbits.formatter.exceptions.FormatterException;

/**
 * interface for reading
 */
public interface IReader {
    /**
     * method which allows to check is next symbol exists
     *
     * @return true/false if next symbol exist/not exist
     * @throws FormatterException - throws exceptions if any error with reading
     */
    boolean hasNext() throws FormatterException;

    /**
     * method which reads char from specified stream(string)
     *
     * @return readed char
     * @throws FormatterException - throws exceptions if any error with reading
     */
    char read() throws FormatterException;
}
