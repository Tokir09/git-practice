package com.dxc.scb.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.scb.Exception.OrderNotFoundException;
import com.dxc.scb.Repository.OrderRepository;
import com.dxc.scb.Service.OrderService;
import com.dxc.scb.model.Order;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
    }

    @Override
    public void updateOrder(Long orderId, Order updatedOrder) {
        Order order = getOrderById(orderId);
        // Update order properties based on the updatedOrder object
        order.setOrderDate(updatedOrder.getOrderDate());
        order.setOrderStatus(updatedOrder.getOrderStatus());
        order.setUser(updatedOrder.getUser());
        order.setOrderProducts(updatedOrder.getOrderProducts());
        order.setCart(updatedOrder.getCart());
        // Update other properties as needed
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }
}
