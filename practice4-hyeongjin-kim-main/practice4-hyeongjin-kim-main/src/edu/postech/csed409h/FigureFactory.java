package edu.postech.csed409h;

import edu.postech.csed409h.figure.*;

public class FigureFactory {
    public Figure createFigure(String type){
        Figure figure = null;
        switch(type){
            case "Oval" -> figure = new Oval();
            case "Rectangle" -> figure = new Rectangle();
            case "RtTriangle" -> figure = new RtTriangle();
            case "Square" -> figure = new Square();
        }
        return figure;
    }
}
