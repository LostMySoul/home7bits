package it.sevenbits.format;

import it.sevenbits.cfg.Config;
import it.sevenbits.reader.IReader;
import it.sevenbits.writer.IWriter;

/**
 * class for code formatting
 */
public class Formatter {
    /**
     * main format method(only for objects which implements specified Interfaces)
     *
     * @param reader - reader object which implements IReader
     * @param writer - writer object which implements IWriter
     */
    public void format(final IReader reader, final IWriter writer) {
        int nestCntr = 0;
        char current = nextNotSpaceSymbol(reader);
        if (current == Config.INTENT_CHAR) {
            return;
        }
        while (reader.hasNext() || current != Config.INTENT_CHAR) {
            if (current == Config.LINE_BREAKER) {
                writer.write(current);
                current = nextNotSpaceSymbol(reader);
                if (current != Config.INTENT_CHAR && current != Config.WRAP_END) {
                    jumpToNextAndAddIntent(writer, nestCntr);
                }
            } else if (current == Config.WRAP_START) {
                writer.write(current);
                nestCntr++;
                current = nextNotSpaceSymbol(reader);
                if (current != Config.INTENT_CHAR) {
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

    private void jumpToNextAndAddIntent(final IWriter writer, final int cntr) {
        writer.write(Config.LINE_JUMP_CHAR);
        for (int i = 0; i < cntr; i++) {
            for (int j = 0; j < Config.INDENT_NUM; j++) {
                writer.write(Config.INTENT_CHAR);
            }
        }
    }

    private char nextNotSpaceSymbol(final IReader reader) {
        char c = ' ';
        while (reader.hasNext()) {
            c = reader.read();
            if (c != Config.INTENT_CHAR && c != Config.LINE_JUMP_CHAR) {
                return c;
            }
        }
        return c;
    }
}