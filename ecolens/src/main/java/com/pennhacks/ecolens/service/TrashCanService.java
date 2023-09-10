package com.pennhacks.ecolens.service;

import com.pennhacks.ecolens.exception.TrashCanNotFoundException;
import com.pennhacks.ecolens.model.*;
import com.pennhacks.ecolens.repository.ItemRepository;
import com.pennhacks.ecolens.repository.CurrentTrashCanItemRepository;
import com.pennhacks.ecolens.repository.LifetimeTrashCanItemRepository;
import com.pennhacks.ecolens.repository.TrashCanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrashCanService {
    private final TrashCanRepository trashCanRepo;
    private final ItemRepository itemRepo;
    private final CurrentTrashCanItemRepository currentTrashCanItemRepo;
    private final LifetimeTrashCanItemRepository lifetimeTrashCanItemRepo;

    public TrashCanService(TrashCanRepository trashCanRepo,
                           ItemRepository itemRepo,
                           CurrentTrashCanItemRepository currentTrashCanItemRepo,
                           LifetimeTrashCanItemRepository lifetimeTrashCanItemRepo) {
        this.trashCanRepo = trashCanRepo;
        this.itemRepo = itemRepo;
        this.currentTrashCanItemRepo = currentTrashCanItemRepo;
        this.lifetimeTrashCanItemRepo = lifetimeTrashCanItemRepo;
    }

    public void initializeTrashCanItems(TrashCan trashCan){
// Create Circular Lid and Hot Cup items
        Item item1 = new Item("lid", true, "Paper is a recyclable material, and it can" +
                " be processed into new paper products during recycling.");
        Item item2 = new Item("paper_cup", true, "Paper is inherently recyclable, and it can be broken" +
                " down and processed into new paper products.");
        Item item3 = new Item("box", false, "Styrofoam is not biodegradable and can" +
                " harm the environment.");
        Item item4 = new Item("spherical_lid", false,"" );
        Item item5 = new Item("straw",false, "Plastic straws, when not properly disposed of," +
                " can contribute to plastic pollution in the environment");
        Item item6 = new Item("paper_bag", true, " These lids are often made from" +
                " polystyrene (PS) and are not accepted for recycling.");

        itemRepo.save(item1);
        itemRepo.save(item2);
        itemRepo.save(item3);
        itemRepo.save(item4);
        itemRepo.save(item5);
        itemRepo.save(item6);

// Create TrashCanItem instances for currentTrashCanItems
        CurrentTrashCanItem lid1 = new CurrentTrashCanItem(trashCan, item1, 0);
        CurrentTrashCanItem cup1 = new CurrentTrashCanItem(trashCan, item2, 0);
        CurrentTrashCanItem box1 = new CurrentTrashCanItem(trashCan, item3, 0);
        CurrentTrashCanItem spherical_lid1 = new CurrentTrashCanItem(trashCan, item4, 0);
        CurrentTrashCanItem straw1 = new CurrentTrashCanItem(trashCan, item5, 0);
        CurrentTrashCanItem paper_bag1 = new CurrentTrashCanItem(trashCan, item6, 0);

// Create TrashCanItem instances for lifetimeTrashCanItems
        LifetimeTrashCanItem lid2 = new LifetimeTrashCanItem(trashCan, item1, 0);
        LifetimeTrashCanItem cup2 = new LifetimeTrashCanItem(trashCan, item2, 0);
        LifetimeTrashCanItem box2 = new LifetimeTrashCanItem(trashCan, item3, 0);
        LifetimeTrashCanItem spherical_lid2 = new LifetimeTrashCanItem(trashCan, item4, 0);
        LifetimeTrashCanItem straw2 = new LifetimeTrashCanItem(trashCan, item5, 0);
        LifetimeTrashCanItem paper_bag2 = new LifetimeTrashCanItem(trashCan, item6, 0);

        trashCan.getCurrentTrashCanItems().add(lid1);
        trashCan.getCurrentTrashCanItems().add(cup1);
        trashCan.getCurrentTrashCanItems().add(box1);
        trashCan.getCurrentTrashCanItems().add(spherical_lid1);
        trashCan.getCurrentTrashCanItems().add(straw1);
        trashCan.getCurrentTrashCanItems().add(paper_bag1);
        trashCan.getLifetimeTrashCanItems().add(lid2);
        trashCan.getLifetimeTrashCanItems().add(cup2);
        trashCan.getLifetimeTrashCanItems().add(box2);
        trashCan.getLifetimeTrashCanItems().add(spherical_lid2);
        trashCan.getLifetimeTrashCanItems().add(straw2);
        trashCan.getLifetimeTrashCanItems().add(paper_bag2);
// Save the updated trashCan
        trashCanRepo.save(trashCan);

    }
    /**
     * Create a new trash can
     * @param trashCan The trash can to be created
     * @return The created trash can
     */
    public TrashCan createTrashCan(TrashCan trashCan){
        TrashCan newTrashCan = trashCanRepo.save(trashCan);
        initializeTrashCanItems(newTrashCan);
        return trashCanRepo.save(newTrashCan);
    }

    /**
     * Retrieve a trashcan by its id
     * @param id The ID of the trashcan to retrieve
     * @return The retrieved trash can
     * @throws TrashCanNotFoundException If the trash can is not found
     */
    public TrashCan getTrashCan(int id){
        Optional<TrashCan> trashCanOptional = trashCanRepo.findById(id);
        return trashCanOptional.orElseThrow(
                () -> new TrashCanNotFoundException("Trash can with id{" + id + "} not found.")
        );
    }

    public List<Integer> getAllTrashCanIds(){
        List<TrashCan> trashCans = trashCanRepo.findAll();
        List<Integer> trashCanIds = new ArrayList<>();
        for(TrashCan trashCan: trashCans){
            trashCanIds.add(trashCan.getId());
        }
        return trashCanIds;
    }

    /**
     * Delete a trashcan by its id
     * @param id The ID of the trash can to delete
     * @throws TrashCanNotFoundException If the trash can is not found
     */
    public void deleteTrashCan(int id){
        TrashCan trashCan = getTrashCan(id);
        trashCanRepo.delete(trashCan);
    }

    /**
     * Dump a trashcan by its id
     * Move all currentTrashCanItems (CTCI) to lifetimeTrashCanItems (LTTC).
     * Sets all CTCI quantities to 0, and adds them to LTTC quantities by the same name.
     * @param id The ID of the trash can to dump
     */
    public void dumpTrash(int id){
        TrashCan trashCan = getTrashCan(id);
        for(int i = 0; i < trashCan.getCurrentTrashCanItems().size(); i++){
            if(trashCan.getCurrentTrashCanItems().get(i).getItem().getName().equals(
                    trashCan.getLifetimeTrashCanItems().get(i).getItem().getName()
            )){
                trashCan.getLifetimeTrashCanItems().get(i).setQuantity(
                        trashCan.getLifetimeTrashCanItems().get(i).getQuantity()
                                +  trashCan.getCurrentTrashCanItems().get(i).getQuantity()
                );
                trashCan.getCurrentTrashCanItems().get(i).setQuantity(0);
                currentTrashCanItemRepo.save(trashCan.getCurrentTrashCanItems().get(i));
            }
        }
        trashCanRepo.save(trashCan);
    }

    /**
     * Represents throwing away item(s) which
     * updates the quantity of a trashCanItem by trashCanItem ID
     * @param itemName The name of the trashCanItem to update
     */
    public void updateTrash(int trashCanId, String itemName){
        TrashCan trashCan = getTrashCan(trashCanId);
        for (CurrentTrashCanItem item : trashCan.getCurrentTrashCanItems()) {
            System.out.println(itemName);
            if (item.getItem().getName().equals(itemName)) {
                item.setQuantity(item.getQuantity() + 1);
                // Save the updated TrashCanItem to persist the quantity change
                currentTrashCanItemRepo.save(item);
            }
        }
        // Save the TrashCan itself to update its associations
        trashCanRepo.save(trashCan);
    }

    public List<TrashCan> getAllTrashCans(){
        return trashCanRepo.findAll();
    }
}
