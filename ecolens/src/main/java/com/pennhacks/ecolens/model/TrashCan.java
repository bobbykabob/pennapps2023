package com.pennhacks.ecolens.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pennhacks.ecolens.exception.ItemNotFoundException;
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

    private boolean isRecycle;
    @OneToMany(mappedBy = "trashCan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrashCanItem> currentTrashCanItems = new ArrayList<>();

    @OneToMany(mappedBy = "trashCan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrashCanItem> lifetimeTrashCanItems = new ArrayList<>();


    public TrashCan(boolean isRecycle, List<TrashCanItem> currentTrashCanItems,
                    List<TrashCanItem> lifetimeTrashCanItems) {
        this.isRecycle = isRecycle;
        this.currentTrashCanItems = currentTrashCanItems;
        this.lifetimeTrashCanItems = lifetimeTrashCanItems;
    }

    public TrashCan(boolean isRecycle) {
        this.isRecycle = isRecycle;
    }

    public TrashCan() {
    }

    public List<TrashCanItem> getCurrentTrashCanItems(){
        return currentTrashCanItems;
    }

    public Item getItemByName(String name) throws ItemNotFoundException {
        for (TrashCanItem item : currentTrashCanItems) {
            if (item.getItem().getName().equals(name)) {
                return item.getItem();
            }
        }
        throw new ItemNotFoundException("Item with name '" + name + "' not found");
    }

    /**
     * Initializes possible trash items to new trash can
     */
    public void initializeTrashCanItems(){
        TrashCanItem circularLid = new TrashCanItem(
                this,
                new Item("Circular Lid",
                        true,
                        "Made of polypropylene (PP) or high-density polyethylene (HDPE)." +
                                " These types of plastics are known for their recyclability. " +
                                "The lids can be melted down and reformed into new plastic products."),
                0);
        TrashCanItem hotCup = new TrashCanItem(
                this,
                new Item("Hot Cup",
                        false,
                        "Hot cups are usually made from paperboard or cardboard materials that" +
                                " are coated with a thin layer of polyethylene plastic." +
                                " This plastic coating is added to make the cups waterproof and prevent leaks." +
                                " However, the presence of this plastic coating makes recycling more complex."),
                0);
        currentTrashCanItems.add(circularLid);
        currentTrashCanItems.add(hotCup);
        lifetimeTrashCanItems.add(circularLid);
        lifetimeTrashCanItems.add(hotCup);
    }

    /**
     * Represents throwing away item(s) which
     * updates the quantity of a trashCanItem by trashCanItem ID
     * @param id The ID of the trashCanItem to update
     */
    public void updateTrashCanItemQuantity(int id){
        TrashCanItem item =  currentTrashCanItems.get(id);
        item.setQuantity(item.getQuantity() + 1);
    }

    /**
     * Move all currentTrashCanItems (CTCI) to lifetimeTrashCanItems (LTTC).
     * Sets all CTCI quantities to 0, and adds them to LTTC quantities by the same name.
     */
    public void dumpTrash(){
        for(TrashCanItem currentItem: currentTrashCanItems){
            for(TrashCanItem lifetimeItem: lifetimeTrashCanItems){
                if(currentItem.getItem().getName().equals(lifetimeItem.getItem().getName())){
                    lifetimeItem.setQuantity(lifetimeItem.getQuantity() + currentItem.getQuantity());
                    currentItem.setQuantity(0);
                }
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
