package edu.postech.csed409h;

import edu.postech.csed409h.figure.*;

public class FigureMain {

    public static void main(String[] args){
        FigureFactory factory = new FigureFactory();

        Rectangle rectangle = (Rectangle) factory.createFigure("Rectangle");
        rectangle.draw();
        rectangle.setHeight(1);
        rectangle.setWidth(2);
        System.out.println(((Rectangle) rectangle).getArea());

        Oval oval = (Oval) factory.createFigure("Oval");
        oval.draw();
        oval.setLongRadius(1);
        oval.setShortRadius(2);
        System.out.println(((Oval) oval).getArea());

        Square square = (Square) factory.createFigure("Square");
        square.draw();
        square.setHeight(1);
        square.setWidth(1);
        System.out.println(((Square) square).getArea());

        RtTriangle rttriangle = (RtTriangle) factory.createFigure("RtTriangle");
        rttriangle.draw();
        rttriangle.setHeight(1);
        rttriangle.setWidth(1);
        System.out.println(((RtTriangle) rttriangle).getArea());


    }
}
