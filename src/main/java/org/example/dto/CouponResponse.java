package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponResponse {
    private boolean success;
    private String message;
    private double discountedAmount;

}
