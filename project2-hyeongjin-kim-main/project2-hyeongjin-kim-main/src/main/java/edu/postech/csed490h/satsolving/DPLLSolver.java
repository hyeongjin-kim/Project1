package edu.postech.csed490h.satsolving;

import edu.postech.csed490h.satsolving.formula.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.*;
import java.util.stream.*;
import java.util.Collections;

/**
 * A DPLL solver for a Boolean formula in CNF (Ver 2).
 */
public class DPLLSolver {
    //TODO: feel free to add any private fields and methods to implement this class.
    private static DPLLState state;
    private static int variableNum = 0;
    /**
     * Creates a DPLL solver for a given Boolean formula (not necessarily in CNF).
     *
     * @param formula a Boolean formula
     */
    DPLLSolver(Exp formula) {
        state = new DPLLState(toSetRepr(formula));
        solve(state);
    }
    /**
     * Returns an optional map that assigns a truth value to each variable in the formula.
     * If the formula is satisfiable, the map is a satisfying assignment. Otherwise, it is empty.
     *
     * @return a satisfying assignment if the formula is satisfiable, and empty otherwise
     */
    static Optional<Map<Integer, Boolean>> checkSat() {
        if (state.isSatisfiable()) {
            return Optional.of(state.getCurrentAssignment());
        } else {
            return Optional.empty();
        }
    }
    static Boolean solve(DPLLState state){
        if(state.isSatisfiable()){
            return true;
        }
        else if(state.isInconsistent()){
            return false;
        }
        int nextliteral = state.getRemainingVars().stream().toList().get(0);
        state.decide(nextliteral, true);
        if(solve(state)){
            return true;
        }
        state.backjumpAndLearn();
        state.decide(nextliteral, false);
        return solve(state);
    }
    /**
     * Returns the set representation of a Boolean formula (not necessarily in CNF).
     * If the formula is not in CNF, it is converted to CNF using the Tseitin transformation.
     *
     * @param formula a Boolean formula
     * @return a set of clauses, where each clause is a set of literals
     */
    static @NotNull Set<Set<Literal>> toSetRepr(@NotNull Exp formula) {
        variableNum = formula.vars().size();
        if(isCNF(formula)){
            return conjunctives(formula)
                    .map(conjunctive -> disjunctives(conjunctive)
                            .map(disjunctive -> literal(disjunctive).orElseThrow(IllegalArgumentException::new))
                            .collect(Collectors.toSet()))
                    .collect(Collectors.toSet());
        }
        else{
            return toCNF(formula)
                    .map(conjunctive -> disjunctives(conjunctive)
                            .map(disjunctive -> literal(disjunctive).orElseThrow(IllegalArgumentException::new))
                            .collect(Collectors.toSet()))
                    .collect(Collectors.toSet());
        }
    }
    private static boolean isCNF(@NotNull Exp exp){
        return Stream.of(exp).allMatch(e -> e instanceof Conjunction) && Stream.of(exp).flatMap(DPLLSolver::conjunctives).allMatch(e -> e instanceof Disjunction);
    }

    private static Stream<Exp> toCNF(Exp exp) {
        if (exp instanceof Negation n && !(n.subExp() instanceof Variable)){
            Variable newVariable = new Variable(variableNum++);
            // Negation을 CNF로 변환
            Exp cnfNegation = new Conjunction(
                    new Negation(newVariable), toCNFclause(exp, newVariable));
            return toCNF(cnfNegation);
        }
        else if (exp instanceof Conjunction c && !isCNF(exp)){
            Variable[] newvariables = new Variable[c.subExps().length];
            Exp[] subExps = c.subExps();
            Exp[] CNFs = new Exp[c.subExps().length + 1];
            for (int i = 0; i < c.subExps().length; i++) {
                if(!(subExps[i] instanceof Variable)){
                    newvariables[i] = new Variable(variableNum++);
                    CNFs[i] = toCNFclause(subExps[i], newvariables[i]);
                }
            }
            CNFs[c.subExps().length] = new Conjunction(newvariables);
            // Negation을 CNF로 변환
            Exp cnfconjunction = new Conjunction(Stream.of(CNFs).filter(Objects::nonNull).toArray(Exp[]::new));
            return toCNF(cnfconjunction);

        }
        else if (exp instanceof Disjunction d && Stream.of(((Disjunction) exp).subExps()).anyMatch(e-> !(e instanceof Variable))){
            Variable[] newvariables = new Variable[d.subExps().length];
            Exp[] subExps = d.subExps();
            Exp[] CNFs = new Exp[d.subExps().length + 1];
            for (int i = 0; i < d.subExps().length; i++) {
                if(!(subExps[i] instanceof Variable)){
                    newvariables[i] = new Variable(variableNum++);
                    CNFs[i] = toCNFclause(subExps[i], newvariables[i]);
                }
            }
            CNFs[d.subExps().length] = new Disjunction(newvariables);
            // Negation을 CNF로 변환
            Exp cnfDisjunction = new Conjunction(Stream.of(CNFs).filter(Objects::nonNull).toArray(Exp[]::new));
            return toCNF(cnfDisjunction);
        }
        else{
            return Stream.of(exp);
        }
    }
    static Exp toCNFclause( Exp exp, Variable newVariable){
        if (exp instanceof Negation n && !(n.subExp() instanceof Variable)) {
            Exp subExp = n.subExp();

            // Negation을 CNF로 변환
            Exp cnfNegation = new Conjunction(
                    new Disjunction(subExp, newVariable),
                    new Disjunction(new Negation(subExp), new Negation(newVariable))
            );
            return cnfNegation;
        } else if (exp instanceof Conjunction c && !isCNF(exp)) {
            Exp[] subExps = c.subExps();
            Exp cnfConjunction = new Conjunction(
                    new Disjunction(new Negation(subExps[0]), new Negation(subExps[1]), newVariable),
                    new Disjunction(subExps[0], new Negation(newVariable)),
                    new Disjunction(subExps[1], new Negation(newVariable))
            );
            return cnfConjunction;
        } else if (exp instanceof Disjunction d && Stream.of(((Disjunction) exp).subExps()).anyMatch(e-> !(e instanceof Variable))) {
            Exp[] subExps = d.subExps();
            // Disjunction을 CNF로 변환
            Exp cnfDisjunction = new Conjunction(
                    new Disjunction(subExps[0], subExps[1], new Negation(newVariable)),
                    new Disjunction(new Negation(subExps[0]), newVariable),
                    new Disjunction(new Negation(subExps[1]), newVariable)
            );

            return cnfDisjunction;
        } else {
            return exp;
        }
    }


    private static Stream<Exp> conjunctives(@NotNull Exp exp) {
        return (exp instanceof Conjunction c)
                ? Arrays.stream(c.subExps()).flatMap(DPLLSolver::conjunctives)
                : Stream.of(exp);
    }

    private static Stream<Exp> disjunctives(@NotNull Exp exp) {
        return (exp instanceof Disjunction d)
                ? Arrays.stream(d.subExps()).flatMap(DPLLSolver::disjunctives)
                : Stream.of(exp);
    }

    private static Optional<Literal> literal(@NotNull Exp exp) {
        return Optional.of((exp instanceof Negation n) ? n.subExp() : exp)
                .filter(e -> e instanceof Variable)
                .map(e -> (Variable) e)
                .map(v -> new Literal(v.identifier(), !(exp instanceof Negation)));
    }


}