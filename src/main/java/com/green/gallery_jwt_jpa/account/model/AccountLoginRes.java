package com.green.gallery_jwt_jpa.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.gallery_jwt_jpa.config.model.JwtUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class AccountLoginRes {
    private long memberId;
//    @JsonIgnore
//    private String loginPw;
    @JsonIgnore
    private JwtUser jwtUser;
}
