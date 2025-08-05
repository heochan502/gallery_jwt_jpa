package com.green.gallery_jwt_jpa.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartDeleteReq {
    private int cartId;
    private long memberId;
}
