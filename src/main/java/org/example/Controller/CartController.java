package org.example.Controller;

import org.apache.coyote.Response;
import org.example.Service.CartService;
import org.example.dto.CartSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/payment/cartsummary")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String,Object>> getALlCartItems(@PathVariable Long userId) {
        System.out.println("Calling http://localhost:8082/cart/details/" + userId);
        RestTemplate restTemplate = new
                RestTemplate();
        String url = "http://localhost:8082/cart/details/" + userId;

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        } catch (Exception e) {
            System.err.println("Failed to fetch cart details:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
