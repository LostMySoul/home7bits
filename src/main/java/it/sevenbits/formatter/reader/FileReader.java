package it.sevenbits.formatter.reader;

import it.sevenbits.formatter.exception.FormatterException;

public class FileReader implements IReader {

    public boolean hasNext() throws FormatterException {
        return true;
    }

    public char read() throws FormatterException {
        return 0;
    }
}
