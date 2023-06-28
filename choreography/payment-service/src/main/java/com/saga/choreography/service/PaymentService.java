package com.saga.choreography.service;

import com.saga.choreography.dto.OrderDTO;
import com.saga.choreography.event.PaymentEvent;

public interface PaymentService {
    PaymentEvent newOrderPayment(OrderDTO orderDTO);

    void cancelOrderPayment(OrderDTO orderDTO);
}
