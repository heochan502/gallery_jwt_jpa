package com.green.gallery_jwt_jpa.order;

import com.green.gallery_jwt_jpa.config.model.UserPrincipal;
import com.green.gallery_jwt_jpa.config.util.HttpUtils;
import com.green.gallery_jwt_jpa.order.model.*;
import com.green.gallery_jwt_jpa.account.etc.AccountConstants;
import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> add(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody OrderPostReq req) {
//        long logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        long logginedMemberId = userPrincipal.getMemberId();
        log.info("req: {}", req);
        int result = orderService.saveOrder(req, logginedMemberId);
        return ResponseEntity.ok(result);
        //return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllByMemberIdOrderByIdDesc(@AuthenticationPrincipal UserPrincipal userPrincipal) {
//        long logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        long logginedMemberId = userPrincipal.getMemberId();
        List<OrderGetRes> result = orderService.findAllByMemberId(logginedMemberId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> findDetail(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable int orderId) {
//        long logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        long logginedMemberId = userPrincipal.getMemberId();
        OrderDetailGetReq req = new OrderDetailGetReq();
        req.setOrderId(orderId);
        req.setMemberId(logginedMemberId);
        OrderDetailGetRes result = orderService.detail(req);
        return ResponseEntity.ok(result);
    }
}
