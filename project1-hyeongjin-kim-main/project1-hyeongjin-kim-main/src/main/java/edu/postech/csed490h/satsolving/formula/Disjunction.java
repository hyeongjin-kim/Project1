package edu.postech.csed490h.satsolving.formula;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A Boolean expression whose top-level operator is || (or).
 *
 * @param subExps sub-expressions
 */
public record Disjunction(Exp... subExps) implements Exp {

    @Override
    public @NotNull Set<Integer> vars() {
        return Arrays.stream(subExps()).flatMap(e -> e.vars().stream())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean eval(@NotNull Map<Integer, Boolean> assignment) {
        return Arrays.stream(subExps()).anyMatch(e -> e.eval(assignment));
    }

    @Override
    public @NotNull String toPrettyString() {
        return "(" + Arrays.stream(subExps()).map(Exp::toPrettyString)
                .collect(Collectors.joining(" || ")) + ")";
    }
}