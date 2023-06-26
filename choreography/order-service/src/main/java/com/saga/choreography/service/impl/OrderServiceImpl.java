package com.saga.choreography.service.impl;

import com.saga.choreography.dto.OrderDto;
import com.saga.choreography.entity.OrderEntity;
import com.saga.choreography.entity.mapper.OrderMapper;
import com.saga.choreography.event.KafkaTopic;
import com.saga.choreography.repository.OrderRepository;
import com.saga.choreography.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public OrderDto save(OrderDto orderDto) {
        OrderEntity orderEntity = orderRepository.save(OrderMapper.INSTANCE.toEntity(orderDto));
        //public message
        kafkaTemplate.send(KafkaTopic.CREATED_ORDER.getCode(), orderEntity.getUuid());
        return OrderMapper.INSTANCE.toDto(orderEntity);
    }
}
