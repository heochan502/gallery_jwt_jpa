package com.green.gallery_jwt_jpa.order.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailGetReq {
    private int orderId;
    private long memberId;
}
