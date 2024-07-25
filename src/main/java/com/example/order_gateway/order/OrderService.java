package com.example.order_gateway.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {
    private final Map<String, Order> orderDatabase = new ConcurrentHashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    public Order createOrder(OrderCreateRequestDto request) {
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, "ACCEPTED", request);
        orderDatabase.put(orderId, order);
        forwardOrderToGateway(order);
        return order;
    }

    @Async
    private void forwardOrderToGateway(Order order) {
        String url = gatewayUrl + order.getOrderId();
        restTemplate.postForObject(url, order, String.class);
        updateOrderStatus(order.getOrderId(), "SENT");
    }

    public void updateOrderStatus(String orderId, String status) {
        Order order = orderDatabase.get(orderId);
        if (order != null) {
            order.setStatus(status);
        }
    }
}