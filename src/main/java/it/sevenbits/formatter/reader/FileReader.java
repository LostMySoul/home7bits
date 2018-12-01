package it.sevenbits.formatter.reader;

import it.sevenbits.formatter.exception.FormatterErrorCode;
import it.sevenbits.formatter.exception.FormatterException;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * implementation of IReader to work with file
 */
public class FileReader implements IReader, AutoCloseable {
    private BufferedReader br;
    private int lastchar;

    /**
     * constructor for FileReader
     *
     * @param path - path to file
     * @throws FormatterException if any ex-s occurs
     */
    public FileReader(final String path) throws FormatterException {
        try {
            br = new BufferedReader(new java.io.FileReader(path)); //TODO: set charset
            lastchar = br.read();
        } catch (IOException ex) {
            throw new FormatterException(FormatterErrorCode.ERROR_READING_FILE, ex);
        }
    }

    /**
     * method for checking the existence of the next element
     *
     * @return condition
     */
    public boolean hasNext() {
        return lastchar != -1;
    }

    /**
     * method to read next char in file
     *
     * @return readed char
     * @throws FormatterException if any ex-s occurs
     */
    public char read() throws FormatterException {
        try {
            if (lastchar == -1) {
                throw new IOException();
            }
            char toReturn = (char) lastchar;
            lastchar = br.read();
            return toReturn;
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
