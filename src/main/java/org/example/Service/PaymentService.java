package org.example.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.example.dto.PaymentRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("/{razorpay.key}")
    private String razorpaykey;

    @Value("/{razorpay.secret}")
    private String razorpaySecret;


    public String initiatePayment(PaymentRequest paymentRequest) {
        //Integerate external payment gateway

        try {
            RazorpayClient razorpayClient=new RazorpayClient(razorpaykey,razorpaySecret);
            JSONObject options=new JSONObject();
            options.put("amount",(paymentRequest.getAmount()*100));
            options.put("currency","INR");
            options.put("receipt","order_rcptid"+paymentRequest.getUserID());
            options.put("payment_capture",1);

            Order order=razorpayClient.orders.create(options);
            return order.toJson().get("id").toString();

        } catch (RazorpayException e) {
            throw new RuntimeException("Failed to initiate Razorpay order", e);
        }

    }
}
