package com.test.eshopweb.service;

import com.test.eshopweb.entity.Item;
import com.test.eshopweb.repository.ItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll(Sort sort) {
        return itemRepository.findAll(sort);
    }

    public Optional<Item> findById(int id) {
        return itemRepository.findById(id);
    }


}
