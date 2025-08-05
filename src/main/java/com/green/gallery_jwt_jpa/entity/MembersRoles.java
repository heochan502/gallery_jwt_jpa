package com.green.gallery_jwt_jpa.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity // 이게 있어야 테이블 생성된다
@Getter
@Setter
@EqualsAndHashCode
public class MembersRoles {
    @EmbeddedId
    private MembersRolesIds membersRolesIds;

    //관계 설정
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) //내기준부터 적으면 댐 앞에 포린키 걸떄
    // CascadeType.PERSIST 영속성 전이 // hibernate
    // CascadeType.REMOVE 부모 영속성이 delete 되면 본인도 delete
    @MapsId("memberId")
    private Members members;
}
