package com.test.eshopweb.service;

import com.test.eshopweb.dto.ItemDto;
import com.test.eshopweb.entity.Item;
import com.test.eshopweb.exception.DeleteException;
import com.test.eshopweb.mapper.ItemMapper;
import com.test.eshopweb.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    @Cacheable("items")
    @Transactional(readOnly = true)
    public List<ItemDto> findAll(Sort sort) {
        return itemMapper.toDto(itemRepository.findAll(sort));
    }

    @Override
    @Cacheable(cacheNames = "item", key = "#id")
    @Transactional(readOnly = true)
    public Optional<ItemDto> findById(int id) {
        return itemRepository.findById(id)
                .map(itemMapper::toDto);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "items", allEntries = true),
            @CacheEvict(cacheNames = "item", key = "#itemDto.id")
    })
    @Transactional
    public ItemDto save(ItemDto itemDto) {
        Item entity = itemMapper.toEntity(itemDto);
        Item savedEntity = itemRepository.save(entity);
        return itemMapper.toDto(savedEntity);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "items", allEntries = true),
            @CacheEvict(cacheNames = "item", key = "#id")
    })
    @Transactional
    public void deleteById(int id) {
        try {
            itemRepository.deleteById(id);
            itemRepository.flush(); // vynuti provedeni delete!!! Dulezite protoze tato operace je transakcni!!!
        } catch (Exception e) {
            throw new DeleteException("Cannot delete item with id: " + id);
        }
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "items", allEntries = true),
            @CacheEvict(cacheNames = "item", key = "#id")
    })
    @Transactional
    public void updatePrice(int id, double newPrice) {
        Optional<Item> optional = itemRepository.findById(id);
        optional.ifPresent(item -> {
            item.setPrice(newPrice);
        });
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "items", allEntries = true),
            @CacheEvict(cacheNames = "item", allEntries = true)
    })
    public void clearCache() {
        log.info("cache cleared!");
    }
}
