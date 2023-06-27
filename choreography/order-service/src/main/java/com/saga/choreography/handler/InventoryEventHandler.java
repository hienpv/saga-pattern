package com.saga.choreography.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.choreography.dto.EventDTO;
import com.saga.choreography.dto.OrderDTO;
import com.saga.choreography.event.InventoryEvent;
import com.saga.choreography.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryEventHandler {
    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @KafkaListener(topics = "inventory-event")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        EventDTO eventDto = (EventDTO) consumerRecord.value();
        InventoryEvent inventoryEvent = objectMapper.convertValue(eventDto.getPayload(), InventoryEvent.class);
        log.info("received payload='{}'", inventoryEvent.toString());
        OrderDTO orderDTO = OrderDTO.builder()
                .uuid(inventoryEvent.getInventoryConsumptionDTO().getOrderId())
                .build();
        orderDTO.setInventoryStatus(inventoryEvent.getStatus());
        orderService.updateOrder(orderDTO);
    }
}
