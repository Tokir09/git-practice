package com.dxc.scb.Exception;

public class CartItemNotExistException extends IllegalArgumentException {

	public CartItemNotExistException(String msg) {
		super(msg);
	}

}
