package edu.postech.csed490h.hoppingrabbitsgame;

import org.junit.jupiter.api.Test;

import static edu.postech.csed490h.hoppingrabbitsgame.Rabbit.X;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void testTwoInitial() {
        var game = GameState.makeInitialState(2);
        assertEquals("xx_oo", game.toString());
    }

    @Test
    void testTwoOneStep() {
        var game = GameState.makeInitialState(2);
        assertEquals("x_xoo", game.move(X).toString());
    }
}