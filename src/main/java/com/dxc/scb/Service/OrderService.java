package com.dxc.scb.Service;

import java.util.List;

import com.dxc.scb.model.Order;

public interface OrderService {

	List<Order> getAllOrders();

	Order getOrderById(Long orderId);

	void updateOrder(Long orderId, Order updatedOrder);

	void deleteOrder(Long orderId);

	
}
