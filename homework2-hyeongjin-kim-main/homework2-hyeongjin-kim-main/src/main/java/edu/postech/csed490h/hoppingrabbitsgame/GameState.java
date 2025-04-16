package edu.postech.csed490h.hoppingrabbitsgame;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A class that represents the state of the hopping rabbits game. The state of the
 * game is represented as an array of rabbits, where the empty position is EMPTY.
 */
public class GameState {
    /**
     * The state of the game, represented as an array of rabbits, where
     * the empty position is represented as EMPTY.
     */
    private final Rabbit[] rabbits;

    //TODO: feel free to add any private fields and methods to implement this class,
    // if necessary. But do not change the signature of the public methods, or do
    // not add any public method, including constructors.

    /**
     * Create an empty state of the game, where the number of rabbits in each
     * team is n. This constructor is private, and thus, it can be called only
     * from the static method makeInitialState and the method move.
     */
    private GameState(int n) {
        rabbits = new Rabbit[2 * n + 1];

        //TODO: feel free to add any other initialization code, if necessary.
    }

    /**
     * Return the initial state of the game, where the number of rabbits in each
     * team is n. For example, if n = 3, the initial state is xxx_yyy, where x
     * represents a rabbit from the x team, y represents a rabbit from the o team,
     * and _ represents an empty position.
     *
     * @param n the number of rabbits on each team
     * @return the initial state of the game
     */
    static GameState makeInitialState(int n) {
        GameState state = new GameState(n);
        for (int i = 0; i < n; i++)
            state.rabbits[i] = Rabbit.X;
        for (int i = n + 1; i < 2 * n + 1; i++)
            state.rabbits[i] = Rabbit.O;
        state.rabbits[n] = Rabbit.EMPTY;

        //TODO: feel free to add any other code, if necessary.
        return state;
    }

    private GameState makestate(Rabbit[] rabbits){
        GameState gameState = new GameState(rabbits.length/2);
        System.arraycopy(rabbits, 0, gameState.rabbits, 0, rabbits.length);
        return gameState;
    }

    /**
     * Return the game state after moving a rabbit to an empty position. Rabbits
     * from the x team can only move to the right, and rabbits from the o team can
     * only move to the left. A rabbit is allowed to advance one position if that
     * position is empty. A rabbit can jump over a rival if the position behind the
     * rival is empty. For example, if the current state is xxxo_oo, the x team can
     * move to xx_oxoo.
     *
     * @param rabbit a rabbit
     * @return the game state after moving rabbit to an empty position, or null if
     * the move is not possible
     */
    GameState move(Rabbit rabbit) {
        //TODO: implement this
        int possible_rabbit = rabbitcanmove(rabbit);
        if(possible_rabbit == -1){
            return null;
        }
        else{
            Rabbit[] newstate = new Rabbit[rabbits.length];
            System.arraycopy(rabbits, 0, newstate , 0, rabbits.length);
            newstate[getEmptyPosition()] = rabbit;
            newstate[possible_rabbit] = Rabbit.EMPTY;
            return makestate(newstate);
        }
    }
    int rabbitcanmove(Rabbit rabbit){
        int emptyspace = getEmptyPosition();
        if(rabbit == Rabbit.X){
            if(emptyspace > 0 && rabbits[emptyspace - 1] == Rabbit.X){
                return emptyspace - 1;
            }
            else if(emptyspace > 1 && rabbits[emptyspace - 2] == Rabbit.X && rabbits[emptyspace - 1] == Rabbit.O){
                return emptyspace - 2;
            }
        }
        else if(rabbit == Rabbit.O){
            if(emptyspace < rabbits.length - 1 && rabbits[emptyspace + 1] == Rabbit.O){
                return emptyspace + 1;
            }
            else if(emptyspace < rabbits.length - 2 && rabbits[emptyspace + 2] == Rabbit.O && rabbits[emptyspace + 1] == Rabbit.X){
                return emptyspace + 2;
            }
        }
        return -1;
    }

    
    /**
     * Return the empty position of the board.
     *
     * @return the empty position of the board
     */
    int getEmptyPosition() {
        //TODO: implement this
        for(int i = 0; i < rabbits.length; i++){
            if(rabbits[i] == Rabbit.EMPTY){
                return i;
            }
        }
        return -1;
    }

    /**
     * Return true if the game is stuck. The game is stuck if no rabbit can move.
     *
     * @return true if the game is stuck, false otherwise
     */
    boolean isStuck() {
        //TODO: implement this
        return move(Rabbit.O) == null && move(Rabbit.X) == null;
    }

    /**
     * Return true if the game is over. The game is over if the two teams have swapped
     * their initial positions: e.g., ooo_xxx, when N = 3.
     *
     * @return true if the game is over, false otherwise
     */
    boolean isGoal() {
        //TODO: implement this
        int n = rabbits.length/2;
        for(int i = 0; i < rabbits.length; i++){
            if( i < n && rabbits[i] == Rabbit.X){
                return false;
            }
            if( i == n && rabbits[i] != Rabbit.EMPTY){
                return false;
            }
            if( i > n && rabbits[i] == Rabbit.O){
                return false;
            }
        }

        return true;
    }

    /**
     * Return the string representation of the game state. The string has length 2N + 1
     * and consists of x, o, and _.
     *
     * @return the string representation of the game state
     */
    @Override
    public String toString() {
        return Arrays.stream(rabbits).map(rabbit -> switch (rabbit) {
            case X -> "x";
            case O -> "o";
            case EMPTY -> "_";
        }).collect(Collectors.joining());
    }
}