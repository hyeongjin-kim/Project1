package edu.postech.csed490h.satsolving;

import com.ibm.icu.impl.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A class for the state of the DPLL algorithm. The state consists of a set of
 * clauses, and a partial assignment of variables to truth values. This class
 * provides methods for unit propagation, deciding a truth value of a variable,
 * backjumping and learning, analyzing conflicts, and checking satisfiability
 * and inconsistency. The state also keeps track of the current decision level
 * and unassigned variables.
 */
public class DPLLState {
    //TODO: feel free to add any private fields and methods to implement this class.
    private record assign(int identifier, int decisionLevel, Set<Literal> clause){}
    private Set<Set<Literal>> formula;
    private Map<Integer, Boolean> assignment = new HashMap<>();
    private Stack<assign> decisionStack = new Stack<>();
    private int decisionLevel = 0;
    /**
     * Creates a DPLL state for a set of clauses
     *
     * @param formula a set of clauses, where each clause is a set of literals
     */
    //this constructor create DPLLState so that maintain the set of clauses and partial assignment
    DPLLState(@NotNull Set<Set<Literal>> formula) {
        this.formula = formula;
    }

    /**
     * Performs unit propagation, if there is a unit clause given by the formula
     * and the partial assignment, and the literal in the clause is not assigned.
     * Returns true if unit propagation is performed, and false otherwise.
     *
     * @return true if unit propagation is performed, and false otherwise
     */
    //this method check the unit clause and if there is a unit clause, then assign the value to the variable
    boolean propagate() {
        AtomicBoolean PropagationDone = new AtomicBoolean(false);
        formula.stream()
                .map(this::literaltounitperform)
                .flatMap(Optional::stream)
                .findAny()
                .ifPresent(pair -> {
                    assignment.put(pair.first.identifier(), pair.first.value());
                    decisionStack.push(new assign(pair.first.identifier(), decisionLevel, pair.second));
                    PropagationDone.set(true);
                });
        return PropagationDone.get();
    }

    private Optional< Pair<Literal, Set<Literal> >>literaltounitperform(Set<Literal> clause){
        if(clause.size() == 1){
            Literal literal = clause.iterator().next();
            if(!assignment.containsKey(literal.identifier())){
                return Optional.of(Pair.of(literal, clause));
            }
        } else if (clause.stream()
                .filter(literal-> !assignment.containsKey(literal.identifier()))
                .toList()
                .size()==1){
            return Optional.of(Pair.of(clause.stream()
                    .filter(literal->!assignment.containsKey(literal.identifier()))
                    .findFirst().get(), clause));
        }
        return Optional.empty();

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
        if (assignment.containsKey(variable)) {
            throw new IllegalArgumentException("The variable is already assigned");
        }
        assignment.put(variable, value);
        decisionStack.push(new assign(variable, decisionLevel, Set.of()));
        decisionLevel++;
    }

    /**
     * Backjumps to the most recent decision level where the source of conflict
     * is found using findConflictClause(), and learns a new clause from the conflict.
     * The opposite truth value of the literal in the conflict clause that is
     * responsible for the conflict is added to the current decision level.
     * If the current decision level is 0, this method throws an exception.
     */
    void backjumpAndLearn() {
        if(decisionLevel == 0){
            throw new IllegalArgumentException("The current decision level is 0");
        }
        Set<Literal> conflictClause = findConflictClause();
        Set<Set<Literal>> temp = new HashSet<>();
        temp.add(conflictClause);
        temp.addAll(formula);
        formula = temp;
        decisionLevel--;
    }

    /**
     * Returns a set of literals that are used for learning a new clause.
     * The set consists of decision literals, and are responsible for the
     * inconsistency of the current partial assignment. If the current
     * decision level is 0, this method returns an empty set.
     *
     * @return a conflict clause that is used for learning
     */
    Set<Literal> findConflictClause() {
        if (decisionLevel == 0) {
            return Set.of();
        }
        Stack<assign> Clausetocheck = new Stack<>();
        int currentDecisionLevel = decisionLevel;
        while (true) {
            assign assign = decisionStack.pop();
            assignment.remove(assign.identifier);
            if (assign.decisionLevel() == currentDecisionLevel) {
                Clausetocheck.add(assign);
            } else if (assign.decisionLevel() < currentDecisionLevel) {
                break;
            }
        }
        if(Clausetocheck.isEmpty()){
            throw new IllegalArgumentException("The conflict clause is empty");
        }
        Set<Literal> conflictclause = new HashSet<>(Clausetocheck.pop().clause);

        while(!Clausetocheck.isEmpty()){
            assign assign = Clausetocheck.pop();
            conflictclause.addAll(assign.clause);
            Set<Literal> toremove = conflictclause.stream().filter(literal -> assign.clause.contains(literal.negate())).collect(Collectors.toSet());
            conflictclause.removeAll(toremove);
        }
        return conflictclause;
    }

    /**
     * Returns true if the current partial assignment is inconsistent, i.e.,
     * there exists a clause whose truth value of every literal is false.
     * Otherwise, this method returns false.
     *
     * @return true if the current partial assignment is inconsistent
     */
    boolean isInconsistent() {
        return formula.stream()
                .anyMatch(clause -> clause.stream()
                        .allMatch(literal -> assignment.containsKey(literal.identifier()) && !assignment.get(literal.identifier()).equals(literal.value())));
    }

    /**
     * Returns true if every clause in the formula is satisfied by the current
     * partial assignment. Otherwise, this method returns false.
     *
     * @return true if every clause in the formula is satisfied.
     */
    boolean isSatisfiable() {
return formula.stream()
                .allMatch(clause -> clause.stream()
                        .anyMatch(literal -> assignment.containsKey(literal.identifier()) && assignment.get(literal.identifier()).equals(literal.value())));
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
        return decisionLevel;
    }

    /**
     * Returns the set of variables that are not assigned yet. Initially, the
     * set contains all variables.
     *
     * @return the set of unassigned variables
     */
    @NotNull Set<Integer> getRemainingVars() {
        return formula.stream()
                .flatMap(clause -> clause.stream())
                .map(Literal::identifier)
                .filter(identifier -> !assignment.containsKey(identifier))
                .collect(Collectors.toSet());
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
