package com.saga.choreography.event;

import com.saga.choreography.util.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryEvent {
    private InventoryStatus status;
    private String orderId;
}
