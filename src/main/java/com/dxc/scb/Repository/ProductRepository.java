package com.dxc.scb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.scb.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product getProductById(Long ProductId);
}
