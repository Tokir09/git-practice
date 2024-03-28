package com.dxc.scb.Service;

import org.springframework.stereotype.Service;

import com.dxc.scb.Exception.CartItemNotExistException;
import com.dxc.scb.dto.AddToCartDto;
import com.dxc.scb.dto.CartDto;
import com.dxc.scb.dto.CartItemDto;
import com.dxc.scb.model.Cart;
import com.dxc.scb.model.Product;
import com.dxc.scb.model.User;

@Service
public interface CartService {

	void addToCart(AddToCartDto addToCartDto, Product product, User user);
	public CartDto listCartItems(User user) ;
	void updateCartItem(AddToCartDto cartDto, User user,Product product);
	void deleteCartItem(Long id,Long userId) throws CartItemNotExistException;
	public void deleteCartItems(Long userId);
	void deleteUserCartItems(User user);
}
