package edu.postech.csed490h.satsolving;

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
    void propagationtest(){
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
    @Test
    void decisiontest(){
        var l1 = new Literal(1, true);
        var l2 = new Literal(2, true);
        var l3 = new Literal(3, true);
        var l4 = new Literal(4, true);
        var setrepr = Set.of(
                Set.of(l1,l2,l3),
                Set.of( l4)
        );
        var state = new DPLLState(setrepr);
        state.decide(1,true);
        assertEquals(1,state.getDecisionLevel());
    }
    @Test
    void existingdecisiontest(){
        var l1 = new Literal(1, true);
        var l2 = new Literal(2, true);
        var l3 = new Literal(3, true);
        var l4 = new Literal(4, true);
        var setrepr = Set.of(
                Set.of(l1,l2,l3),
                Set.of( l4)
        );
        var state = new DPLLState(setrepr);
        state.decide(1,true);
        try{
            state.decide(1,true);
        } catch (IllegalArgumentException e){
            assertEquals("The variable is already assigned",e.getMessage());
        }
        assertEquals(1,state.getDecisionLevel());
    }
    @Test
    void backjumpingtest(){
        var l1 = new Literal(1, true);
        var l2 = new Literal(2, true);
        var l3 = new Literal(3, true);
        var l4 = new Literal(4, true);
        var l5 = new Literal(5, true);
        var l6 = new Literal(6, true);
        var setrepr = Set.of(
                Set.of(l1,l2),
                Set.of(l2,l3),
                Set.of(l1.negate(),l4.negate(),l5),
                Set.of(l1.negate(),l4,l6),
                Set.of(l1.negate(),l5.negate(),l6),
                Set.of(l1.negate(),l4,l6.negate()),
                Set.of(l1.negate(),l5.negate(),l6.negate())
        );
        var state = new DPLLState(setrepr);
        state.decide(1,true);
        state.decide(2,true);
        state.decide(3,true);
        state.decide(4,true);
        while(state.propagate()){}
        state.backjumpAndLearn();
        assertEquals(3,state.getDecisionLevel());
    }
    @Test
    void nodicisiontobackjumptest(){
        var l1 = new Literal(1, true);
        var l2 = new Literal(2, true);
        var l3 = new Literal(3, true);
        var l4 = new Literal(4, true);
        var setrepr = Set.of(
                Set.of(l1,l2,l3),
                Set.of( l4)
        );
        var state = new DPLLState(setrepr);
        try{
            state.backjumpAndLearn();
        } catch (IllegalArgumentException e){
            assertEquals("The current decision level is 0",e.getMessage());
        }
    }
    @Test
    void noconflicttest(){
        var l1 = new Literal(1, true);
        var l2 = new Literal(2, true);
        var l3 = new Literal(3, true);
        var l4 = new Literal(4, true);
        var setrepr = Set.of(
                Set.of(l1,l2,l3),
                Set.of( l4)
        );
        var state = new DPLLState(setrepr);
        state.decide(1,true);
        state.decide(2,true);
        state.decide(3,true);
        state.decide(4,true);
        while(state.propagate()){}
        try{
            state.backjumpAndLearn();
        } catch (IllegalArgumentException e){
            assertEquals("The conflict clause is empty",e.getMessage());
        }
    }
    @Test
    void inconsistenttest(){
        var l1 = new Literal(1, true);
        var l2 = new Literal(2, true);
        var l3 = new Literal(3, true);
        var l4 = new Literal(4, true);
        var setrepr = Set.of(
                Set.of(l1,l2,l3),
                Set.of( l4)
        );
        var state = new DPLLState(setrepr);
        state.decide(1,true);
        state.decide(2,true);
        state.decide(3,true);
        state.decide(4,false);
        assertTrue(state.isInconsistent());
    }
    @Test
    void satisfiabletest(){
        var l1 = new Literal(1, true);
        var l2 = new Literal(2, true);
        var l3 = new Literal(3, true);
        var l4 = new Literal(4, true);
        var setrepr = Set.of(
                Set.of(l1,l2,l3),
                Set.of( l4)
        );
        var state = new DPLLState(setrepr);
        state.decide(1,true);
        state.decide(2,true);
        state.decide(3,true);
        state.decide(4,true);
        assertTrue(state.isSatisfiable());
    }
    @Test
    void remainvariabletest(){
        var l1 = new Literal(1, true);
        var l2 = new Literal(2, true);
        var l3 = new Literal(3, true);
        var l4 = new Literal(4, true);
        var setrepr = Set.of(
                Set.of(l1,l2,l3),
                Set.of( l4)
        );
        var state = new DPLLState(setrepr);
        assertEquals(4,state.getRemainingVars().size());
        state.decide(1,true);
        state.decide(2,true);
        state.decide(3,true);
        state.decide(4,true);
        assertEquals(0,state.getRemainingVars().size());
    }
}