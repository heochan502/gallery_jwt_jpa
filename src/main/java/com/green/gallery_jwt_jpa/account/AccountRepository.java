package com.green.gallery_jwt_jpa.account;

import com.green.gallery_jwt_jpa.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

//Members: 연결한 entity클래스명 작성
// Long : 해당 entity의 PK 타입 작성

public interface AccountRepository extends JpaRepository<Members, Long> {
    Members findByLoginId(String loginId);

}
