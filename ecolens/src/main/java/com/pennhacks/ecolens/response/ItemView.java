package com.pennhacks.ecolens.response;

public class ItemView {
    private String name;
    private String description;
    private boolean recyclable;

    public ItemView(String name, String description, boolean recyclable) {
        this.name = name;
        this.description = description;
        this.recyclable = recyclable;
    }

    public ItemView(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public ItemView() {
    }

    public boolean isRecyclable() {
        return recyclable;
    }

    public void setRecyclable(boolean recyclable) {
        this.recyclable = recyclable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "ItemView{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", recyclable=" + recyclable +
                '}';
    }
}
