package com.green.gallery_jwt_jpa.cart;

import com.green.gallery_jwt_jpa.cart.model.CartDeleteReq;
import com.green.gallery_jwt_jpa.cart.model.CartGetRes;
import com.green.gallery_jwt_jpa.cart.model.CartPostReq;
import com.green.gallery_jwt_jpa.entity.Carts;
import com.green.gallery_jwt_jpa.entity.Items;
import com.green.gallery_jwt_jpa.entity.Members;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.print.attribute.standard.Media;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;
    private final CartRepository cartRepository;

    public void save(CartPostReq req) {
       try{
           Items items = new Items();
           items.setId(req.getItemId());

           Members members = new Members();
           members.setId(req.getMemberId());

//           return cartMapper.save(req);
           Carts carts = new Carts();

           carts.setMembers(members);
           carts.setItems(items);

           cartRepository.save(carts);
       }
       catch (DuplicateKeyException e){
//           e.printStackTrace();
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"이미 카트에 등록 되어있습니다.");
            }

    }

    public List<CartGetRes> findAll(long memberId) {
        return cartMapper.findAllWithItemByMemberId(memberId);
    }

    public int remove(CartDeleteReq req) {
        return cartMapper.deleteByCartIdAndMemberId(req);
    }

    public int removeAll(long memberId) {
        return cartMapper.deleteByMemberId(memberId);
    }
}
