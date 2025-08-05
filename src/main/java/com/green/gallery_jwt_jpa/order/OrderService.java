package com.green.gallery_jwt_jpa.order;


import com.green.gallery_jwt_jpa.cart.*;
import com.green.gallery_jwt_jpa.item.ItemMapper;
import com.green.gallery_jwt_jpa.item.model.*;
import com.green.gallery_jwt_jpa.order.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ItemMapper itemMapper;
    private final CartMapper cartMapper;

    @Transactional
    public int saveOrder(OrderPostReq req, long logginedMemberId) {
        //상품 정보 DB로 부터 가져온다.
        List<ItemGetRes> itemList = itemMapper.findAllByIdIn(req.getItemIds());

        long amount = 0;
        for (ItemGetRes item : itemList) {
            amount += item.getPrice() - (item.getPrice() * item.getDiscountPer() / 100);
        }
        log.info("amount={}", amount);

        OrderPostDto orderPostDto = new OrderPostDto();
        orderPostDto.setMemberId(logginedMemberId);
        orderPostDto.setName(req.getName());
        orderPostDto.setAddress(req.getAddress());
        orderPostDto.setPayment(req.getPayment());
        orderPostDto.setCardNumber(req.getCardNumber());
        orderPostDto.setAmount(amount);
        log.info("before-orderPostDto={}", orderPostDto);
        orderMapper.save(orderPostDto);
        log.info("after-orderPostDto={}", orderPostDto);

        // OrderItemPostDto 객체화 하시면서 데이터 넣어주세요.
        OrderItemPostDto orderItemPostDto = new OrderItemPostDto(orderPostDto.getOrderId(), req.getItemIds());
        orderItemMapper.save(orderItemPostDto);

        cartMapper.deleteByMemberId(logginedMemberId);

        return 1;
    }

    public List<OrderGetRes> findAllByMemberId(long memberId) {
        return orderMapper.findAllByMemberIdOrderByIdDesc(memberId);
    }

    public OrderDetailGetRes detail(OrderDetailGetReq req) {
        OrderDetailGetRes result = orderMapper.findByOrderIdAndMemberId(req);
        List<OrderDetailDto> items = orderItemMapper.findAllByOrderId(req.getOrderId());
        result.setItems(items);
        log.info("result={}", result);
        return result;
    }
}
