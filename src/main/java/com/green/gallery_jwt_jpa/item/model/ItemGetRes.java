package com.green.gallery_jwt_jpa.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ItemGetRes {
    private Long id;
    private String name;
    private String imgPath;
    private int price;
    private int discountPer;
}
