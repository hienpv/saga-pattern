package com.saga.choreography.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryConsumptionDTO{
    String uuid;
    String productId;
    String orderId;
    Long quantity;
}
