package edu.postech.csed490h.satsolving.formula;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ExpTest {

    @Test
    void testParserOK() {
        var expStr = "((p1 || (p2 && (! p3))) || true)";
        var exp = ExpParser.parse(expStr);
        assertEquals(expStr, exp.toPrettyString());
    }

    @Test
    void testParserError() {
        assertThrows(IllegalStateException.class, () -> ExpParser.parse("p1 || p2 && ! p0 || true"));
    }

    @Test
    void testGetVars() {
        var exp = ExpParser.parse("((p1 || (p2 && (! p3))) || true)");
        assertEquals(Set.of(1, 2, 3), exp.vars());
    }

    @Test
    void testEvaluate() {
        var exp = ExpParser.parse("((p1 && p2) || (p3 && (! p1 || p2)))");
        assertTrue(exp.eval(Map.of(1, true, 2, true, 3, true)));
        assertTrue(exp.eval(Map.of(1, true, 2, true, 3, false)));
        assertFalse(exp.eval(Map.of(1, true, 2, false, 3, true)));
        assertFalse(exp.eval(Map.of(1, true, 2, false, 3, false)));
        assertTrue(exp.eval(Map.of(1, false, 2, true, 3, true)));
        assertFalse(exp.eval(Map.of(1, false, 2, true, 3, false)));
        assertTrue(exp.eval(Map.of(1, false, 2, false, 3, true)));
        assertFalse(exp.eval(Map.of(1, false, 2, false, 3, false)));
    }
}
