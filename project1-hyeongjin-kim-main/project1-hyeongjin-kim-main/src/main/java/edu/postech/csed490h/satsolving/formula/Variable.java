package edu.postech.csed490h.satsolving.formula;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

/**
 * A Boolean variable, identified by positive integers
 *
 * @param identifier a positive integer
 */
public record Variable(int identifier) implements Exp {
    /**
     * The identifier of this variable must be a positive integer.
     *
     * @param identifier a positive integer
     */
    public Variable {
        if (identifier <= 0)
            throw new IllegalArgumentException("Variable Id must be a positive integer");
    }

    @Override
    public @NotNull Set<Integer> vars() {
        return Set.of(identifier());
    }

    @Override
    public boolean eval(@NotNull Map<Integer, Boolean> assignment) {
        return assignment.get(identifier());
    }

    @Override
    public @NotNull String toPrettyString() {
        return "p" + identifier();
    }
}