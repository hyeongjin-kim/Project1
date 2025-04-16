package edu.postech.csed490h.satsolving.formula;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

/**
 * A Boolean constant, either true or false.
 *
 * @param value a Boolean value
 */
public record Constant(boolean value) implements Exp {

    @Override
    public @NotNull Set<Integer> vars() {
        return Set.of();
    }

    @Override
    public boolean eval(@NotNull Map<Integer, Boolean> assignment) {
        return value;
    }

    @Override
    public @NotNull String toPrettyString() {
        return Boolean.toString(value());
    }
}