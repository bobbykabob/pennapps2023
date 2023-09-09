package com.pennhacks.ecolens.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.relational.core.sql.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class TrashCan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(mappedBy = "trashCan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="trashCan_id")
    private List<TrashCanItem> currentTrashCanItems = new ArrayList<>();

    @OneToMany(mappedBy = "trashCan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="trashCan_id")
    private List<TrashCanItem> lifetimeTrashCanItems = new ArrayList<>();

    public void addCurrentTrashCanItem(TrashCanItem item){
        currentTrashCanItems.add(item);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
