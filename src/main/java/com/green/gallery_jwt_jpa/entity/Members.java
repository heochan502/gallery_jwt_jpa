package com.green.gallery_jwt_jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Entity // 모든 entity는 pk값을 가져야한다
@EqualsAndHashCode // 모든 엔티티에는 이게 있어야한다. 다른 객체에서 같은 db의 같은 table을 가져왔을때 서로 비교해야하는데
//그때 두개 판별하기 쉽게 하는거 두테이블을 가져와서 비교 하는것에 성능을 높이기 위한 것
public class Members extends Created {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk는 왠만하면 long 타입으로 권장 한다.

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String loginId; // 자동으로 스네이크 기법으로 표기된다 데이터 베이스에

    @Column(nullable = false, length = 100)
    private String loginPw;
}

