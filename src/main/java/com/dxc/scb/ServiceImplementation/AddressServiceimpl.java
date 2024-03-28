package com.dxc.scb.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.scb.Repository.AddressRepository;
import com.dxc.scb.Service.AddressService;
import com.dxc.scb.model.Address;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class AddressServiceimpl implements AddressService{

	

	    @Autowired
	    private AddressRepository addressRepository;

	 
	    public Address createAddress(Address address) {
	        return addressRepository.save(address);
	    }

	  
	    public Address updateAddress(Address address) {
	        return addressRepository.save(address);
	    }

	 
	    public void deleteAddressById(Long id) {
	        addressRepository.deleteById(id);
	    }

	   
	    public Optional<Address> getAddressById(Long id) {
	        return addressRepository.findById(id);
	    }

	    public List<Address> getAllAddresses() {
	        return addressRepository.findAll();
	    }


}
	

