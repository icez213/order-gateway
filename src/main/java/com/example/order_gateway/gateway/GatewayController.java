package com.example.order_gateway.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GatewayController {

    @Autowired
    GatewayService gatewayService;

    @PostMapping(value = "/acknowledge/{orderId}", produces = "application/json")
    public void acknowledge(@PathVariable String orderId) {
        gatewayService.acknowledgeOrder(orderId);
    }
}
