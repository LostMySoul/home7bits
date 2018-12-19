package it.sevenbits.formatter.io.writer;

import it.sevenbits.formatter.exceptions.FormatterException;

/**
 * interface for writing
 */
public interface IWriter {
    /**
     * method which writes char in specific stream(string)
     *
     * @param c - char for writing
     * @throws FormatterException - throws exceptions if any error with writing in
     */
    void write(char c)  throws FormatterException;
}
