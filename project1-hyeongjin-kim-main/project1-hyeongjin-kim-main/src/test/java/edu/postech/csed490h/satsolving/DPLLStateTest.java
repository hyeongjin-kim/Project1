package edu.postech.csed490h.satsolving;

import edu.postech.csed490h.satsolving.formula.ExpParser;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DPLLStateTest {

    @Test
    void testToSetRepr() {
        var state = new DPLLState(Set.of(Set.of(new Literal(1, true))));
        assertEquals(0, state.getDecisionLevel());
    }
    // TODO: write more test methods to achieve desired branch coverage.
    @Test
    void test(){
        var l1 = new Literal(1, true);
        var l2 = new Literal(2, true);
        var l3 = new Literal(3, true);
        var l4 = new Literal(4, true);
        var setrepr = Set.of(
                Set.of(l1,l2,l3),
                Set.of( l4)
        );
        var state = new DPLLState(setrepr);
        state.propagate();
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(4, true);
        assertEquals(map,state.getCurrentAssignment());
    }
}