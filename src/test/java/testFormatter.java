import it.sevenbits.format.Formatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testFormatter {
    @Test
    public void testFormatStringOneSpaced() {
        Formatter formatter = new Formatter();
        String ex = "aaa { bbbb; ccc;}";
        String ex_s = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        assertEquals(formatter.format(ex), ex_s);
    }

    @Test
    public void testFormatStringSpaced() {
        Formatter formatter = new Formatter();
        String ex = "aaa {    bbbb; ccc;}";
        String ex_s = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        assertEquals(formatter.format(ex), ex_s);
    }

    @Test
    public void testFormatRandomSpaced() {
        Formatter formatter = new Formatter();
        String ex = "aaa { bbbb;    ccc;   }";
        String ex_s = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        assertEquals(formatter.format(ex), ex_s);
    }

    @Test
    public void testFormatOneBigSpacing() {
        Formatter formatter = new Formatter();
        String ex = "aaa {            bbbb; ccc;}";
        String ex_s = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        assertEquals(formatter.format(ex), ex_s);
    }
    @Test
    public void testFormatNesting() {
        Formatter formatter = new Formatter();
        String ex = "aaa { aa{aa;}}";
        String ex_s =
                "aaa {\n" +
                "    aa{\n" +
                "        aa;\n" +
                "    }\n" +
                "}";
        assertEquals(formatter.format(ex), ex_s);
    }

    @Test
    public void testFormatAlreadyFormattedString() {
        Formatter formatter = new Formatter();
        String ex = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        String ex_s = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        assertEquals(formatter.format(ex), ex_s);
    }


}
