package com.green.gallery_jwt_jpa.cart.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartPostReq {
    private Long memberId;
    private Long itemId;
}
