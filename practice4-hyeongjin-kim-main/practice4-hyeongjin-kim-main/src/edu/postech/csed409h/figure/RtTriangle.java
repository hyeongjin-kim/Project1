package edu.postech.csed409h.figure;

public class RtTriangle extends Triangle{

    private double width, height;

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public void setWidth(double _width){
        width = _width;
    }

    public void setHeight(double _height){
        height = _height;
    }

    private double getHypotenuse(){
        return Math.sqrt(width * width + height * height);
    }

    public double getArea() {
        return (width * height) / 2;
    }

    public double getPerimeter() {
        return width + height + Math.sqrt(width * width + height * height);
    }

    public void draw(){
        System.out.println("Drawing Right Triangle...");
    }
}
