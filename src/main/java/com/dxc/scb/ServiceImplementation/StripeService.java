package com.dxc.scb.ServiceImplementation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class StripeService {

    @Value("${stripe.api.private-key}")
    private String stripeApiKey;

    @jakarta.annotation.PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    public void chargeCreditCard(String token, double amount, String currency) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int) (amount * 100)); // Amount in cents
        params.put("currency", currency);
        params.put("source", token); // Token generated by Stripe.js or Checkout

        Charge charge = Charge.create(params);
        // Handle successful charge
    }
}
