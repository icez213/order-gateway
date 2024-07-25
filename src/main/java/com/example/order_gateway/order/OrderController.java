package com.example.order_gateway.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/order", produces = "application/json")
    public ResponseEntity<?> create(@RequestBody OrderCreateRequestDto request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.accepted().body(order);
    }
}
