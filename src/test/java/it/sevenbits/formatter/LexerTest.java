package it.sevenbits.formatter;

import it.sevenbits.formatter.exception.FormatterException;
import it.sevenbits.formatter.lexer.IToken;
import it.sevenbits.formatter.lexer.Lexer;
import it.sevenbits.formatter.reader.FileReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LexerTest {
//    @Test
//    public void testLexerFULLLEXEME() throws FormatterException {
//        FileReader fr = new FileReader("./src/test/java/it/sevenbits/formatter/testLexer.txt");
//        Lexer lexer = new Lexer(fr);
//        IToken token = lexer.readToken();
//        String answer = "aaa {\n" +
//                "    bbbb;\n" +
//                "    ccc;\n" +
//                "}";
//        assertEquals(answer, token.getLexeme());
//        assertEquals("BeforeWrapEndLexeme", token.getName());
//    } OLD

    @Test
    public void testLexerMock() throws FormatterException {
        FileReader fr = mock(FileReader.class);
        when(fr.hasNext()).thenReturn(true, true, true, false);
        when(fr.read()).thenReturn('a', 'b');
        Lexer lexer = new Lexer(fr);
        IToken token = lexer.readToken();
        assertEquals("ab", token.getLexeme());
    }
}
