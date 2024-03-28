package com.dxc.scb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxc.scb.Service.AddressService;
import com.dxc.scb.dto.AddressDto;
import com.dxc.scb.model.Address;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Endpoint to create a new address
    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@Valid @RequestBody AddressDto addressDTO) {
        Address address = mapDtoToEntity(addressDTO);
        Address createdAddress = addressService.createAddress(address);
        AddressDto createdAddressDTO = mapEntityToDto(createdAddress);
        return new ResponseEntity<>(createdAddressDTO, HttpStatus.CREATED);
    }

    // Endpoint to update an existing address
    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDto addressDTO) {
        Optional<Address> existingAddressOptional = addressService.getAddressById(id);
        if (existingAddressOptional.isPresent()) {
            Address existingAddress = existingAddressOptional.get();
            Address updatedAddress = mapDtoToEntity(addressDTO);
            updatedAddress.setId(existingAddress.getId()); 
            Address savedAddress = addressService.updateAddress(updatedAddress);
            AddressDto savedAddressDTO = mapEntityToDto(savedAddress);
            return new ResponseEntity<>(savedAddressDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        Optional<Address> existingAddressOptional = addressService.getAddressById(id);
        if (existingAddressOptional.isPresent()) {
            addressService.deleteAddressById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to retrieve an address by its ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable Long id) {
        Optional<Address> addressOptional = addressService.getAddressById(id);
        return addressOptional.map(address -> new ResponseEntity<>(mapEntityToDto(address), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to retrieve all addresses
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        List<AddressDto> addressDTOs = addresses.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(addressDTOs, HttpStatus.OK);
    }

    // Helper method to map AddressDTO to Address entity
    private Address mapDtoToEntity(AddressDto addressDTO) {
        Address address = new Address();
        address.setHouse_No(addressDTO.getHouseNo());
        address.setApartment_Name(addressDTO.getApartmentName());
        address.setStreet_Name(addressDTO.getStreetName());
        address.setPinCode(addressDTO.getPinCode());
        address.setCity(addressDTO.getCity());
        // Set other properties as needed
        return address;
    }

    // Helper method to map Address entity to AddressDTO
    private AddressDto mapEntityToDto(Address address) {
        AddressDto addressDTO = new AddressDto();
        addressDTO.setHouseNo(address.getHouse_No());
        addressDTO.setApartmentName(address.getApartment_Name());
        addressDTO.setStreetName(address.getStreet_Name());
        addressDTO.setPinCode(address.getPinCode());
        addressDTO.setCity(address.getCity());
        // Set other properties as needed
        return addressDTO;
    }
}

