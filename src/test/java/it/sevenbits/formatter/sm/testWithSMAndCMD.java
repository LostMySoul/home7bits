package it.sevenbits.formatter.sm;

import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.format.Formatter;
import it.sevenbits.formatter.lexer.ILexerFactory;
import it.sevenbits.formatter.lexer.LexerFactory;
import it.sevenbits.formatter.reader.StringReader;
import it.sevenbits.formatter.writer.StringWriter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testWithSMAndCMD {
    private ILexerFactory factory = new LexerFactory();

    @Test
    public void testFormatWithComment() throws FormatterException {
        String toTest = "f   unction1(){function2(){                 function3(){function4(){    " +
                "function5(){     \"   //   \" ;                                // comment HEH\n\r\t\n    }}}}}";
        StringReader reader = new StringReader(toTest);
        StringWriter writer = new StringWriter(null);
        Formatter formatter = new Formatter(factory);
        formatter.format(reader, writer);
        String ans = "f unction1(){\n" +
                "    function2(){\n" +
                "        function3(){\n" +
                "            function4(){\n" +
                "                function5(){\n" +
                "                    \"   //   \" ;\n" +
                "                    // comment HEH\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        System.out.println(writer.getString());
        assertEquals(ans, writer.getString());
    }

    @Test
    public void testFormat2() throws FormatterException {
        String toTest = "s;//comment\n" +
                "package it.sevenbits.formatter;\n" +
                "\n" +
                "import it.sevenbits.formatter.exception.FormatterException;\n" +
                "import it.sevenbits.formatter.format.Formatter;\n" +
                "import it.sevenbits.formatter.lexer.ILexerFactory; //TO DO check bug if no indent between line and comment (fixed)\n" +
                "import it.sevenbits.formatter.lexer.LexerFactory;\n" +
                "import it.sevenbits.formatter.reader.FileReader;\n" +
                "import it.sevenbits.formatter.writer.FileWriter;\n" +
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
                "import it.sevenbits.formatter.exception.FormatterException;\n" +
                "import it.sevenbits.formatter.format.Formatter;\n" +
                "import it.sevenbits.formatter.lexer.ILexerFactory;\n" +
                "//TO DO check bug if no indent between line and comment (fixed)\n" +
                "import it.sevenbits.formatter.lexer.LexerFactory;\n" +
                "import it.sevenbits.formatter.reader.FileReader;\n" +
                "import it.sevenbits.formatter.writer.FileWriter;\n" +
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
        assertEquals(ans,writer.getString());
    }
}
