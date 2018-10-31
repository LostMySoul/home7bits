package it.sevenbits.formatter;

import it.sevenbits.formatter.format.Formatter;
import it.sevenbits.formatter.reader.StringReader;
import it.sevenbits.formatter.writer.StringWriter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormatterTest {
    @Test
    public void testFormatStringWithOnlyBounds(){
        String toTest = "{{{{{}}}}}";
        String answer = "{\n" +
                "    {\n" +
                "        {\n" +
                "            {\n" +
                "                {\n" +
                "                    \n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter();
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }





    @Test
    public void testFormatStringWithOneSpace() {
        String toTest = "aaa { bbbb; ccc;}";
        String answer = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter();
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }

    @Test
    public void testFormatStringWithSpaces() {
        String toTest = "aaa {    bbbb; ccc;}";
        String answer = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter();
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }

    @Test
    public void testFormatStringWithRandomNumberOfSpaces() {
        String toTest = "aaa { bbbb;    ccc;   }";
        String answer = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter();
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }

    @Test
    public void testFormatStringWithOneBigSpace() {
        String toTest = "aaa {            bbbb; ccc;}";
        String answer = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter();
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }
    @Test
    public void testFormatStringMoreNesting() {
        String toTest = "aaa { aa{aa;}}";
        String answer =
                "aaa {\n" +
                "    aa{\n" +
                "        aa;\n" +
                "    }\n" +
                "}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter();
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }

    @Test
    public void testFormatAlreadyFormattedString() {
        String toTest = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        String answer = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter();
        formatter.format(reader, writer);
        assertEquals(answer, writer.getString());
    }

}
