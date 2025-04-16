package edu.postech.csed409h.figure;

import edu.postech.csed409h.Figure;

public abstract class Triangle implements Figure {
    public int getSide() {return 3;}

    public void darw() { System.out.println("Drawing Triangle..."); }
}
