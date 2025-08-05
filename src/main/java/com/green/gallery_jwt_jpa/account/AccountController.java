package com.green.gallery_jwt_jpa.account;

import com.green.gallery_jwt_jpa.config.jwt.JwtTokenManager;
import jakarta.servlet.http.HttpServletRequest;
import com.green.gallery_jwt_jpa.account.etc.AccountConstants;
import com.green.gallery_jwt_jpa.account.model.AccountJoinReq;
import com.green.gallery_jwt_jpa.account.model.AccountLoginReq;
import com.green.gallery_jwt_jpa.account.model.AccountLoginRes;
import com.green.gallery_jwt_jpa.config.util.HttpUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;
    private final JwtTokenManager jwtTokenManager;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody AccountJoinReq req) {
        if(!StringUtils.hasLength(req.getName())
                || !StringUtils.hasLength(req.getLoginId())
                || !StringUtils.hasLength(req.getLoginPw())) {
            return ResponseEntity.badRequest().build(); //state: 400
        }

//        int result = accountService.join(req);
        accountService.join(req);
        return ResponseEntity.ok(1); //stat
        // e: 200
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletResponse response, @RequestBody AccountLoginReq req) {
        AccountLoginRes result = accountService.login(req);
        if(result == null) {
            return ResponseEntity.notFound().build();
        }
        jwtTokenManager.issue(response, result.getJwtUser());

        //세션 처리
        //HttpUtils.setSession(httpReq, AccountConstants.MEMBER_ID_NAME, result.getId());

        return ResponseEntity.ok(result);
    }

//    @GetMapping("/check")
//    public ResponseEntity<?> check(HttpServletRequest httpReq) {
//        Integer id = (Integer)HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
//        log.info("id: {}", id);
//        return ResponseEntity.ok(id);
//    }

    @PostMapping ("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        jwtTokenManager.reissue(request, response);
        return ResponseEntity.ok(1);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        jwtTokenManager.logout(response);
        return ResponseEntity.ok(1);
    }

}
