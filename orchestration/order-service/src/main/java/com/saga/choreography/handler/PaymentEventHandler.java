package com.saga.choreography.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.choreography.dto.EventDTO;
import com.saga.choreography.dto.OrderDTO;
import com.saga.choreography.event.PaymentEvent;
import com.saga.choreography.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentEventHandler {
    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @KafkaListener(topics = "payment-event")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        EventDTO eventDto = (EventDTO) consumerRecord.value();
        PaymentEvent paymentEvent = objectMapper.convertValue(eventDto.getPayload(), PaymentEvent.class);
        log.info("inventory-event : received payload='{}'", paymentEvent.toString());
        OrderDTO orderDTO = OrderDTO.builder()
                .uuid(paymentEvent.getOrderId())
                .paymentStatus(paymentEvent.getStatus())
                .build();
        orderService.updateOrder(orderDTO);
    }
}
