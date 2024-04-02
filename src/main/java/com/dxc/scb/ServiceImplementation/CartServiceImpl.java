package com.dxc.scb.ServiceImplementation;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.scb.dto.AddToCartDto;
import com.dxc.scb.dto.CartDto;
import com.dxc.scb.dto.CartItemDto;
import com.dxc.scb.dto.ProductDto;
import com.dxc.scb.Exception.CartItemNotExistException;
import com.dxc.scb.Repository.CartRepository;
import com.dxc.scb.Repository.ProductRepository;
import com.dxc.scb.model.Cart;
import com.dxc.scb.model.Product;
import com.dxc.scb.model.User;
import com.dxc.scb.Service.CartService;
import com.dxc.scb.Service.ProductService;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CartServiceImpl implements CartService{
 
    @Autowired
    private CartRepository cartRepository;
    private ProductRepository productRepository;
 
    public CartServiceImpl(CartRepository cartRepository,ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository=productRepository;
    }
 
    public void addToCart(AddToCartDto addToCartDto, Product product, User user){
	Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cartRepository.save(cart);
//    	changes 1042024
       
//    	
//        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
//        cartRepository.save(cart);
    }
 
 

 
    public CartDto listCartItems(User user) {

    	 List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
         List<CartItemDto> cartItems = new ArrayList<>();
         for (Cart cart:cartList){
             CartItemDto cartItemDto = getDtoFromCart(cart);
             cartItems.add(cartItemDto);
         }
         double totalCost = 0;
         for (CartItemDto cartItemDto :cartItems){
             totalCost += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
         }
         return new CartDto(cartItems,totalCost);
    }
 
    
 
    public static CartItemDto getDtoFromCart(Cart cart) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cart.getId());
        cartItemDto.setQuantity(cart.getQuantity());
        cartItemDto.setProduct(cart.getProduct());
        return cartItemDto;
    }


	public void updateCartItem(AddToCartDto cartDto, User user,Product product){
        Cart cart = cartRepository.findByUserId(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        //change 1042024
    //    cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }
 
    public void deleteCartItem(Long id,Long userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);
 
    }
 


	
}
