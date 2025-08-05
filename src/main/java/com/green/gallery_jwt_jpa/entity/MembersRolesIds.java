package com.green.gallery_jwt_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@EqualsAndHashCode
@Embeddable // 포함 될 수 있는

// 복합키 할때는 아래와 같이 serialzable 해야한다 // 복합키는 복수형으로 적고
// serialzable을 implments해야한다
public class MembersRolesIds implements Serializable {
    private Long memberId;
    @Column( length = 20)
    private Long roleName;
}
