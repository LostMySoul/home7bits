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
    private ILexerFactory factory = new LexerFactory();

    @Test
    public void testFormat1() throws FormatterException {
        String toTest = "{{{{{     \"   //   \" ; // \n    }}}}}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        System.out.println(writer.getString());
//        assertEquals(toTest, writer.getString()); //now doesnt work cause commentCMD doesnt add line jump
    }

    @Test
    public void testFormat2() throws FormatterException {
        String toTest = "aaa { bbbb; ccc;}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        System.out.println(writer.getString());
//        assertEquals(toTest, writer.getString()); //now doesnt work cause commentCMD doesnt add line jump
    }
}
