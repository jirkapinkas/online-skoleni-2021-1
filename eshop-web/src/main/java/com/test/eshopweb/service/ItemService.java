package com.test.eshopweb.service;

import com.test.eshopweb.dto.ItemDto;
import com.test.eshopweb.entity.Item;
import com.test.eshopweb.mapper.ItemMapper;
import com.test.eshopweb.repository.ItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public List<ItemDto> findAll(Sort sort) {
        return itemMapper.toDto(itemRepository.findAll(sort));
    }

    public Optional<ItemDto> findById(int id) {
        return itemRepository.findById(id)
                .map(itemMapper::toDto);
    }

    public ItemDto save(ItemDto itemDto) {
        Item entity = itemMapper.toEntity(itemDto);
        Item savedEntity = itemRepository.save(entity);
        return itemMapper.toDto(savedEntity);
    }

    public void deleteById(int id) {
        itemRepository.deleteById(id);
    }

}
