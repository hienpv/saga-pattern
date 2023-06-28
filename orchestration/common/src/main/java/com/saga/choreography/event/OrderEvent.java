package com.saga.choreography.event;

import com.saga.choreography.dto.OrderDTO;
import com.saga.choreography.util.OrderStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderEvent {
    private OrderStatus status;
    private OrderDTO orderDTO;
}
