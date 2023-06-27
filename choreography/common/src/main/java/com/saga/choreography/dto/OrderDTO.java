package com.saga.choreography.dto;

import com.saga.choreography.util.InventoryStatus;
import com.saga.choreography.util.OrderStatus;
import com.saga.choreography.util.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class OrderDTO {
    String uuid;
    String userId;
    String productId;
    Long price;
    Long quantity;
    OrderStatus orderStatus;
    PaymentStatus paymentStatus;
    InventoryStatus inventoryStatus;
}
