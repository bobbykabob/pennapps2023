package com.pennhacks.ecolens.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class CurrentTrashCanItem{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "currentTrashCanId")
    private TrashCan currentTrashCan;

    @ManyToOne
    private Item item;
    private int quantity;

    public CurrentTrashCanItem(TrashCan currentTrashCan, Item item, int quantity) {
        this.currentTrashCan = currentTrashCan;
        this.item = item;
        this.quantity = quantity;
    }

    public CurrentTrashCanItem() {

    }

    
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }


    public TrashCan getTrashCan() {
        return currentTrashCan;
    }


    public void setTrashCan(TrashCan currentTrashCan) {
        this.currentTrashCan = currentTrashCan;
    }


    public Item getItem() {
        return item;
    }

    
    public void setItem(Item item) {
        this.item = item;
    }

    
    public int getQuantity() {
        return quantity;
    }

    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Add any additional methods or functionality specific to CurrentTrashCanItem here
}
