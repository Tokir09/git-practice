package com.dxc.scb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dxc.scb.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	
}

