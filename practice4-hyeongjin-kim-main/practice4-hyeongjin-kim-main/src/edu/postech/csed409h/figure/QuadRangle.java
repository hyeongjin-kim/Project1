package edu.postech.csed409h.figure;

import edu.postech.csed409h.Figure;

public abstract class QuadRangle implements Figure {

    public int getSide() {return 4;}

    public void draw(){
        System.out.println("Drawing QuadRangle...");
    }
}
