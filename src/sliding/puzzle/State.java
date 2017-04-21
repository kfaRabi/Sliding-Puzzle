/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sliding.puzzle;

import java.util.Arrays;

/**
 *
 * @author rabi
 */
public class State {
    private int board[][];
    private State parent;
    private int blankRow;
    private int blankCol;
    private Action action;
    int step;
    
    public State(int board[][]) {
        this.board = new int[board.length][];
        for (int r = 0; r < board.length; r++) {
            this.board[r] = new int[board[r].length];
            for (int c = 0; c < board[r].length; c++) {
                this.board[r][c] = board[r][c];
                if (board[r][c] == board.length * board[r].length) {
                    blankRow = r;
                    blankCol = c;
                }
            }
        }
        parent = null;
        action = null;
        step = 0;
    }

    public void setCell(int row, int col, int value) {
        board[row][col] = value;
    }
    
    public State nextState(Action action) {
        State state = new State(board);
        int nextRow = blankRow + action.getDy();
        int nextCol = blankCol + action.getDx();
        if (nextRow < 0 || nextCol < 0 
                || nextRow >= board.length || nextCol >= board[0].length)
            return null;
        state.setCell(nextRow, nextCol, board[blankRow][blankCol]);
        state.setCell(blankRow, blankCol, board[nextRow][nextCol]);
        state.blankRow = nextRow;
        state.blankCol = nextCol;
        state.parent = this;
        state.step = this.step + 1;
        state.action = action;
        return state;
    }

    public State getParent() {
        return parent;
    }
    
    
    @Override
    public String toString() {
        String output = "Step " + step +  " (" + action +")\n";
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++)
                if (board[r][c] == board.length * board[r].length)
                    output += " XX";
                else output += String.format("%3d", board[r][c]);
            output += "\n";
        }
        return output;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public int mapToInteger() {
        int value = 0;
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[r].length; c++) {
                value = value * 10;
                value = value + board[r][c];
            }
        return value;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (!Arrays.deepEquals(this.board, other.board)) {
            return false;
        }
        return true;
    }
    
    
}
