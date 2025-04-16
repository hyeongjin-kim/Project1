package edu.postech.csed490h.satsolving;

import edu.postech.csed490h.satsolving.formula.ExpParser;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DPLLSolverTest {
    @Test
    void testDPLLStateCreation() {
        var formula = ExpParser.parse("(p1 || p2 || p3) && (! p1 || p4)");
        var l1 = new Literal(1, true);
        var l2 = new Literal(2, true);
        var l3 = new Literal(3, true);
        var l4 = new Literal(4, true);

        var setrepr = Set.of(
                Set.of(l1,l2,l3),
                Set.of(l1.negate(), l4)
        );
        assertEquals(setrepr, DPLLSolver.toSetRepr(formula));
    }

    // TODO: write more test methods to achieve desired branch coverage.
    @Test
    void TseitinTest(){
        var formula = ExpParser.parse("(p1 && ! p2) || !(p3 && p4)");
        var l1 = new Literal(1, true);
        var l2 = new Literal(2, true);
        var l3 = new Literal(3, true);
        var l4 = new Literal(4, true);
        new DPLLSolver(formula);
        var setrepr = Set.of(
                Set.of(l1,l2,l3),
                Set.of(l1.negate(), l4)
        );
        Map<Integer, Boolean> Assignment = new HashMap<>();
        Assignment.put(1,true);
        Assignment.put(2,false);
        Assignment.put(3,true);
        Assignment.put(4,true);
        DPLLSolver.checkSat().ifPresent(assgin ->{assert(formula.eval(assgin));});
    }
}