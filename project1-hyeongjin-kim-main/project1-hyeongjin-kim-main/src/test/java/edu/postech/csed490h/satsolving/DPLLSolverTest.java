package edu.postech.csed490h.satsolving;

import edu.postech.csed490h.satsolving.formula.ExpParser;
import org.junit.jupiter.api.Test;

import java.util.Set;

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
        new DPLLSolver(formula);
        System.out.println(DPLLSolver.checkSat());
    }

    // TODO: write more test methods to achieve desired branch coverage.
    @Test
    void testilligalargumentforsetrepresentation(){
        boolean check = false;
        var formula2 = ExpParser.parse("(p1 || p2 || p3) && (! p1 && p4)");
        try{
            DPLLSolver.toSetRepr(formula2);
        }catch (IllegalArgumentException e){
            check = true;
        }
        assert check;
    }
    @Test
    void test(){
        var formula = ExpParser.parse("(p1) && (! p1)");
        new DPLLSolver(formula);
        assert DPLLSolver.checkSat().isEmpty();
    }
}