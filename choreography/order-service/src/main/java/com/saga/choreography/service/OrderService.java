package com.saga.choreography.service;

import com.saga.choreography.dto.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDto);

    void updateOrder(OrderDTO orderDTO);
}
