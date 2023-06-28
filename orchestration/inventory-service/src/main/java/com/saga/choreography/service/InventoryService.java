package com.saga.choreography.service;

import com.saga.choreography.dto.OrderDTO;
import com.saga.choreography.event.InventoryEvent;

public interface InventoryService {
    InventoryEvent newOrderInventory(OrderDTO orderDTO);

    void cancelOrderInventory(OrderDTO orderDTO);
}
