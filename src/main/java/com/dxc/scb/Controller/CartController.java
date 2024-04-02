package com.dxc.scb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.scb.Exception.CartItemNotExistException;
import com.dxc.scb.Service.CartService;
import com.dxc.scb.Service.ProductService;
import com.dxc.scb.Service.UserService;
import com.dxc.scb.dto.AddToCartDto;
import com.dxc.scb.dto.CartDto;
import com.dxc.scb.model.Product;
import com.dxc.scb.model.User;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	private final CartService cartService;
	@Autowired
	private final ProductService productService;
	@Autowired
	private final UserService userService;

	@Autowired
	public CartController(CartService cartService, UserService userService, ProductService productService) {
		this.cartService = cartService;
		this.productService = productService;
		this.userService = userService;
	}

	@PostMapping("/add")
	public ResponseEntity<Void> addToCart(@RequestBody AddToCartDto addToCartDto,
			@RequestParam("productId") Long productId, @RequestParam("userId") Long userId) {
	
		
		// Assuming you have a method to fetch the Product and User objects
	
		
		Product product = productService.getProductById(productId);
		User user = userService.loadUserById(userId);
		cartService.addToCart(addToCartDto, product, user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/list")
	public ResponseEntity<CartDto> listCartItems(@RequestParam("id") Long id) {
		User user = userService.loadUserById(id);
		CartDto cartDto = cartService.listCartItems(user);
		return new ResponseEntity<>(cartDto, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Void> updateCartItem(@RequestBody AddToCartDto cartDto, @RequestParam("userId") Long userId,
																		@RequestParam("productId") Long productId) {
		// Assuming you have a method to fetch the Product and User objects
	
		Product product = productService.getProductById(productId);
		User user = userService.loadUserById(userId);
		cartService.updateCartItem(cartDto, user, product);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteCartItem(@PathVariable("id") Long id, @RequestParam("userId") Long userId) {
		try {
			cartService.deleteCartItem(id, userId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (CartItemNotExistException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}