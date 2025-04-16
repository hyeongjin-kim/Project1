package edu.postech.csed490h.satsolving;

import edu.postech.csed490h.satsolving.formula.Exp;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A DPLL solver for a Boolean formula in CNF.
 */
public class DPLLSolver {
    //TODO: feel free to add any private fields and methods to implement this class.
    static private DPLLState finalstate;
    /**
     * Creates a DPLL solver for a given Boolean formula in CNF.
     *
     * @param formula a Boolean formula in CNF
     */
    DPLLSolver(Exp formula) {
        //TODO: implement this
        DPLLState state = new DPLLState(DPLLSolver.toSetRepr(formula));
        solve(state);
        finalstate = state;
    }

    Boolean solve(DPLLState state){
        if(state.isSatisfiable()){
            return true;
        } else if (state.isInconsistent() && state.getRemainingVars().isEmpty()) {
            return false;
        }
        int nextliteral = state.getRemainingVars().stream().toList().get(0);
        state.decide(nextliteral, true);
        if(solve(state)){
            return true;
        }
        state.backtrack();
        state.decide(nextliteral, false);
        return solve(state);
    }

    /**
     * Returns an optional map that assigns a truth value to each variable in the formula.
     * If the formula is satisfiable, the map is a satisfying assignment. Otherwise, it is empty.
     *
     * @return a satisfying assignment if the formula is satisfiable, and empty otherwise
     */
    public @NotNull static Optional<Map<Integer, Boolean>> checkSat() {
        //TODO: implement this
        if(finalstate.isSatisfiable()){
            return Optional.of(finalstate.getCurrentAssignment());
        }
        return Optional.empty();
    }

    /**
     * Returns the set representation of a Boolean formula in CNF
     *
     * @param formula a Boolean formula in CNF
     * @return a set of clauses, where each clause is a set of literals
     * @throws IllegalArgumentException if formula is not in CNF
     */
    static @NotNull Set<Set<Literal>> toSetRepr(@NotNull Exp formula) {
        //TODO: implement this
        Set<Set<Literal>> setrepresentation = new HashSet<>();
        String[] clauses = Stream.of(formula.toPrettyString()).map(string -> string.substring(1, string.length() - 1)).collect(Collectors.joining()).split("\\s*&*&\\s*");
        for(String clause : clauses){
            int openParenthesesCount = 0;
            int closeParenthesesCount = 0;
            for (char c : clause.toCharArray()) {
                if (c == '(') {
                    openParenthesesCount++;
                } else if (c == ')') {
                    closeParenthesesCount++;
                }
            }
            if(openParenthesesCount != closeParenthesesCount) throw new IllegalArgumentException();
            String[] literals = clause.split("\\s*\\|\\|\\s*");
            Set<Literal> literalSet = new HashSet<>();
            for(String literal: literals){
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(literal);

                while (matcher.find()) {
                    String number = matcher.group();
                    literalSet.add(new Literal(Integer.parseInt(number), !literal.contains("!")));
                }
            }
            setrepresentation.add(literalSet);
        }
        return setrepresentation;
    }
}
