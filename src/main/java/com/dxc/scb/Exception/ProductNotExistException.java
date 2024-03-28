package com.dxc.scb.Exception;

public class ProductNotExistException extends IllegalArgumentException {
	public ProductNotExistException(String msg) {
		super(msg);
	}

}
