package edu.postech.csed490h.hoppingrabbitsgame;

import edu.postech.csed490h.numbertranslator.EnglishNumberTranslator;

import java.nio.channels.GatheringByteChannel;
import java.util.*;

/**
 * Two teams of N rabbits each are placed facing each other in a row with 2N + 1
 * positions. Initially, the x team occupies the first N positions, the o team
 * occupies the last N positions, and the middle position is empty. The goal is
 * to swap the positions of the two teams by moving the rabbits. A rabbit can
 * move to an empty position or jump over a rival to an empty position.
 */
public class GameSolver {
    //TODO: feel free to add any private fields and methods to implement this class.

    private List<GameState> solution;
    private GameState initialState;
    private Set<GameState> visitedStates;
    private Queue<GameState> queue;
    /**
     * Create a game solver for a given number of rabbits on each team.
     * The number of rabbits on each team should be at least 1.

     * @param n the number of rabbits on each team
     */
    GameSolver(int n) {
        //TODO: implement this method
        solution = new ArrayList<>();
        initialState = GameState.makeInitialState(n);
        visitedStates = new HashSet<>();
        queue = new LinkedList<>();
    }

    /**
     * Return a solution to the game. If there is no solution, return null. A solution
     * is represented as a list of game states, where the first element is the initial
     * state, and the last element is the goal state. For example, consider n = 2.
     * Then, the (potential) solution is as follows: xx_oo -> x_xoo -> xox_o -> xoxo_
     * -> xo_ox -> _oxox -> o_xox -> oox_x -> oo_xx.
     *
     * @param n the number of rabbits on each team
     * @return a list of game states that represents a solution to the game, starting
     * from the initial state. If there is no solution, return null.
     */
    List<GameState> solve() {

        //TODO: implement this method
        //Hint: you may use recursion (using another recursive private method), or use
        // a queue or a stack to store game states.
        queue.offer(initialState);
        visitedStates.add(initialState);

        while (!queue.isEmpty()) {
            GameState currentState = queue.poll();
            solution.add(currentState);

            if (currentState.isGoal()) {
                break;
            }

            // 현재 상태에서 가능한 모든 이동을 생성하고 검사
            for (Rabbit rabbit : Rabbit.values()) {
                if (currentState.move(rabbit) != null) {
                        GameState nextState = currentState.move(rabbit);
                        if (!visitedStates.contains(nextState)) {
                            queue.offer(nextState);
                            visitedStates.add(nextState);
                        }
                }
            }
        }

        return solution;
    }

    public static void main(String[] args) {
        GameSolver gameSolver = new GameSolver(2);
        List<GameState> list = gameSolver.solve();
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).toString());
        }
    }
}
