package com.green.gallery_jwt_jpa.item;

import com.green.gallery_jwt_jpa.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Items, Long> {
    List<Items> findAllByIdIn(List<Long> id);
}
