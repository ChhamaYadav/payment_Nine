package org.example.Service;

import org.example.dto.CouponRequest;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
    public boolean applyCoupon(CouponRequest couponRequest) {
        return "FIRST10".equals(couponRequest.getCouponCode())||
        "WELCOME100".equals(couponRequest.getCouponCode());

    }
}
