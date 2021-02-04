package com.test.eshopweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ItemDto {

    private int id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @Min(1)
    private double price;

    private CategoryDto category;

}
