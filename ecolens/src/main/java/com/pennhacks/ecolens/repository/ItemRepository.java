package com.pennhacks.ecolens.repository;

import com.pennhacks.ecolens.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ItemRepo")
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
