package com.saga.choreography.dto;

import com.saga.choreography.event.InventoryStatus;
import com.saga.choreography.event.OrderStatus;
import com.saga.choreography.event.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderDto {
    String userId;
    String productId;
    Long price;
    Long quantity;
    OrderStatus orderStatus;
    PaymentStatus paymentStatus;
    InventoryStatus inventoryStatus;
}
