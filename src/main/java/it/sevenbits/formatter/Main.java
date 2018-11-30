package it.sevenbits.formatter;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.format.Formatter;
import it.sevenbits.formatter.lexer.ILexerFactory;
import it.sevenbits.formatter.lexer.LexerFactory;
import it.sevenbits.formatter.reader.FileReader;
import it.sevenbits.formatter.writer.FileWriter;


/**
 * Main application point entry
 */
public final class Main {
    private Main() {
    }

    /**
     * Main Function for app
     *
     * @param args - console arguments
     * @throws FormatterException - if any ex-s with formatter occurs
     */
    public static void main(final String[] args) throws FormatterException {
        FileReader reader = new FileReader("./maintext.txt"); //TODO: do args and add check that args not empty
        FileWriter writer = new FileWriter("./out.txt");
        ILexerFactory factory = new LexerFactory();
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        reader.close();
        writer.close();
    }
}
