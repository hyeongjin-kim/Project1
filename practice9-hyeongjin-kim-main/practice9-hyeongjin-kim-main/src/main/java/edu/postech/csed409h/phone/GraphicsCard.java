package edu.postech.csed409h.phone;

public class GraphicsCard {
    private GraphicsMemory graphicsMemory;

    public GraphicsCard(GraphicsMemory graphicsMemory) {
        this.graphicsMemory = graphicsMemory;
    }

    public GraphicsMemory getGraphicsMemory() {
        return graphicsMemory;
    }
}
