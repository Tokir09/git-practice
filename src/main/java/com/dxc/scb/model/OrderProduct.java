package com.dxc.scb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OrderProduct")
public class OrderProduct {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long orderProductId;

	
	@Column
	@Nonnull
    private int quantity;
	
	@Nonnull
	@Column
    private double price;
	
	  @ManyToOne
	    @JoinColumn(name = "order_id")
	    private Order order;

	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;
	
    
}