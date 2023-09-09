package com.pennhacks.ecolens.service;

import com.pennhacks.ecolens.exception.TrashCanNotFoundException;
import com.pennhacks.ecolens.model.TrashCan;
import com.pennhacks.ecolens.repository.TrashCanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrashCanService {
    private final TrashCanRepository trashCanRepo;

    public TrashCanService(TrashCanRepository trashCanRepo) {
        this.trashCanRepo = trashCanRepo;
    }

    /**
     * Create a new trash can
     * @param trashCan The trash can to be created
     * @return The created trash can
     */
    public TrashCan createTrashCan(TrashCan trashCan){
        trashCan.initializeTrashCanItems();
        return trashCanRepo.save(trashCan);
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
     * @param id The ID of the trash can to dump
     */
    public void dumpTrash(int id){
        TrashCan trashCan = getTrashCan(id);
        trashCan.dumpTrash();
    }
}
