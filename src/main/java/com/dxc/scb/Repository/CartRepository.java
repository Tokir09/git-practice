package com.dxc.scb.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dxc.scb.model.Cart;
import com.dxc.scb.model.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
	 
    Cart findByUserId(Long userId);
    List<Cart> findAll();
	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
	boolean existsById(Long id);
	void deleteByUser(User user);
	
 
    // Additional methods 
}
