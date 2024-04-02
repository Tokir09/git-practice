package com.dxc.scb.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.scb.model.Enums.OrderStatus;
import com.dxc.scb.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

List<Order> findByOrderStatus(OrderStatus status);

	List<Order> findByUser_Id(int orderId);

	//*
	Optional<Order> findById(int orderId);


}
