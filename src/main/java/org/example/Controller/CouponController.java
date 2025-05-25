package org.example.Controller;

import org.apache.coyote.Response;
import org.example.Service.CouponService;
import org.example.dto.CouponRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/apply")
    public ResponseEntity<String> applyCoupon(@RequestBody CouponRequest request) {
        boolean isValid = couponService.applyCoupon(request);
        return ResponseEntity.ok(isValid ? "Coupon applied" : "Invalid coupon");
    }

}
