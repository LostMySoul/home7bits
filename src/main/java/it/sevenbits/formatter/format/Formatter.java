package it.sevenbits.formatter.format;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.reader.IReader;
import it.sevenbits.formatter.writer.IWriter;

/**
 * class for code formatting
 */
public class Formatter {
    /**
     * main format method(only for objects which implements specified Interfaces)
     *
     * @param reader - reader object which implements IReader
     * @param writer - writer object which implements IWriter
     * @throws FormatterException - throws exception if any problem with formatting appears
     */
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        int nestCntr = 0;
        char current = nextNotSpaceSymbol(reader);
        while (reader.hasNext() || current != Config.INDENT_CHAR) {
            if (current == Config.LINE_BREAKER) {
                writer.write(current);
                current = nextNotSpaceSymbol(reader);
                if (current != Config.INDENT_CHAR && current != Config.WRAP_END) {
                    jumpToNextAndAddIntent(writer, nestCntr);
                }
            } else if (current == Config.WRAP_START) {
                writer.write(current);
                nestCntr++;
                current = nextNotSpaceSymbol(reader);
                if (current != Config.INDENT_CHAR) {
                    jumpToNextAndAddIntent(writer, nestCntr);
                }
            } else if (current == Config.WRAP_END) {
                nestCntr--;
                jumpToNextAndAddIntent(writer, nestCntr);
                writer.write(current);
                current = nextNotSpaceSymbol(reader);
            } else {
                writer.write(current);
                current = reader.read();
            }

        }

    } //TODO: add checks if inside () or comment

    private void jumpToNextAndAddIntent(final IWriter writer, final int cntr) throws FormatterException {
        writer.write(Config.LINE_JUMP_CHAR);
        for (int i = 0; i < cntr; i++) {
            for (int j = 0; j < Config.INDENT_NUM; j++) {
                writer.write(Config.INDENT_CHAR);
            }
        }
    }

    private char nextNotSpaceSymbol(final IReader reader) throws FormatterException {
        char c = Config.INDENT_CHAR;
        while (reader.hasNext()) {
            c = reader.read();
            if (c != Config.INDENT_CHAR && c != Config.LINE_JUMP_CHAR) {
                return c;
            }
        }
        return c;
    }
}