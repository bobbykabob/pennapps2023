package com.pennhacks.ecolens.service;

import com.pennhacks.ecolens.exception.TrashCanNotFoundException;
import com.pennhacks.ecolens.model.*;
import com.pennhacks.ecolens.repository.ItemRepository;
import com.pennhacks.ecolens.repository.CurrentTrashCanItemRepository;
import com.pennhacks.ecolens.repository.LifetimeTrashCanItemRepository;
import com.pennhacks.ecolens.repository.TrashCanRepository;
import org.springframework.stereotype.Service;

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
        Item item1 = new Item("Circular Lid", true, "Made of polypropylene (PP)" +
                ", these types of plastics are known for their recyclability.");
        Item item2 = new Item("Hot Cup", false, "Hot cups' plastic coating" +
                " makes recycling more complex.");

        itemRepo.save(item1);
        itemRepo.save(item2);

// Create TrashCanItem instances for currentTrashCanItems
        CurrentTrashCanItem  circularLid1 = new CurrentTrashCanItem(trashCan, item1, 0);
        CurrentTrashCanItem hotCup1 = new CurrentTrashCanItem(trashCan, item2, 0);

// Create TrashCanItem instances for lifetimeTrashCanItems
        LifetimeTrashCanItem circularLid2 = new LifetimeTrashCanItem(trashCan, item1, 0);
        LifetimeTrashCanItem hotCup2 = new LifetimeTrashCanItem(trashCan, item2, 0);

        trashCan.getCurrentTrashCanItems().add(circularLid1);
        trashCan.getCurrentTrashCanItems().add(hotCup1);
        trashCan.getLifetimeTrashCanItems().add(circularLid2);
        trashCan.getLifetimeTrashCanItems().add(hotCup2);
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
                System.out.println(item.getItem().getName());
                item.setQuantity(item.getQuantity() + 1);
                // Save the updated TrashCanItem to persist the quantity change
                currentTrashCanItemRepo.save(item);
            }
        }
        // Save the TrashCan itself to update its associations
        trashCanRepo.save(trashCan);
    }

    public void getItemDescription(String itemName){

    }
}
