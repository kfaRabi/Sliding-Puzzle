/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sliding.puzzle;

/**
 *
 * @author rabi
 */
public enum Action {
    LEFT    (+0, -1),
    RIGHT   (+0, +1),
    UP      (-1, +0),
    DOWN    (+1, +0);
    
    private int dy;
    private int dx;
    
    Action(int dy, int dx) {
        this.dy = dy;
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }
}
