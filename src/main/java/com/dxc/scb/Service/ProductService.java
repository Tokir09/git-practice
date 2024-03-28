package com.dxc.scb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dxc.scb.Exception.ProductNotExistException;
import com.dxc.scb.Repository.ProductRepository;
import com.dxc.scb.dto.ProductDto;
import com.dxc.scb.model.Product;

@Service
public interface ProductService {

	 List<ProductDto> listProducts();
	 
	 ProductDto getDtoFromProduct(Product product);
     Product getProductFromDto(ProductDto productDto);
     void addProduct(ProductDto productDto) ;
     void updateProduct(Long productID, ProductDto productDto);
     
     Product getProductById(Long productId) throws ProductNotExistException;
}
