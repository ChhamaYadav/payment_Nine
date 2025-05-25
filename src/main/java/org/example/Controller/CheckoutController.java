package org.example.Controller;

import org.example.Service.CheckoutService;
import org.example.dto.CheckoutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    public ResponseEntity<String> processOrder(@RequestBody CheckoutRequest request){
        checkoutService.processOrder(request);
        return ResponseEntity.ok("Order processed successfully ! ");
    }

}
