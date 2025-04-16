package edu.postech.csed490h.satsolving.formula;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

/**
 * A Boolean expression.
 */
public interface Exp {
    /**
     * Returns the set of all variables in this Boolean expression.
     * For example, if the formula is "(p1 || p2) && (p2 || ! p3)",
     * this method returns the set {1, 2, 3}.
     *
     * @return the set of variables in this expression
     */
    @NotNull Set<Integer> vars();

    /**
     * Evaluates the truth value of this Boolean expression, given
     * a truth assignment. A truth assignment is a map from variable
     * identifiers to Boolean values. For example, suppose that the
     * formula is "(p1 || p2) && (p2 || ! p3)". This method returns
     * true, given {1 |-> true, 2 |-> false, 3 |-> false}.
     *
     * @param assignment a truth assignment
     * @return true or false
     */
    boolean eval(@NotNull Map<Integer, Boolean> assignment);

    /**
     * Returns a string representation of this expression.
     *
     * @return a string representation
     */
    @NotNull String toPrettyString();

}