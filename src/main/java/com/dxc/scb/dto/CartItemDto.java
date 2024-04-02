package com.dxc.scb.dto;

import java.util.List;

import com.dxc.scb.model.Cart;
import com.dxc.scb.model.Product;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    
	
	private Long id;
    @Nonnull
    private  Integer quantity;
    @Nonnull
    private Product product;

 

//    public CartItemDto(Cart cart) {
//        this.setId(cart.getId());
//        this.setQuantity(cart.getQuantity());
//        this.setProduct(cart.getProductId());
//    }
}