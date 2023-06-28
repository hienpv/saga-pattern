package com.saga.choreography.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.choreography.dto.EventDTO;
import com.saga.choreography.event.OrderEvent;
import com.saga.choreography.event.PaymentEvent;
import com.saga.choreography.service.KafkaPublisherService;
import com.saga.choreography.service.PaymentService;
import com.saga.choreography.util.KafkaTopic;
import com.saga.choreography.util.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventHandler {
    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;
    private final KafkaPublisherService kafkaPublisherService;

    @KafkaListener(topics = "order-event")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        EventDTO eventDto = (EventDTO) consumerRecord.value();
        OrderEvent orderEvent = objectMapper.convertValue(eventDto.getPayload(), OrderEvent.class);
        log.info("received payload='{}'", orderEvent.toString());
        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getStatus())) {
            PaymentEvent paymentEvent = paymentService.newOrderPayment(orderEvent.getOrderDTO());
            kafkaPublisherService.raiseEvent(KafkaTopic.PAYMENT_EVENT.getCode(), paymentEvent);
        } else if (OrderStatus.ORDER_CANCELLED.equals(orderEvent.getStatus())) {
            paymentService.cancelOrderPayment(orderEvent.getOrderDTO());
        } else {
            log.info("Process order complete");
        }
    }
}
