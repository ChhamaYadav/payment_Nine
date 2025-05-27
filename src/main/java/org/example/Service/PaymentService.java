package org.example.Service;

import org.example.dto.PaymentRequest;

public class PaymentService {
    public String initiatePayment(PaymentRequest paymentRequest) {
        //Integerate external payment gateway
        return "https://payment-gateway.com/pay?token=abc123";


    }
}
