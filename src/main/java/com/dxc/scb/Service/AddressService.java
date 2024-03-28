package com.dxc.scb.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dxc.scb.model.Address;

@Service
public interface AddressService {
	
	 Address createAddress(Address address);
	 Address updateAddress(Address address);
	 void deleteAddressById(Long id);
	 Optional<Address> getAddressById(Long id);
	 List<Address> getAllAddresses();
	

}
