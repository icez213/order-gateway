package com.example.order_gateway.gateway;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GatewayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAcknowledgeOrder() throws Exception {
        String orderId = "12345";
        String orderJson = "{\"orderId\":\"" + orderId + "\",\"status\":\"ACCEPTED\",\"clOrdID\":\"ABC123\",\"side\":\"1\",\"price\":100.5,\"orderQty\":10,\"symbol\":\"AAPL\"}";

        mockMvc.perform(post("/acknowledge/" + orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk());
    }
}