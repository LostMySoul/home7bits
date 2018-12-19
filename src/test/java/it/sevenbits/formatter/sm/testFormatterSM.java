package it.sevenbits.formatter.sm;

import it.sevenbits.formatter.exceptions.FormatterException;
import it.sevenbits.formatter.format.Formatter;
import it.sevenbits.formatter.lexer.ILexerFactory;
import it.sevenbits.formatter.lexer.LexerFactory;
import it.sevenbits.formatter.io.reader.StringReader;
import it.sevenbits.formatter.io.writer.StringWriter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testFormatterSM {
    private ILexerFactory factory = new LexerFactory();

    @Test
    public void testFormatCode() throws FormatterException {
        String toTest = "s;//comment\n" +
                "package it.sevenbits.formatter;\n" +
                "\n" +
                "import it.sevenbits.formatter.exceptions.FormatterException;\n" +
                "import it.sevenbits.formatter.format.Formatter;\n" +
                "import it.sevenbits.formatter.lexer.ILexerFactory; //TO DO check bug if no indent between line and comment (fixed)\n" +
                "import it.sevenbits.formatter.lexer.LexerFactory;\n" +
                "import it.sevenbits.formatter.io.reader.FileReader;\n" +
                "import it.sevenbits.formatter.io.writer.FileWriter;\n" +
                "\n" +
                "public final class Main {\n" +
                "    private Main() {\n" +
                "          }\n" +
                "\n" +
                "\n" +
                "         public static void main(final String[] args) throws FormatterException {\n" +
                "        FileReader reader = new FileReader(\"./maintext.txt\");             //do args and add check that args not empty\n" +
                "           FileWriter writer = new FileWriter(\"./out.txt\");\n" +
                "        ILexerFactory factory = new LexerFactory();\n" +
                "          Formatter formatter = new Formatter(factory);\n" +
                "        formatter.format(reader, writer);\n" +
                "                     reader.close();\n" +
                "         writer.close();\n" +
                "    }\n" +
                "}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        String ans = "s;\n" +
                "//comment\n" +
                "package it.sevenbits.formatter;\n" +
                "import it.sevenbits.formatter.exceptions.FormatterException;\n" +
                "import it.sevenbits.formatter.format.Formatter;\n" +
                "import it.sevenbits.formatter.lexer.ILexerFactory;\n" +
                "//TO DO check bug if no indent between line and comment (fixed)\n" +
                "import it.sevenbits.formatter.lexer.LexerFactory;\n" +
                "import it.sevenbits.formatter.io.reader.FileReader;\n" +
                "import it.sevenbits.formatter.io.writer.FileWriter;\n" +
                "public final class Main {\n" +
                "    private Main() {\n" +
                "    }\n" +
                "    public static void main(final String[] args) throws FormatterException {\n" +
                "        FileReader reader = new FileReader(\"./maintext.txt\");\n" +
                "        //do args and add check that args not empty\n" +
                "        FileWriter writer = new FileWriter(\"./out.txt\");\n" +
                "        ILexerFactory factory = new LexerFactory();\n" +
                "        Formatter formatter = new Formatter(factory);\n" +
                "        formatter.format(reader, writer);\n" +
                "        reader.close();\n" +
                "        writer.close();\n" +
                "    }\n" +
                "}\n";
        System.out.println(writer.getString());
        assertEquals(ans, writer.getString());
    }

    @Test
    public void testBracketsWithOtherTokens() throws FormatterException {
        String toTest = "fufufu{{aa;{{//s\n{\"\"; { {} } aaa;}\"\";}//cc\n}}}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        String ans = "fufufu{\n" +
                "    {\n" +
                "        aa;\n" +
                "        {\n" +
                "            {\n" +
                "                //s\n" +
                "                {\n" +
                "                    \"\";\n" +
                "                    {\n" +
                "                        {\n" +
                "                        }\n" +
                "                    }\n" +
                "                    aaa;\n" +
                "                }\n" +
                "                \"\";\n" +
                "            }\n" +
                "            //cc\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        System.out.println(writer.getString());
        assertEquals(ans, writer.getString());
    }

    @Test
    public void testCommentWithOtherTokens() throws FormatterException {
        String toTest = " //ssssssssssss\n" +
                "catch (FormatterException e){" +
                "//dddddddddddddddddddd\n" +
                "System.out.println(\"Problems with formatter!\");" +
                "//aaaaaaa\n" +
                "//bbbbbbbbbbbbb\n" +
                "}" +
                "//ssssssssssss\n" +
                "   //nnnnnnnnnnn\n";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        String ans = "//ssssssssssss\n" +
                "catch (FormatterException e){\n" +
                "    //dddddddddddddddddddd\n" +
                "    System.out.println(\"Problems with formatter!\");\n" +
                "    //aaaaaaa\n" +
                "    //bbbbbbbbbbbbb\n" +
                "}\n" +
                "//ssssssssssss\n" +
                "//nnnnnnnnnnn\n";
        System.out.println(writer.getString());
        assertEquals(ans, writer.getString());
    }

    @Test
    public void testEndLineWithOtherTokens() throws FormatterException {
        String toTest = "aaaaaaa;catch (FormatterException e){aaaaaaaaa;" +
                "aaaaaaaaaa;\"aaaaa\";aa;" +
                "//aaaaaaa\n" +
                "aaa;}aaaaa;" +
                " aaaa;";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        String ans = "aaaaaaa;\n" +
                "catch (FormatterException e){\n" +
                "    aaaaaaaaa;\n" +
                "    aaaaaaaaaa;\n" +
                "    \"aaaaa\";\n" +
                "    aa;\n" +
                "    //aaaaaaa\n" +
                "    aaa;\n" +
                "}\n" +
                "aaaaa;\n" +
                "aaaa;\n";
        System.out.println(writer.getString());
        assertEquals(ans, writer.getString());
    }
}
