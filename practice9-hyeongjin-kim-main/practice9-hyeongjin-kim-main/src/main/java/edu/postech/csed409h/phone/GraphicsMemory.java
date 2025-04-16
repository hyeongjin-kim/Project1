package edu.postech.csed409h.phone;

public class GraphicsMemory {
    private String dedicatedMemory;
    private boolean isMemoryValid;

    public GraphicsMemory(String dedicatedMemory, boolean validMemory) {
        this.dedicatedMemory = dedicatedMemory;
        this.isMemoryValid = validMemory;
    }

    public String getDedicatedMemory() {
        return dedicatedMemory;
    }

    public boolean isMemoryValid() {
        return isMemoryValid;
    }
}