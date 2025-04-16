package edu.postech.csed409h.phone;

public class Vendor {
    public static void main(String args[]){
        GraphicsMemory graphicsMemory = new GraphicsMemory("4 GB", true); // Example dedicated memory value
        GraphicsCard graphicsCard = new GraphicsCard(graphicsMemory);
        SmartPhone smartPhone = new SmartPhone(graphicsCard, null); // The second parameter is for the Camera; you can pass null for this example.

        SmartPhoneManager manager = new SmartPhoneManager();
        manager.printSize(smartPhone);
    }
}
