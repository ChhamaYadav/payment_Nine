package org.example.Service;

import org.example.dto.CartSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class CartService {

    @Value("${cart.api.url}")
    private String cartApiUrl;

    RestTemplate  restTemplate=new RestTemplate();

    public ResponseEntity<CartSummary> getCartSummary(Long userId) {
        String url=cartApiUrl+"/cart/details/"+userId;
        ResponseEntity<CartSummary> response= restTemplate.getForEntity(url, CartSummary.class);
        return new ResponseEntity<>(response.getBody(),response.getStatusCode());
    }
}
