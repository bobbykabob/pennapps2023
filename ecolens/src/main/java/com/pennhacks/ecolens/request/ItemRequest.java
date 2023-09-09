package com.pennhacks.ecolens.request;

/**
 * JSON request payload passed in from
 * python opencv stream containing camera data
 */
public class ItemRequest {
    private String name;
    private double confidence;

    public ItemRequest(String name, double confidence) {
        this.name = name;
        this.confidence = confidence;
    }

    public ItemRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }


    public String toString() {
        return "ItemRequest{" +
                "name='" + name + '\'' +
                ", confidence=" + confidence +
                '}';
    }
}
