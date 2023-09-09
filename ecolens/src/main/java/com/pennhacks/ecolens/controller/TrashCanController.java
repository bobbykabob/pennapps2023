package com.pennhacks.ecolens.controller;

import com.pennhacks.ecolens.model.TrashCan;
import com.pennhacks.ecolens.service.TrashCanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Integer> createTrashCan(){
        TrashCan newTrashCan = new TrashCan();
        TrashCan createdTrashCan = trashCanService.createTrashCan(newTrashCan);
        return ResponseEntity.ok(createdTrashCan.getId());
    }

    @DeleteMapping("/trashcan/{id}")
    public ResponseEntity<String> deleteTrashCan(@PathVariable int id){
        trashCanService.deleteTrashCan(id);
        return ResponseEntity.ok("Trash can with id: " + id + " deleted.");
    }

    @PatchMapping("/trashcan:id")
    public ResponseEntity<String> dumpTrashCan(@RequestParam int id){
        trashCanService.dumpTrash(id);
        return ResponseEntity.ok("Trash can with id " + id + " successfully dumped");
    }

    @PatchMapping("/trashcan/{trashCanId}")
    public ResponseEntity<String> updateTrashCanItems(@PathVariable int trashCanId, @RequestBody int trashCanItemId){
        trashCanService.updateTrash(trashCanId, trashCanItemId);
        return ResponseEntity.ok("Trash can with id " + trashCanId + " added item with id" + trashCanItemId +".");
    }
}
