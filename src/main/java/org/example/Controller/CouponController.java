package org.example.Controller;

import org.apache.coyote.Response;
import org.example.Service.CouponService;
import org.example.dto.CouponRequest;
import org.example.dto.CouponResponse;
import org.example.dto.ShippingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;

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

    @PostMapping("/shipping/calculate")
    public ResponseEntity<?> calculateController(@RequestBody ShippingRequest request) {
        System.out.println("Calling shipping service");
        String country= request.getCountry().trim().toLowerCase();
        double shippingAmount;
        switch (country) {
            case "india":
                shippingAmount = 150.0;
                break;
            case "usa":
                shippingAmount = 1300.0;
                break;
            case "uk":
                shippingAmount = 1250.0;
                break;
            case "australia":
                shippingAmount = 1280.0;
                break;
            default:
                shippingAmount = 1400.0; // default for unsupported/other countries
                break;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("Success",true);
        response.put("shippingAmount",shippingAmount);
        response.put("message","Shipping calculated for"+country);

        return ResponseEntity.ok(response);

    }
}
