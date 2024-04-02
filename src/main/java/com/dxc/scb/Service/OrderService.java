package com.dxc.scb.Service;

import java.util.List;
import java.util.Optional;

import com.dxc.scb.Exception.OrderNotFoundException;
import com.dxc.scb.model.Enums.OrderStatus;
import com.dxc.scb.model.Order;

public interface OrderService {

	Order getOrderById(Long id) throws OrderNotFoundException;

	Optional<Order> getOrdersByUserId(Long userId);

	List<Order> getOrdersByStatus(OrderStatus status);


	Order createOrder(Order order);

	void updateOrderStatus(Long id, Order status) throws OrderNotFoundException;

	List<Order> getAllOrders();

	void deleteOrder(Long orderId);

	void updateOrder(Long orderId, Order updatedOrder);

	//*
	Order getOrderDetail(int orderId);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
