package com.green.gallery_jwt_jpa.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderPostDto {
    private int orderId;
    private long memberId;
    private String name;
    private String address;
    private String payment;
    private String cardNumber;
    private long amount; //총 구매가격
}
