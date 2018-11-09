package it.sevenbits.formatter.writer;

import it.sevenbits.formatter.exception.FormatterException;

/**
 * interface for writing
 */
public interface IWriter {
    /**
     * method which writes char in specific stream(string)
     *
     * @param c - char for writing
     * @throws FormatterException - throws exception if any error with writing in
     */
    void write(char c)  throws FormatterException;
}
