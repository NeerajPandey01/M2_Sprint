package com.app.payment_service.controller;

import com.app.payment_service.model.Payment;
import com.app.payment_service.model.PaymentInfoRequest;
import com.app.payment_service.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<Object> createPaymentIntent(@RequestBody PaymentInfoRequest paymentInfoRequest)
            throws StripeException {

        Map<String,String> clientSecret = paymentService.createPaymentIntent(paymentInfoRequest);

        return new ResponseEntity<>(clientSecret, HttpStatus.OK);
        
    }


    @PostMapping("/payment-complete")
    public ResponseEntity<Payment> stripePaymentComplete(@RequestBody Payment payment)
{
        return paymentService.stripePayment(payment);
    }
}