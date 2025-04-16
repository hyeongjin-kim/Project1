package edu.postech.csed409h.figure;

import edu.postech.csed409h.Figure;

public abstract class Circle implements Figure {

    private double radius;

     final public double getRadius(){
        return radius;
    }

    final public void setRadius(double _radius){
        radius = _radius;
    }


    public double getArea() {
        return radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public void draw(){
        System.out.println("Drawing Circle...");
    }
}
