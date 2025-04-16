package edu.postech.csed490h.satsolving.formula;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

/**
 * A Boolean expression whose top-level operator is ! (not).
 *
 * @param subExp a sub-expression
 */
public record Negation(@NotNull Exp subExp) implements Exp {

    @Override
    public @NotNull Set<Integer> vars() {
        return subExp.vars();
    }

    @Override
    public boolean eval(@NotNull Map<Integer, Boolean> assignment) {
        return !subExp.eval(assignment);
    }

    @Override
    public @NotNull String toPrettyString() {
        return "(! " + subExp().toPrettyString() + ")";
    }
}
