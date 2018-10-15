import it.sevenbits.format.Formatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testFormatter {
    @Test
    public void testFormatV1() {
        Formatter formatter = new Formatter();
        String ex = "aaa { bbbb; ccc;}";
        String ex_s = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        assertEquals(formatter.format(ex), ex_s);
    }

    @Test
    public void testFormatV2() {
        Formatter formatter = new Formatter();
        String ex = "aaa {    bbbb; ccc;}";
        String ex_s = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        assertEquals(formatter.format(ex), ex_s);
    }

    @Test
    public void testFormatV3() {
        Formatter formatter = new Formatter();
        String ex = "aaa { bbbb;    ccc;   }";
        String ex_s = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        assertEquals(formatter.format(ex), ex_s);
    }

    @Test
    public void testFormatV4() {
        Formatter formatter = new Formatter();
        String ex = "aaa {            bbbb; ccc;}";
        String ex_s = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        assertEquals(formatter.format(ex), ex_s);
    }
    @Test
    public void testFormatV5() {
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


}
