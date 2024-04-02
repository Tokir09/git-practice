package com.dxc.scb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.scb.Service.PaymentService;
import com.dxc.scb.ServiceImplementation.StripeService;
import com.dxc.scb.dto.ChargeRequest;
import com.dxc.scb.dto.PaymentMode;
import com.dxc.scb.dto.PaymentRequest;
import com.dxc.scb.dto.PaymentResponse;
import com.dxc.scb.model.Payment;
import com.dxc.scb.model.PaymentLockResponse;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	//stripe integration
	@Autowired
	private StripeService stripeService;

	@PostMapping("/charge")
	public String chargeCreditCard(@RequestBody ChargeRequest request) {
		try {
			stripeService.chargeCreditCard(request.getToken(), request.getAmount(), request.getCurrency());
			return "Payment successful";
		} catch (StripeException e) {
			return "Payment failed: " + e.getMessage();
		}
	}

// this be balance check for wallet ? if so we pass parameter such as wallet id 

	@GetMapping("/balance-check")
	public ResponseEntity<String> balanceCheck() {
		String result = paymentService.balanceCheck();
		return ResponseEntity.ok(result);
	}

	@PostMapping("/request-to-lock")
	public ResponseEntity<String> requestToLock() {
		String result = paymentService.requestToLock();
		return ResponseEntity.ok(result);
	}

	@PostMapping("/process-payment")
	public ResponseEntity<String> processPayment() {
		String result = paymentService.processPayment();
		return ResponseEntity.ok(result);
	}

	@PostMapping("/lock-cancel")
	public ResponseEntity<String> lockCancel() {
		String result = paymentService.lockCancel();
		return ResponseEntity.ok(result);
	}

	@GetMapping("/bank-details")
	public String getBankPaymentDetails() {
		return paymentService.getBankPaymentDetails();
	}

	@GetMapping("/wallet-details")
	public String getWalletPaymentDetails() {
		return paymentService.getWalletPaymentDetails();
	}

	@GetMapping("/digital-pound-details")
	public String getDigitalPoundPaymentDetails() {
		return paymentService.getDigitalPoundPaymentDetails();
	}
}
