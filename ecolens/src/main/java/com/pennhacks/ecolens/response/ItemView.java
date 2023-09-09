package com.pennhacks.ecolens.response;

public class ItemView {
    private String name;
    private String description;

    public ItemView(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ItemView() {
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

    
    public String toString() {
        return "ItemView{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
