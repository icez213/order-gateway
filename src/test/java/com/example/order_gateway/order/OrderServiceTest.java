package com.example.order_gateway.order;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private RestTemplate restTemplate;

    private ConcurrentHashMap<String, Order> orderDatabase;

    @BeforeEach
    public void setUp() throws Exception {
        // Use reflection to set the orderDatabase field
        Field field = OrderService.class.getDeclaredField("orderDatabase");
        field.setAccessible(true);
        orderDatabase = new ConcurrentHashMap<>();
        field.set(orderService, orderDatabase);
    }

    @Test
    public void testCreateOrder() {
        OrderCreateRequestDto request = new OrderCreateRequestDto();
        request.setClOrdID("ABC123");
        request.setSide('1');
        request.setPrice(100.5);
        request.setOrderQty(10);
        request.setSymbol("AAPL");

        Order order = orderService.createOrder(request);

        assertNotNull(order.getOrderId());
        assertEquals("SENT", order.getStatus());
        assertEquals(request.getClOrdID(), order.getClOrdID());
        assertEquals(request.getSide(), order.getSide());
        assertEquals(request.getPrice(), order.getPrice(), 0.001);
        assertEquals(request.getOrderQty(), order.getOrderQty());
        assertEquals(request.getSymbol(), order.getSymbol());
    }

    @Test
    public void testUpdateOrderStatus() {
        OrderCreateRequestDto request = new OrderCreateRequestDto();
        request.setClOrdID("ABC123");
        request.setSide('1');
        request.setPrice(100.5);
        request.setOrderQty(10);
        request.setSymbol("AAPL");

        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, "ACCEPTED", request);

        orderDatabase.put(orderId, order);  // Directly put the order in the database

        orderService.updateOrderStatus(orderId, "SENT");

        assertEquals("SENT", order.getStatus());
    }
}