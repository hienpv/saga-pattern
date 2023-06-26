package com.saga.choreography.service;

import com.saga.choreography.dto.OrderDto;

public interface OrderService {
    OrderDto save(OrderDto orderDto);
}
