package com.dxc.scb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dxc.scb.Exception.CartItemNotExistException;
import com.dxc.scb.dto.AddToCartDto;
import com.dxc.scb.dto.CartDto;
import com.dxc.scb.dto.CartItemDto;
import com.dxc.scb.model.Cart;
import com.dxc.scb.model.Product;
import com.dxc.scb.model.User;

@Service
public interface CartService {

	void addToCart(AddToCartDto addToCartDto, Product product, User user);
	public CartDto listCartItems(User user) ;
	void updateCartItem(AddToCartDto cartDto, User user,Product product);
	void deleteCartItem(Long id,Long userId) throws CartItemNotExistException;

	
	

	    /**
	     * Adds a product with the specified quantity to the cart.
	     *
	     * @param productId the ID of the product to add to the cart
	     * @param quantity the quantity of the product to add
	     */
//	    void addProductToCart(Long productId, int quantity);
//
//	    /**
//	     * Updates the quantity of a product in the cart.
//	     *
//	     * @param productId the ID of the product to update in the cart
//	     * @param quantity the new quantity of the product
//	     */
//	    void updateProductQuantity(Long productId, int quantity);
//
//	    /**
//	     * Removes a product from the cart.
//	     *
//	     * @param productId the ID of the product to remove from the cart
//	     */
//	    void removeProductFromCart(Long productId);
//
//	    /**
//	     * Clears all products from the cart.
//	     */
//	    void clearCart();
//
//	    /**
//	     * Retrieves the contents of the cart.
//	     *
//	     * @return a list of cart items representing the contents of the cart
//	     */
//	    List<Cart> getCartItems();
//
//	    /**
//	     * Calculates the total price of all items in the cart.
//	     *
//	     * @return the total price of all items in the cart
//	     */
//	    double getTotalPrice();
	

	
	
}
