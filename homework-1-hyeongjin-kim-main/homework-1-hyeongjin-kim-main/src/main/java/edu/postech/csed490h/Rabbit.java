package edu.postech.csed490h;


/**
 * A rabbit in the hopping rabbits game.
 */
public enum Rabbit {
    X, O;

    public Rabbit other() {
        return this == X ? O : X;
    }
}
