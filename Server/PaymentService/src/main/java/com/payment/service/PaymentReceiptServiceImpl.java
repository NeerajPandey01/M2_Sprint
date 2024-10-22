package com.payment.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentReceiptServiceImpl implements PaymentReceiptService{
	
	private Logger logger = LoggerFactory.getLogger(PaymentReceiptServiceImpl.class);
	
	
	
	@Override
	public String createOrder(double amount) throws RazorpayException {
		
		
		Integer finalAmount = (int)amount*100;
		logger.info("Calculating Final Amount...."+finalAmount);
		logger.info("Calculating Amount...."+amount);
			//Linking to RazorPay
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_LrHTWqr4yuxxA2","ddLMK949k57f2ldSsOzwhxaI");
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount",finalAmount);
			System.out.println(amount);
			orderRequest.put("currency","INR");
			orderRequest.put("receipt", "receipt#1");
			Order order = razorpayClient.orders.create(orderRequest);
			
		
			return order.get("id")+","+finalAmount.toString();
		}
	
	@Override
	public String getPaymentDetails(String paymentId) throws RazorpayException {
	    RazorpayClient razorpayClient = new RazorpayClient("rzp_test_LrHTWqr4yuxxA2", "ddLMK949k57f2ldSsOzwhxaI");
	    Payment paymentDetails = razorpayClient.payments.fetch(paymentId);
	    return paymentDetails.toString();

	
	
	}
}
