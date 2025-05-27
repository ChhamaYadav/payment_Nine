package org.example.Controller;

import org.apache.coyote.Response;
import org.example.Service.CartService;
import org.example.dto.CartSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment/cartsummary")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartSummary>getCart(@PathVariable Long userId){
        CartSummary summary=cartService.getCartSummary(userId);
        return ResponseEntity.ok(summary);
    }
}
