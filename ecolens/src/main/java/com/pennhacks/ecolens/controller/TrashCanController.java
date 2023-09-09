package com.pennhacks.ecolens.controller;

import com.pennhacks.ecolens.exception.ItemNotFoundException;
import com.pennhacks.ecolens.exception.TrashCanNotFoundException;
import com.pennhacks.ecolens.model.Item;
import com.pennhacks.ecolens.model.TrashCan;
import com.pennhacks.ecolens.response.ItemErrorResponse;
import com.pennhacks.ecolens.response.ItemView;
import com.pennhacks.ecolens.response.TrashCanErrorResponse;
import com.pennhacks.ecolens.response.TrashCanView;
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
     * Ex URL:http://localhost:8080/trashcan/1?itemName=Hot%20Cup
     * @param trashCanId
     * @param itemName
     * @return
     */
    @PatchMapping("/trashcan/{trashCanId}")
    public ResponseEntity<String> updateTrashCanItems(@PathVariable int trashCanId, @RequestParam("itemName") String itemName){
        trashCanService.updateTrash(trashCanId, itemName);
        return ResponseEntity.ok("Trash can with id " + trashCanId + " added item: " + itemName +".");
    }

    @GetMapping("/{itemName}")
    public ResponseEntity<?> getItemDescription(@PathVariable String itemName){
        try {
            TrashCan trashCan = new TrashCan();
            Item item = trashCan.getItemByName(itemName);

            // Assuming you have an ItemView class to represent the item's information
            ItemView itemView = new ItemView(item.getName(), item.getDescription());

            return ResponseEntity.ok(itemView);
        } catch (ItemNotFoundException e) {
            // Handle the case where the item is not found
            ItemErrorResponse errorResponse = new ItemErrorResponse(
                    ZonedDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "/{itemName}",
                    "Item not found: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (TrashCanNotFoundException e) {
            // Handle the case where the trash can is not found
            TrashCanErrorResponse errorResponse = new TrashCanErrorResponse(
                    ZonedDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "/{itemName}",
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
}
