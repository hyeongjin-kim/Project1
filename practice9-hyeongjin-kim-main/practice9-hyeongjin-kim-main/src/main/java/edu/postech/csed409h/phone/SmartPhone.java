package edu.postech.csed409h.phone;

import java.util.Optional;

public class SmartPhone {
    private GraphicsCard graphicsCard;
    private Camera camera;

    public SmartPhone(GraphicsCard graphicsCard, Camera camera) {
        this.graphicsCard = graphicsCard;
        this.camera = camera;
    }

    public GraphicsCard getGraphicsCard() {
        return graphicsCard;
    }

    public Camera getCamera() {
        return camera;
    }

    @Override
    public String toString() {
        return "SmartPhone";
    }

    private String getExtraInfo(SmartPhone smartPhone){
        if (Optional.ofNullable(smartPhone.getCamera()).isPresent()) {
            Camera camera = smartPhone.getCamera();
            if (Optional.ofNullable(camera.getCameraFeatures()).isPresent()) {
                return ", Camera Features: " + camera.getCameraFeatures();
            }
        }
        return "";
    }


}
