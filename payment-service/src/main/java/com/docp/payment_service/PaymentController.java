package com.docp.payment_service;


import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create/order")
    public String createOrder(@RequestParam int amount, @RequestParam String currency) throws RazorpayException {
       return paymentService.createOrder(amount, currency, "12345");
    }

}
