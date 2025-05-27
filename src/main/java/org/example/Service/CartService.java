package org.example.Service;

import org.example.dto.CartSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class CartService {

    RestTemplate  restTemplate=new RestTemplate();

    public ResponseEntity<Map<String,Object>> getCartSummary(Long userId) {
        String url="http://localhost:8082/cart/details/"+userId;
        ResponseEntity<Map> response= restTemplate.getForEntity(url, Map.class);
        return new ResponseEntity<>(response.getBody(),response.getStatusCode());
    }
}
