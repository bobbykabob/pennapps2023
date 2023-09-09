package com.pennhacks.ecolens.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.relational.core.sql.In;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class TrashCan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(mappedBy = "trashCan", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKeyJoinColumn(name = "item_id")
    @JsonManagedReference
    private Map<Item, Integer> currentItems;

    @ElementCollection
    @OneToMany(mappedBy = "trashCan", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKeyJoinColumn(name = "item_id")
    private Map<Item, Integer> lifetimeItems;


}
