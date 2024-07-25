package com.example.order_gateway.gateway;

import org.springframework.stereotype.Service;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class GatewayService {

    public void acknowledgeOrder(String orderId) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            System.out.println("Order " + orderId + " acknowledged");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}