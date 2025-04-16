package edu.postech.csed409h.figure;

import edu.postech.csed409h.Figure;

public class Rectangle extends QuadRangle {

    protected double width, height;

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }

    public void setHeight(double _height){
        height = _height;
    }

    public void setWidth(double _width){
        width = _width;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return width * 2 + height * 2;
    }

    public void resize(double x) {
        width = width * x;
        height = height * x;
    }
    public void draw(){
        System.out.println("Drawing Rectangle...");
    }

}
