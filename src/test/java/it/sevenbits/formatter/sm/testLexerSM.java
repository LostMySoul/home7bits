package it.sevenbits.formatter.sm;

import it.sevenbits.formatter.exceptions.FormatterException;
import it.sevenbits.formatter.io.reader.StringReader;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.ILexerFactory;
import it.sevenbits.formatter.lexer.IToken;
import it.sevenbits.formatter.lexer.LexerFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testLexerSM {
    private ILexerFactory factory = new LexerFactory();

    @Test
    public void testEndLineTokenReadedNormally() throws FormatterException {
        String toTest = "a;bb;//s\nccc; dddd;{eeee;}ffff;\"\";";
        StringReader reader = new StringReader(toTest);
        IToken token;
        ILexer lexer = factory.createLexer(reader);
        token = lexer.readToken();
        String ans = "a;";     //endline to endline
        assertEquals(ans, token.getLexeme());
        token = lexer.readToken();
        ans = "bb;";    //endline to comment
        assertEquals(ans, token.getLexeme());
        lexer.readToken();
        token = lexer.readToken();
        ans = "ccc;";   //endline to whitespace
        assertEquals(ans, token.getLexeme());
        token = lexer.readToken();
        ans = " dddd;";     //endline to bracket start
        assertEquals(ans, token.getLexeme());
        lexer.readToken();
        token = lexer.readToken();
        ans = "eeee;";      //endline to bracket end
        assertEquals(ans, token.getLexeme());
        lexer.readToken();
        token = lexer.readToken();
        ans = "ffff;";      //endline to literal
        assertEquals(ans, token.getLexeme());
        token = lexer.readToken();
        ans = "\"\";";      //endline with literal at end
        assertEquals(ans, token.getLexeme());
    }

    @Test
    public void testCommentTokenReadedNormally() throws FormatterException {
        String toTest = "//aa\nbb;//s\n//ddd\n //fffff\n{//sss\n}//kkkkk\n\"\";";
        StringReader reader = new StringReader(toTest);
        IToken token;
        ILexer lexer = factory.createLexer(reader);
        token = lexer.readToken();
        String ans = "//aa";     //comment to endline
        assertEquals(ans, token.getLexeme());
        lexer.readToken();
        token = lexer.readToken();
        ans = "//s";    //comment to comment
        assertEquals(ans, token.getLexeme());
        token = lexer.readToken();
        ans = "//ddd";   //comment to whitespace
        assertEquals(ans, token.getLexeme());
        token = lexer.readToken();
        ans = " //fffff";     //comment to bracket start
        assertEquals(ans, token.getLexeme());
        lexer.readToken();
        token = lexer.readToken();
        ans = "//sss";      //comment to bracket end
        assertEquals(ans, token.getLexeme());
        lexer.readToken();
        token = lexer.readToken();
        ans = "//kkkkk";      //comment to literal
        assertEquals(ans, token.getLexeme());
    }

    @Test
    public void testBracketsTokenReadedNormally() throws FormatterException {
        String toTest = "{a;{//s\n{\"\";{ {{}} }aaa;}//s\n}\"\";";
        StringReader reader = new StringReader(toTest);
        IToken token;
        ILexer lexer = factory.createLexer(reader);
        token = lexer.readToken();
        String ans = "{";     //bracket start to endline
        assertEquals(ans, token.getLexeme());
        lexer.readToken();
        token = lexer.readToken();      //bracket start to comment
        assertEquals(ans, token.getLexeme());
        lexer.readToken();
        token = lexer.readToken();      //bracket start to string literal
        assertEquals(ans, token.getLexeme());
        lexer.readToken();
        token = lexer.readToken();      //bracket start to whitespace
        ans = "{";
        assertEquals(ans, token.getLexeme());
        ans = " {";
        token = lexer.readToken();      //bracket start to bracket start
        assertEquals(ans, token.getLexeme());
        token = lexer.readToken();
        ans = "{";      //bracket start to bracket end
        assertEquals(ans, token.getLexeme());
        token = lexer.readToken();
        ans = "}";      //bracket end to bracket end
        assertEquals(ans, token.getLexeme());
        token = lexer.readToken();      //bracket end to whitespace
        assertEquals(ans, token.getLexeme());
        token = lexer.readToken();
        ans = " }";      //bracket end to endline
        assertEquals(ans, token.getLexeme());
        lexer.readToken();
        token = lexer.readToken();
        ans = "}";      //bracket end to comment
        assertEquals(ans, token.getLexeme());
        lexer.readToken();
        token = lexer.readToken();      //bracket end to literal
        assertEquals(ans, token.getLexeme());
    }
}
