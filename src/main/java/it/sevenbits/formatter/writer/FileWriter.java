package it.sevenbits.formatter.writer;

import it.sevenbits.formatter.exception.FormatterErrorCode;
import it.sevenbits.formatter.exception.FormatterException;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * implementation of IWriter to work with file
 * */
public class FileWriter implements IWriter, AutoCloseable {
    private BufferedWriter bw;
    /**
     * constructor for FileWriter
     * @param path - path to file
     * @throws FormatterException if any ex-s occurs
     * */
    public FileWriter(final String path) throws FormatterException {
        try {
            bw = new BufferedWriter(new java.io.FileWriter(path));
        } catch (IOException ex) {
            throw new FormatterException(FormatterErrorCode.ERROR_READING_FILE, ex);
        }
    }

    /**
     * method to write char to file
     * @param c - char to write
     * @throws FormatterException if any ex-s occurs
     * */
    public void write(final char c) throws FormatterException {
        try {
            bw.write(c);
        } catch (IOException ex) {
            throw new FormatterException(FormatterErrorCode.ERROR_READING_FILE, ex);
        }
    }

    @Override
    public void close() throws FormatterException {
        try {
            bw.close();
        } catch (IOException ex) {
            throw new FormatterException(FormatterErrorCode.ERROR_READING_FILE, ex);
        }

    }
}
