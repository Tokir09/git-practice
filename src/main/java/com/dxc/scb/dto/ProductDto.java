package com.dxc.scb.dto;

import com.dxc.scb.model.Product;

import jakarta.annotation.Nonnull;
import lombok.Data;
@Data
public class ProductDto {
	    
	    private Long id;
	    @Nonnull
	    private String name;
	    @Nonnull
	    private String imageURL;
	    @Nonnull
	    private double price;
	    @Nonnull
	    private String description;
	    
	    private int availableQuantity;

	  

	    public ProductDto(Product product) {
	        this.setId(product.getId());
	        this.setName(product.getName());
	        this.setImageURL(product.getImageURL());
	        this.setDescription(product.getDescription());
	        this.setPrice(product.getPrice());
	        this.setAvailableQuantity(product.getAvailableQuantity());
	    }


	

	 
}
