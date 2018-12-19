package it.sevenbits.formatter.format;

import it.sevenbits.formatter.exceptions.FormatterException;
import it.sevenbits.formatter.io.reader.IReader;
import it.sevenbits.formatter.io.writer.IWriter;

/**
 * class for formatters
 */
public interface IFormatter {
    /**
     * format method
     * @param reader - reader object
     * @param writer - writer object
     * @throws FormatterException - throws exceptions if any problem with formatting appears
     */
    void format(IReader reader, IWriter writer) throws FormatterException;
}
