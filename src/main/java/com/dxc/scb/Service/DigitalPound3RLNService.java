package com.dxc.scb.Service;

	public interface DigitalPound3RLNService {
	    double getBalance(long id);
	    boolean lock(long id);
	    boolean unlock(long id);
	}


