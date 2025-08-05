package com.green.gallery_jwt_jpa.cart;

import com.green.gallery_jwt_jpa.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends JpaRepository<Carts, Long> {
}
