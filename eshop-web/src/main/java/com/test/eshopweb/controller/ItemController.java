package com.test.eshopweb.controller;

import com.test.eshopweb.dto.ItemDto;
import com.test.eshopweb.entity.Item;
import com.test.eshopweb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

//    // http://localhost:8080/item
//    @GetMapping
//    public List<ItemDto> items() {
//        return itemService.findAll(Sort.by(Sort.Direction.ASC, "id"));
//    }
//
//    // http://localhost:8080/item?sort=name&direction=desc
//    // http://localhost:8080/item?sort=name&direction=asc
//    @GetMapping(params = {"sort", "direction"})
//    public List<ItemDto> items(@RequestParam String sort, @RequestParam String direction) {
//        return itemService.findAll(Sort.by(Sort.Direction.fromString(direction.toUpperCase()), sort));
//    }

    // http://localhost:8080/item
    // http://localhost:8080/item?sort=name&direction=desc
    // http://localhost:8080/item?sort=name&direction=asc
    @GetMapping
    public List<ItemDto> items(@RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "ASC") String direction) {
        return itemService.findAll(Sort.by(Sort.Direction.fromString(direction.toUpperCase()), sort));
    }

    // http://localhost:8080/item/1
    // http://localhost:8080/item/2
    @GetMapping("/{id}")
    public Optional<ItemDto> item(@PathVariable int id) {
        return itemService.findById(id);
    }

}
