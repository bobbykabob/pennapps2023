package com.pennhacks.ecolens.service;

import com.pennhacks.ecolens.repository.ItemRepository;
import com.pennhacks.ecolens.response.ItemView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepo;

    public ItemService(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }
}
