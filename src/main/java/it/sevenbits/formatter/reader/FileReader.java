package it.sevenbits.formatter.reader;

import it.sevenbits.formatter.exception.FormatterErrorCode;
import it.sevenbits.formatter.exception.FormatterException;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * implementation of IReader to work with file
 * */
public class FileReader implements IReader, AutoCloseable {
    private BufferedReader br;
    /**
     * constructor for FileReader
     * @param path - path to file
     * @throws FormatterException if any ex-s occurs
     * */
    public FileReader(final String path) throws FormatterException {
        try {
            br = new BufferedReader(new java.io.FileReader(path));
        } catch (IOException ex) {
            throw new FormatterException(FormatterErrorCode.ERROR_READING_FILE, ex);
        }
    }
    /**
     * method for checking the existence of the next element
     *
     * @throws FormatterException if any ex-s occurs
     * @return condition
     * */
    public boolean hasNext() throws FormatterException {
        try {
            return br.ready();
        } catch (IOException ex) {
            throw new FormatterException(FormatterErrorCode.ERROR_READING_FILE, ex);
        }
    }
    /**
     * method to read next char in file
     * @return readed char
     * @throws FormatterException if any ex-s occurs
     * */
    public char read() throws FormatterException {
        try {
            return (char) br.read();
        } catch (IOException ex) {
            throw new FormatterException(FormatterErrorCode.ERROR_READING_FILE, ex);
        }
    }

    @Override
    public void close() throws FormatterException {
        try {
            br.close();
        } catch (IOException ex) {
            throw new FormatterException(FormatterErrorCode.ERROR_READING_FILE, ex);
        }
    }
}
