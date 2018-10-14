import it.sevenbits.format.Formatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testFormatter {
    @Test
    public void testFormat() {
        Formatter formatter = new Formatter();
        String ex = "aaa { bbbb; ccc;}";
        String ex_s = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        assertEquals(formatter.format(ex), ex_s);
    }
}
