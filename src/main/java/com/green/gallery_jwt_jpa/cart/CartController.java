package com.green.gallery_jwt_jpa.cart;

import com.green.gallery_jwt_jpa.config.model.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import com.green.gallery_jwt_jpa.account.etc.AccountConstants;
import com.green.gallery_jwt_jpa.cart.model.CartDeleteReq;
import com.green.gallery_jwt_jpa.cart.model.CartGetRes;
import com.green.gallery_jwt_jpa.cart.model.CartPostReq;
import com.green.gallery_jwt_jpa.config.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> save(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody CartPostReq req) {
        log.info("req: {}", req);
//        int logginedMemberId = (int)HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
//        req.setMemberId(logginedMemberId);
        req.setMemberId(userPrincipal.getMemberId());
        int result = cartService.save(req);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> getCart(@AuthenticationPrincipal UserPrincipal userPrincipal) {
//        int logginedMemberId = (int)HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
       int logginedMemberId = userPrincipal.getMemberId();
        List<CartGetRes> result = cartService.findAll(logginedMemberId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteMemberItem(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable int cartId) {
//        int logginedMemberId = (int)HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        int logginedMemberId = userPrincipal.getMemberId();
        CartDeleteReq req = new CartDeleteReq(cartId, logginedMemberId);
        int result = cartService.remove(req);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMemberCart(@AuthenticationPrincipal UserPrincipal userPrincipal) {
//        int logginedMemberId = (int)HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        int logginedMemberId = userPrincipal.getMemberId();
        int result = cartService.removeAll(logginedMemberId);
        return ResponseEntity.ok(result);
    }
}
