package edu.postech.csed490h.satsolving;

/**
 * A Boolean literal, given by the identifier of a Boolean variable
 *
 * @param identifier the identifier of a Boolean variable
 * @param value      the truth value of the literal
 */
public record Literal(int identifier, boolean value) {

    /**
     * Returns the negation of this literal
     *
     * @return the negation of this literal
     */
    public Literal negate() {
        return new Literal(identifier, !value);
    }
}
