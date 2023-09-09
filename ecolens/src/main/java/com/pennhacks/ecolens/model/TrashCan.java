package com.pennhacks.ecolens.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pennhacks.ecolens.exception.ItemNotFoundException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TrashCan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    private boolean isRecycle;
    @OneToMany(mappedBy = "currentTrashCan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<CurrentTrashCanItem> currentTrashCanItems = new ArrayList<>();

    @OneToMany(mappedBy = "lifetimeTrashCan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<LifetimeTrashCanItem> lifetimeTrashCanItems = new ArrayList<>();


    public TrashCan(boolean isRecycle, List<CurrentTrashCanItem> currentTrashCanItems,
                    List<LifetimeTrashCanItem> lifetimeTrashCanItems) {
        this.isRecycle = isRecycle;
        this.currentTrashCanItems = currentTrashCanItems;
        this.lifetimeTrashCanItems = lifetimeTrashCanItems;
    }

    public TrashCan(boolean isRecycle) {
        this.isRecycle = isRecycle;
    }

    public TrashCan() {
    }

    public List<CurrentTrashCanItem> getCurrentTrashCanItems(){
        return currentTrashCanItems;
    }

    public List<LifetimeTrashCanItem> getLifetimeTrashCanItems(){
        return lifetimeTrashCanItems;
    }

    public Item getItemByName(String name) throws ItemNotFoundException {
        for (CurrentTrashCanItem item : currentTrashCanItems) {
            if (item.getItem().getName().equals(name)) {
                return item.getItem();
            }
        }
        throw new ItemNotFoundException("Item with name '" + name + "' not found");
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentTrashCanItems(List<CurrentTrashCanItem> currentTrashCanItems) {
        this.currentTrashCanItems = currentTrashCanItems;
    }

    public void setLifetimeTrashCanItems(List<LifetimeTrashCanItem> lifetimeTrashCanItems) {
        this.lifetimeTrashCanItems = lifetimeTrashCanItems;
    }
}
