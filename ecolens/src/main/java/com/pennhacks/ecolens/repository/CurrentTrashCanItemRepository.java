package com.pennhacks.ecolens.repository;

import com.pennhacks.ecolens.model.CurrentTrashCanItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentTrashCanItemRepository extends JpaRepository<CurrentTrashCanItem, Integer> {
}
