package com.pennhacks.ecolens.controller;

import com.pennhacks.ecolens.exception.ItemNotFoundException;
import com.pennhacks.ecolens.exception.TrashCanNotFoundException;
import com.pennhacks.ecolens.model.CurrentTrashCanItem;
import com.pennhacks.ecolens.model.Item;
import com.pennhacks.ecolens.model.LifetimeTrashCanItem;
import com.pennhacks.ecolens.model.TrashCan;
import com.pennhacks.ecolens.response.*;
import com.pennhacks.ecolens.service.TrashCanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
public class TrashCanController {
    private final TrashCanService trashCanService;

    public TrashCanController(TrashCanService trashCanService){
        this.trashCanService =trashCanService;
    }

    /**
     * Create a new user
     * @return ResponseEntity containing the trash can ID
     */
    @PostMapping("/trashcan")
    public ResponseEntity<String> createTrashCan(){
        TrashCan newTrashCan = new TrashCan();
        TrashCan createdTrashCan = trashCanService.createTrashCan(newTrashCan);
        return ResponseEntity.ok("New trash can created with id: " + createdTrashCan.getId());
    }

    @DeleteMapping("/trashcan/{id}")
    public ResponseEntity<String> deleteTrashCan(@PathVariable int id){
        trashCanService.deleteTrashCan(id);
        return ResponseEntity.ok("Trash can with id: " + id + " deleted.");
    }

    /**
     * Request format: http://localhost:8080/trashcan:dump?id=1
     * @param id
     * @return
     */
    @PatchMapping("/trashcan:dump")
    public ResponseEntity<String> dumpTrashCan(@RequestParam int id ){
        trashCanService.dumpTrash(id);
        return ResponseEntity.ok("Trash can with id " + id + " successfully dumped");
    }

    /**
     * Ex URL: http://localhost:8080/trashcan/1?itemName=Hot%20Cup
     * @param trashCanId
     * @param itemName
     * @return
     */
    @PatchMapping("/trashcan/{trashCanId}")
    public ResponseEntity<String> updateTrashCanItems(@PathVariable int trashCanId, @RequestParam("itemName") String itemName){
        trashCanService.updateTrash(trashCanId, itemName);
        return ResponseEntity.ok("Trash can with id " + trashCanId + " added item: " + itemName +".");
    }

    @GetMapping("/trashcan")
    public ResponseEntity<?> getItemDescription(@RequestParam("itemName") String itemName) {
        try {
            // Your logic to retrieve the item by itemName
            TrashCan trashCan = new TrashCan();
            trashCanService.initializeTrashCanItems(trashCan);
            Item item = trashCan.getItemByName(itemName);

            // Assuming you have an ItemView class to represent the item's information
            ItemView itemView = new ItemView(item.getName(), item.getDescription());

            return ResponseEntity.ok(itemView);
        } catch (ItemNotFoundException e) {
            // Handle the case where the item is not found
            ItemErrorResponse errorResponse = new ItemErrorResponse(
                    ZonedDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "/trashcan", // Adjust the path to match the mapping
                    "Item not found: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (TrashCanNotFoundException e) {
            // Handle the case where the trash can is not found
            TrashCanErrorResponse errorResponse = new TrashCanErrorResponse(
                    ZonedDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "/trashcan", // Adjust the path to match the mapping
                    "Trash Can not found: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/trashcan/{trashCanId}")
    public ResponseEntity<?> getTrashCanItems(@PathVariable int trashCanId){
        try {
            TrashCan trashCan = trashCanService.getTrashCan(trashCanId);

            // Assuming you have an ItemView class to represent the item's information
            TrashCanView trashCanView = new TrashCanView(
                    trashCan.getCurrentTrashCanItems(),
                    trashCan.getLifetimeTrashCanItems()
            );

            return ResponseEntity.ok(trashCanView);
        }catch (TrashCanNotFoundException e) {
            // Handle the case where the trash can is not found
            TrashCanErrorResponse errorResponse = new TrashCanErrorResponse(
                    ZonedDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "/{trashCanId}",
                    "Trash Can not found: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/trashcan/{current}/{trashCanId}")
    public ResponseEntity<?> getCurrentTrashTypesById(@PathVariable boolean current, 
                                                      @PathVariable int trashCanId){
        try {
            TrashCan trashCan = trashCanService.getTrashCan(trashCanId);
            int recyclables = 0;
            int nonrecyclables = 0;

            if(current){
                for(CurrentTrashCanItem item : trashCan.getCurrentTrashCanItems()){
                    if(item.getItem().isRecyclable()){
                        recyclables += item.getQuantity();
                    }
                    else{
                        nonrecyclables += item.getQuantity();
                    }
                }
            }
            else{
                for(LifetimeTrashCanItem item : trashCan.getLifetimeTrashCanItems()){
                    if(item.getItem().isRecyclable()){
                        recyclables += item.getQuantity();
                    }
                    else{
                        nonrecyclables += item.getQuantity();
                    }
                }
            }

            // Assuming you have an ItemView class to represent the item's information
            TrashTypesView trashCanView = new TrashTypesView(
                    recyclables,
                    nonrecyclables
            );

            return ResponseEntity.ok(trashCanView);
        }catch (TrashCanNotFoundException e) {
            // Handle the case where the trash can is not found
            TrashCanErrorResponse errorResponse = new TrashCanErrorResponse(
                    ZonedDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "/{trashCanId}",
                    "Trash Can not found: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("trashcan:all")
    public ResponseEntity<?> getAllTrashTypes(@RequestParam boolean current){
        try {
            TrashTypesView trashCanView = getTrashTypesView(current);
            return ResponseEntity.ok(trashCanView);
        }catch (TrashCanNotFoundException e) {
            // Handle the case where the trash can is not found
            TrashCanErrorResponse errorResponse = new TrashCanErrorResponse(
                    ZonedDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "/{trashCanId}",
                    "Trash Can not found: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    private TrashTypesView getTrashTypesView(boolean current) {
        int recyclables = 0;
        int nonrecyclables = 0;

        if(current){
            for(TrashCan trashCan: trashCanService.getAllTrashCans()) {
                for (CurrentTrashCanItem item : trashCan.getCurrentTrashCanItems()) {
                    if (item.getItem().isRecyclable()) {
                        recyclables += item.getQuantity();
                    } else {
                        nonrecyclables += item.getQuantity();
                    }
                }
            }
        }
        else{
            for(TrashCan trashCan: trashCanService.getAllTrashCans()) {
                for (LifetimeTrashCanItem item : trashCan.getLifetimeTrashCanItems()) {
                    if (item.getItem().isRecyclable()) {
                        recyclables += item.getQuantity();
                    } else {
                        nonrecyclables += item.getQuantity();
                    }
                }
            }
        }

        // Assuming you have an ItemView class to represent the item's information
        return new TrashTypesView(
                recyclables,
                nonrecyclables
        );
    }
}
