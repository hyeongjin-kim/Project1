package edu.postech.csed409h.phone;

import java.util.NoSuchElementException;
import java.util.Optional;

public class SmartPhoneManager {

    private String getExtraInfo(SmartPhone smartPhone) {
      return Optional.of(smartPhone).map(SmartPhone::getCamera).map(Camera::getCameraFeatures).map(c->{ return ", Camera Features: " + c;}).orElse("");
    }

    public void printSize(SmartPhone smartPhone) {
        String size = "unknown";
        if(Optional.ofNullable(smartPhone).isPresent()) {
            if (Optional.of(smartPhone).map(SmartPhone::getGraphicsCard).map(GraphicsCard::getGraphicsMemory).filter(GraphicsMemory::isMemoryValid).map(GraphicsMemory::getDedicatedMemory).isPresent()) {
                String extraInfo = getExtraInfo(smartPhone);
                size = smartPhone.getGraphicsCard().getGraphicsMemory().getDedicatedMemory() + extraInfo;
            }
        }
        System.out.println("Size: " + size + ", for Object: " + (smartPhone != null ? smartPhone.toString() : "null"));
    }
}
