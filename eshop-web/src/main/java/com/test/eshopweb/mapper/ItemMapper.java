package com.test.eshopweb.mapper;

import com.test.eshopweb.dto.ItemDto;
import com.test.eshopweb.entity.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toDto(Item item);
    List<ItemDto> toDto(List<Item> item);

    Item toEntity(ItemDto dto);
    List<Item> toEntity(List<ItemDto> dto);


}
