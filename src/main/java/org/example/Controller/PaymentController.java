package org.example.Controller;

import org.example.Service.PaymentService;
import org.example.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    public ResponseEntity<String> initiatePayment(@RequestBody PaymentRequest paymentRequest){
        String paymenturl=paymentService.initiatePayment(paymentRequest);
        return ResponseEntity.ok(paymenturl);
    }
}
