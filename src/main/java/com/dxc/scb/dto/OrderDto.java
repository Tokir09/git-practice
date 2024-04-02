package com.dxc.scb.dto;

import java.util.List;

import com.dxc.scb.model.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
	private String orderDescription;
  private List<Cart> cartItems;
    private String customerEmail;
    private String customerName;

}
