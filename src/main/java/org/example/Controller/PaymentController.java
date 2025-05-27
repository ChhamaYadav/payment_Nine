package org.example.Controller;

import com.razorpay.RazorpayException;
import org.example.Service.PaymentService;
import org.example.dto.PaymentRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.razorpay.Utils.verifySignature;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<String> initiatePayment(@RequestBody PaymentRequest paymentRequest){
        String paymenturl=paymentService.initiatePayment(paymentRequest);
        return ResponseEntity.ok(paymenturl);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> handlePayment(
            @RequestBody String payload,
            @RequestHeader("X-Razorpay-Signature") String razorpaySignature
    ){

        try {

            String webhookSecret="webhook_secret";

            if (!verifySignature(payload, razorpaySignature, webhookSecret)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature");
            }

            JSONObject event=new JSONObject(payload);
            String eventType=event.getString("event");

            if("payment.capture".equals(eventType)) {
                JSONObject payment = event
                        .getJSONObject("payload")
                        .getJSONObject("payment")
                        .getJSONObject("entity");


                // Extract payment details
                String razorpayOrderId = payment.getString("order_id");
                String paymentId = payment.getString("id");
                double amount = payment.getDouble("amount") / 100.0;

                String userEmail = payment.optJSONObject("notes") != null
                        ? payment.getJSONObject("notes").optString("user_email", "unknown@example.com")
                        : "unknown@example.com";

                // Call your order API
//                orderService.createOrder(razorpayOrderId, paymentId, amount, userEmail);

                // Send confirmation emails
//                emailService.sendUserConfirmation(userEmail);
//                emailService.sendSupplierNotification();

                return ResponseEntity.ok("Payment verified and order processed");
            }
            return ResponseEntity.ok("Event ignored");

        } catch (RazorpayException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Webhook handling failed");
        }
    }
}
