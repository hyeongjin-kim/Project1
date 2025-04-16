package edu.postech.csed490h.satsolving;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.*;

/**
 * A class for the state of the DPLL algorithm. The state consists of a set of
 * clauses, and a partial assignment of variables to truth values. This class
 * provides methods for unit propagation, deciding a truth value of a variable,
 * backtracking, and checking satisfiability and inconsistency. The state also
 * keeps track of the current decision level and unassigned variables.
 */
public class DPLLState {
    //TODO: feel free to add any private fields and methods to implement this class.

    private Set<Set<Literal>> clauses;
    private Map<Integer, Boolean> assignment;
    private Optional<Literal> LastDecidedLiteral;
    private List<Literal> DecisionOrder;
    private int DecisionLevel;
    /**
     * Creates a DPLL state for a given Boolean formula in CNF.
     *
     * @param formula a Boolean formula in CNF
     */
    DPLLState(@NotNull Set<Set<Literal>> formula) {
        //TODO: implement this constructor.
        clauses = new HashSet<>(formula);
        assignment = new HashMap<>();
        DecisionLevel = 0;
        DecisionOrder = new ArrayList<>();
        LastDecidedLiteral = Optional.empty();
    }

    /**
     * Performs unit propagation, if there is a unit clause given by the formula
     * and the partial assignment, and the literal in the clause is not assigned.
     * Returns true if unit propagation is performed, and false otherwise.
     *      *
     * @return true if unit propagation is performed, and false otherwise
     */
    boolean propagate() {
        //TODO: implement this method.
        boolean PropagationwasDone = Boolean.FALSE;
        List<Set<Literal>> removed = new ArrayList<>();
        for (Set<Literal> clause: clauses) {
            if(clause.size() == 1){
                Literal UnitLiteral = clause.iterator().next();
                assignment.put(UnitLiteral.identifier(),UnitLiteral.value());
                removed.addAll(clauses.stream().filter(SomeClause->SomeClause.contains(UnitLiteral)||SomeClause.contains(UnitLiteral.negate())).toList());
                PropagationwasDone = Boolean.TRUE;
            }
        }
        removed.forEach(clauses::remove);
        return PropagationwasDone;
    }

    /**
     * Assigns a truth value to a variable, provided that the variable does not
     * occur in the formula or is already assigned. Otherwise, this method throws
     * an exception. This method does not perform unit propagation.
     *
     * @param variable a variable
     * @param value    a truth value
     * @throws IllegalArgumentException if the variable is already assigned or
     *                                  does not occur in the formula
     */
    void decide(Integer variable, boolean value) {
        //TODO: implement this method.
        Literal targetLiteral = new Literal(variable, value);
        if(assignment.containsKey(variable)
                || clauses.stream().noneMatch(clause->clause.contains(targetLiteral)
                        || clause.contains(targetLiteral.negate()))){
            throw new IllegalArgumentException();
        }
        assignment.put(variable, value);
        DecisionOrder.add(targetLiteral);
        LastDecidedLiteral = Optional.of(targetLiteral);
        DecisionLevel += 1;
    }

    /**
     * Backtracks the last decision and assigns the opposite truth value to
     * the corresponding variable. If there is no decision to backtrack, this
     * method throws an exception.
     *
     * @throws IllegalStateException if there is no decision to backtrack
     */
    void backtrack() {
        //TODO: implement this method.
        if(LastDecidedLiteral.isPresent()){
            assignment.remove(LastDecidedLiteral.get().identifier());
            DecisionOrder.remove(LastDecidedLiteral.get());
            LastDecidedLiteral = DecisionOrder.isEmpty()
                    ?Optional.empty()
                    :Optional.of(DecisionOrder.get(DecisionOrder.size()-1));
            DecisionLevel -= 1;
            return;
        }
        throw new IllegalStateException();
    }

    /**
     * Returns true if the current partial assignment is inconsistent, i.e.,
     * there exists a clause whose truth value of every literal is false.
     * Otherwise, this method returns false.
     *
     * @return true if the current partial assignment is inconsistent
     */
    boolean isInconsistent() {
        //TODO: implement this method.
        for(Set<Literal> clause: clauses){
            boolean ClauseisFalse = true;
            for (Literal literal: clause){
                if(assignment.containsKey(literal.identifier()) && assignment.get(literal.identifier()) == literal.value()){
                    ClauseisFalse = false;
                    break;
                }
            }
            if(ClauseisFalse) return true;
        }
        return false;
    }

    /**
     * Returns true if every clause in the formula is satisfied by the current
     * partial assignment. Otherwise, this method returns false.
     *
     * @return true if every clause in the formula is satisfied.
     */
    boolean isSatisfiable() {
        //TODO: implement this method.
        for(Set<Literal> clause: clauses){
            boolean satisfied = false;
            for (Literal literal: clause){
                if(assignment.containsKey(literal.identifier()) && assignment.get(literal.identifier()) == literal.value()){
                    satisfied = true;
                    break;
                }
            }
            if(!satisfied) return false;
        }
        return true;
    }

    /**
     * Returns the current decision level. Initially, the decision level is 0.
     * The decision level increases by 1 whenever a new decision is made, and
     * decreases by 1 whenever backtracking is performed.
     *
     * @return the current decision level
     */
    int getDecisionLevel() {
        //TODO: implement this method.
        return DecisionLevel;
    }

    /**
     * Returns the set of variables that are not assigned yet. Initially, the
     * set contains all variables.
     *
     * @return the set of unassigned variables
     */
    @NotNull Set<Integer> getRemainingVars() {
        //TODO: implement this method.
        List<Integer> remainingvariables = new ArrayList<>();
        for(Set<Literal> clause: clauses){
            for (Literal literal: clause){
                if(!assignment.containsKey(literal.identifier())){
                    remainingvariables.add(literal.identifier());
                }
            }
        }
        return new HashSet<>(remainingvariables);
    }

    /**
     * Returns the current partial assignment.
     *
     * @return the current partial assignment
     */
    @NotNull Map<Integer, Boolean> getCurrentAssignment() {
        //TODO: implement this method.
        return assignment;
    }
}
