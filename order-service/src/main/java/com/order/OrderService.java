package com.order;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderDTO createOrder(Long productId, Integer quantity);
    OrderDTO getOrderById(Long orderId);

    List<OrderDTO> getAllOrders();
}
