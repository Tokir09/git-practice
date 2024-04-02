package com.dxc.scb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
//	private Date createdDate;
	//amount of shoppong cart
    private float amount;

	private int quantity;
//* changes added 10402024
	 private int productId;
	    private String productName;
	    
	    public Cart(int productId, String productName, int quantity, float amount) {
	        this.productId = productId;
	        this.productName = productName;
	        this.quantity = quantity;
	        this.amount = amount;
	    }
	    
	    public Cart(int productId, int quantity) {
	        this.productId = productId;
	        this.quantity = quantity;
	    }

		public void setProduct(Product product) {
			// TODO Auto-generated method stub
			
		}

		
//*
	@OneToOne
		@JoinColumn(name = "product_id_1", referencedColumnName = "id")
	private Product product;
//
//	public Cart(Product product, int quantity, User user) {
//		this.user = user;
//		this.product = product;
//		this.quantity = quantity;
//		this.createdDate = new Date();
//	}
}
