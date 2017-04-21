/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sliding.puzzle;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author rabi
 */
public class SlidingPuzzle {
    State initialState;
    State goalState;
    
    public SlidingPuzzle(int initialBoard[][],
            int goalBoard[][]) {
        initialState = new State(initialBoard);
        goalState = new State(goalBoard);
    }
    
    private void printPath(State state) {
        if (state.getParent() != null)
            printPath(state.getParent());
        System.out.println(state);
    }
    
    public void doBFS() {
        boolean isExplored[] = new boolean[987654322];
        boolean goalFound = false;
        State finalState = null;
        Queue<State> queue = new LinkedList<>();
        queue.add(initialState);
        isExplored[initialState.mapToInteger()] = true;
        
        while (!queue.isEmpty() && !goalFound) {
            State currentState = queue.poll();
            if (currentState == null)
                break;
            for (Action action: Action.values()) {
                State nextState = currentState.nextState(action);
                if (nextState == null)
                    continue;
                if (isGoal(nextState)) {
                    goalFound = true;
                    finalState = nextState;
                    break;
                }
                int map = nextState.mapToInteger();
                if (!isExplored[map]) {
                    queue.add(nextState);
                    isExplored[map] = true;
                }
            }
        }
        if (goalFound) {
            System.out.println("Found a solution!");
            printPath(finalState);
        } else {
            System.out.println("No solution!");
        }
    }
    
    public boolean isGoal(State state) {
        return state.equals(goalState);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int initialBoard[][] = {{1, 2, 3}, {4, 5, 6}, {9, 7, 8}};
//        int initialBoard[][] = {{8, 6, 7}, {2, 5, 4}, {3, 9, 1}};
        int goalBoard[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        SlidingPuzzle slidingPuzzle = new SlidingPuzzle(initialBoard, goalBoard);
        slidingPuzzle.doBFS();
    }
    
}
