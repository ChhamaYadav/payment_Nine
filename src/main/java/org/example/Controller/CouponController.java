package org.example.Controller;

import org.apache.coyote.Response;
import org.example.Service.CouponService;
import org.example.dto.CouponRequest;
import org.example.dto.CouponResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/payment/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/apply")
    public ResponseEntity<CouponResponse> applyCoupon(@RequestBody CouponRequest request) {
        System.out.println("Coupon Applied");
        boolean isValid = couponService.applyCoupon(request);
//        ResponseEntity.ok(isValid ? "Coupon applied" : "Invalid coupon");
        double discountedAmount = request.getTotalAmount();
        String message;
        if (isValid) {
            switch (request.getCouponCode()) {
                case "FIRST10":
                case "WELCOME100":
                    discountedAmount -= 100.0;
                    message = "Coupon applied successfully !";
                    break;
                default:
                    message = "Valid coupon but no discount applied";
            }
        } else {
            message = "Invalid coupon";
        }

    CouponResponse couponResponse=new CouponResponse();
        couponResponse.setSuccess(isValid);
        couponResponse.setDiscountedAmount(discountedAmount);
        couponResponse.setMessage(message);
        return ResponseEntity.ok(couponResponse);
    }

}
