package com.dxc.scb.model;

import java.util.List;

import com.dxc.scb.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "products")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
  

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int availableQuantity;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Nonnull
    private String imageURL;
  //addedQuantity1042024
    
    
    @Nonnull
    private String description;

    //chnages done 290324
//    @OneToMany(mappedBy = "product")
//    private List<OrderProduct> orderProducts;
//*****************************************************
    @OneToOne(mappedBy = "product")
    private OrderProduct orderProducts;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Cart> carts;
//******************************************************
    public Product(ProductDto productDto) {
        this.name = productDto.getName();
        this.imageURL = productDto.getImageURL();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.availableQuantity=productDto.getAvailableQuantity();

    }
	
	
}
