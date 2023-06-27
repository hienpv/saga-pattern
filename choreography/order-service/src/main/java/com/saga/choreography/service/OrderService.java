package com.saga.choreography.service;

import com.saga.choreography.dto.OrderDTO;

public interface OrderService {
    OrderDTO save(OrderDTO orderDto);

    OrderDTO updateOrder(OrderDTO orderDTO);
}
