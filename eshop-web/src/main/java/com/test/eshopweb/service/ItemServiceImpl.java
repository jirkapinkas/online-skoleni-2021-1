package com.test.eshopweb.service;

import com.test.eshopweb.dto.ItemDto;
import com.test.eshopweb.entity.Item;
import com.test.eshopweb.mapper.ItemMapper;
import com.test.eshopweb.repository.ItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemDto> findAll(Sort sort) {
        return itemMapper.toDto(itemRepository.findAll(sort));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemDto> findById(int id) {
        return itemRepository.findById(id)
                .map(itemMapper::toDto);
    }

    @Override
    @Transactional
    public ItemDto save(ItemDto itemDto) {
        Item entity = itemMapper.toEntity(itemDto);
        Item savedEntity = itemRepository.save(entity);
        return itemMapper.toDto(savedEntity);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updatePrice(int id, double newPrice) {
        Optional<Item> optional = itemRepository.findById(id);
        optional.ifPresent(item -> {
            item.setPrice(newPrice);
        });
    }
}