package it.sevenbits.formatter;

import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.format.Formatter;
import it.sevenbits.formatter.lexer.ILexerFactory;
import it.sevenbits.formatter.lexer.LexerFactory;
import it.sevenbits.formatter.reader.StringReader;
import it.sevenbits.formatter.writer.StringWriter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormatterTest {
    ILexerFactory factory = new LexerFactory();
    @Test
    public void testFormatStringWithOnlyBounds() throws FormatterException {
        String toTest = "{{{{{}}}}}";
        String answer = "{\n" +
                "    {\n" +
                "        {\n" +
                "            {\n" +
                "                {\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }


    @Test
    public void testFormatStringWithOneSpace() throws FormatterException {
        String toTest = "aaa { bbbb; ccc;}";
        String answer = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}\n";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
//        assertEquals(answer, writer.getString());
    }

    @Test
    public void testFormatStringWithSpaces() throws FormatterException {
        String toTest = "aaa {    bbbb; ccc;}";
        String answer = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}\n";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }

    @Test
    public void testFormatStringWithRandomNumberOfSpaces() throws FormatterException {
        String toTest = "aaa { bbbb;    ccc;   }";
        String answer = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}\n";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }

    @Test
    public void testFormatStringWithOneBigSpace() throws FormatterException {
        String toTest = "aaa {            bbbb; ccc;}";
        String answer = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}\n";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }

    @Test
    public void testFormatStringMoreNesting() throws FormatterException {
        String toTest = "aaa { aa{aa;}}";
        String answer =
                "aaa {\n" +
                        "    aa{\n" +
                        "        aa;\n" +
                        "    }\n" +
                        "}\n";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }

    @Test
    public void testFormatAlreadyFormattedString() throws FormatterException {
        String toTest = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        String answer = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}\n";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }

}
