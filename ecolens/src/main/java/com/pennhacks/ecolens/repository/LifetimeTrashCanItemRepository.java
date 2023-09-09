package com.pennhacks.ecolens.repository;

import com.pennhacks.ecolens.model.LifetimeTrashCanItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifetimeTrashCanItemRepository extends JpaRepository<LifetimeTrashCanItem, Integer> {
}
