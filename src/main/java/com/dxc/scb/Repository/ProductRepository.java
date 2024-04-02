package com.dxc.scb.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.scb.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product getProductById(Long ProductId);

	Optional<Product> findById(int productId);
}

