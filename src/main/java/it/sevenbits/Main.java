package it.sevenbits;

import it.sevenbits.format.Formatter;
import it.sevenbits.reader.StringReader;
import it.sevenbits.writer.StringWriter;


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
     */
    public static void main(final String[] args) {
        //Improvised main
        //TODO: rework main class
        String str = "{{{{{}}}}}";
        System.out.println("String to test:\n" + str);
        StringReader reader = new StringReader(str);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter();
        formatter.format(reader, writer);
        String formatted = writer.getString();
        System.out.println("Formatted string\n" + formatted);
    }
}
