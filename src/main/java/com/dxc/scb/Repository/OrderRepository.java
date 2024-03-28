package com.dxc.scb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.scb.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
