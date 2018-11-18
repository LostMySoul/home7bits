package it.sevenbits.formatter.format;

import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.reader.IReader;
import it.sevenbits.formatter.writer.IWriter;

/**
 * class for formatters
 */
public interface IFormatter {
    /**
     * format method
     * @param reader - reader object
     * @param writer - writer object
     * @throws FormatterException - throws exception if any problem with formatting appears
     */
    void format(IReader reader, IWriter writer) throws FormatterException;
}
