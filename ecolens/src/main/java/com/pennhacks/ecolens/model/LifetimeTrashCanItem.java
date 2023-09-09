package com.pennhacks.ecolens.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class LifetimeTrashCanItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "lifetimeTrashCanId")
    private TrashCan lifetimeTrashCan;

    @ManyToOne
    private Item item;
    private int quantity;

    public LifetimeTrashCanItem(TrashCan lifetimeTrashCan, Item item, int quantity) {
        this.lifetimeTrashCan = lifetimeTrashCan;
        this.item = item;
        this.quantity = quantity;
    }

    public LifetimeTrashCanItem() {

    }

    
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    
    public TrashCan getTrashCan() {
        return lifetimeTrashCan;
    }

    
    public void setTrashCan(TrashCan lifetimeTrashCan) {
        this.lifetimeTrashCan = lifetimeTrashCan;
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

    // Add any additional methods or functionality specific to LifetimeTrashCanItem here
}
