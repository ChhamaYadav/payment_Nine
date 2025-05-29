package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartSummary {
    private Long userId;
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
    private double totalPrice;
}
