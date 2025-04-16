package edu.postech.csed409h.figure;

public class Oval extends Circle {
    private double radiusL, radiusS;

    public double getLongRadius() {
        return radiusL;
    }

    public double getShortRadius() {
        return radiusS;
    }

    public void setLongRadius(double _radiusL) {
        radiusL = _radiusL;
    }

    public void setShortRadius(double _radiusS){
        radiusS = _radiusS;
    }

    public double getArea() {
        return Math.PI * radiusL * radiusS;
    }

    public double getPerimeter() {
        return 2 * Math.PI * Math.sqrt((radiusL * radiusL + radiusS * radiusS) / 2);
    }

    public void draw(){
        System.out.println("Drawing Oval...");
    }

}
