package it.sevenbits.formatter.sm;

import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.format.Formatter;
import it.sevenbits.formatter.lexer.ILexerFactory;
import it.sevenbits.formatter.lexer.LexerBuffer;
import it.sevenbits.formatter.lexer.LexerFactory;
import it.sevenbits.formatter.reader.StringReader;
import it.sevenbits.formatter.writer.StringWriter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testSM {
    ILexerFactory factory = new LexerFactory();

    @Test
    public void testFormat() throws FormatterException {
        String toTest = "{{{{{     \"   //   \" // }}}}}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        System.out.println(writer.getString());
        assertEquals(toTest, writer.getString());
    }
}
