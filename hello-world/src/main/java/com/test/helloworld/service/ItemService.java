package com.test.helloworld.service;

import com.test.helloworld.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

//    @Value("${java.version}")
    private String javaVersion;

    public ItemService(@Qualifier("jdbcItemRepository") ItemRepository itemRepository, @Value("${java.version}") String javaVersion) {
        this.itemRepository = itemRepository;
        this.javaVersion = javaVersion;
    }

//    public ItemService(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    public String getItemCount() {
        return "Item count is: " + itemRepository.count() + " & Java version is: " + javaVersion;
    }

}
