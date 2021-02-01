package com.test.helloworld.service;

import com.test.helloworld.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public String getItemCount() {
        return "Item count is: " + itemRepository.count();
    }

}
