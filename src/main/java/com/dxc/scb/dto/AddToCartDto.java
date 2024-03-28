package com.dxc.scb.dto;

import jakarta.annotation.Nonnull;
import lombok.Data;

@Data
public class AddToCartDto {
    private Long id;
    
    @Nonnull
    private  Integer productId;
    @Nonnull
    private  Integer quantity;
 
    public AddToCartDto() {
    }

}