package org.example.Service;

import org.example.dto.CouponRequest;

public class CouponService {
    public boolean applyCoupon(CouponRequest couponRequest) {
        return "FIRST".equals(couponRequest.getCouponCode());
    }
}
