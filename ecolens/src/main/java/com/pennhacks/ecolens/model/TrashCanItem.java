package com.pennhacks.ecolens.model;

import jakarta.persistence.*;

/**
 *  Purpose of TrashCanItem Class
 *     Association Between TrashCan and Item:
 *
 *     Establish a Many-to-One relationship between the TrashCan and Item entities.
 *     This means that each instance of TrashCanItem is associated with a specific TrashCan and a specific Item.
 *
 *     Quantity Tracking:
 *
 *     The quantity attribute within the TrashCanItem class represents the quantity of a particular Item
 *     within the associated TrashCan. This quantity tracking is essential for keeping count of how many of
 *     each item is currently present in the trash can.
 *
 *     Distinguishing Current and Lifetime Items:
 *
 *     IsCurrentItem differentiates between current and lifetime items
 *     within the TrashCan. This attribute helps in categorizing items based on whether they are currently
 *     in the trash can or represent a historical record of items that have been in the trash can over time.
 *
 *     Historical Tracking:
 *
 *     The TrashCanItem class can also serve as a historical record of items that have
 *     been in the trash can over time. By keeping a record of both current and lifetime items, you can analyze trends
 *     or patterns in item disposal and management.
 *
 *     Data Integrity and Consistency:
 *
 *     The TrashCanItem class helps maintain data integrity and consistency by ensuring that the quantity of each
 *     item within a TrashCan is accurately recorded and associated with the corresponding TrashCan and Item.
 *     It prevents duplication and enforces referential integrity.
 */
@Entity
public class TrashCanItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private TrashCan trashCan;

    @ManyToOne
    private Item item;

    private int quantity;

    public TrashCanItem(TrashCan trashCan, Item item, int quantity) {
        this.trashCan = trashCan;
        this.item = item;
        this.quantity = quantity;
    }

    public TrashCanItem() {

    }

    public String getItemDescription(){
        return item.getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TrashCan getTrashCan() {
        return trashCan;
    }

    public void setTrashCan(TrashCan trashCan) {
        this.trashCan = trashCan;
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
}
