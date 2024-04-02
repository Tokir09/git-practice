package com.dxc.scb.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.scb.Repository.ProductRepository;
import com.dxc.scb.Exception.OrderNotFoundException;
import com.dxc.scb.Repository.OrderRepository;
import com.dxc.scb.Service.OrderService;
import com.dxc.scb.model.Cart;
import com.dxc.scb.model.Enums.OrderStatus;
import com.dxc.scb.model.Order;
import com.dxc.scb.model.Product;

import lombok.Data;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class OrderServiceImpl implements OrderService {
//added 1042024
	private OrderRepository orderRepository;
	private ProductRepository productRepository;

	public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}

	@Override
	public Order getOrderDetail(int orderId) {
		Optional<Order> order = this.orderRepository.findById(orderId);
		return order.isPresent() ? order.get() : null;
	}

	public float getCartAmount(List<Cart> shoppingCartList) {

		float totalCartAmount = 0f;
		float singleCartAmount = 0f;
		int availableQuantity = 0;

		for (Cart cart : shoppingCartList) {

			int productId = cart.getProductId();
			Optional<Product> product = productRepository.findById(productId);
			if (product.isPresent()) {
				Product product1 = product.get();
				if (product1.getAvailableQuantity() < cart.getQuantity()) {
					singleCartAmount = (float) (product1.getPrice() * product1.getAvailableQuantity());
					// cart.setQuantity (product1.getAvailableQuantity());
					cart.setQuantity(product1.getAvailableQuantity());
				} else {
					singleCartAmount = (float) (cart.getQuantity() * product1.getPrice());
					availableQuantity = (int) (product1.getAvailableQuantity() - cart.getQuantity());
				}
				totalCartAmount = totalCartAmount + singleCartAmount;
				product1.setAvailableQuantity(availableQuantity);
				availableQuantity = 0;
				cart.setProductName(product1.getName());
				cart.setAmount(singleCartAmount);
				productRepository.save(product1);
			}
		}
		return totalCartAmount;
	}

	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public void processPayment(Order order) {
		// Implement payment processing logic here
		// Update order status if successful
		order.setOrderStatus(OrderStatus.ORDER_PLACED);
		orderRepository.save(order);
	}
	
	//for admin

	@Override
	public Order getOrderById(Long id) throws OrderNotFoundException {
		Optional<Order> order = orderRepository.findById(id);
		if (order.isEmpty()) {
			throw new OrderNotFoundException("Order with ID " + id + " not found");
		}
		return order.get();
	}

	public Optional<Order> getOrdersByUserId(Long userId) {
		return orderRepository.findById(userId);
	}

	@Override
	public List<Order> getOrdersByStatus(OrderStatus status) {
		return orderRepository.findByOrderStatus(status);
	}

	@Override
	public void updateOrderStatus(Long id, Order status) throws OrderNotFoundException {
		Order order = getOrderById(id);
		order.setOrderStatus(status.getOrderStatus());
		orderRepository.save(order);
	}

	// Additional functionalities below  added 1042024 
	public void cancelOrder(Long id) throws OrderNotFoundException {
		Order order = getOrderById(id);
		order.setOrderStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);
	}



	@Override
	public List<Order> getAllOrders() {

		return	orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) {
		orderRepository.deleteById(orderId);

	}

	public void updateOrder(Long orderId, Order updatedOrder) {

	    // Retrieve the order with the given orderId from your database or repository
	    Order existingOrder = orderRepository.findById(orderId).orElse(null);

	    // Check if the order exists
	    if (existingOrder == null) {
	        // Handle case where the order does not exist
	        throw new OrderNotFoundException("Order with ID " + orderId + " not found");
	    }

	    // Update the order details with the details provided in updatedOrder
	  
	    existingOrder.setOrderProducts(updatedOrder.getOrderProducts());
	    existingOrder.setId(updatedOrder.getId());
	    existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
	    // Add more fields to update as needed

	    // Save the updated order back to the database or repository
	    orderRepository.save(existingOrder);
	}


}
