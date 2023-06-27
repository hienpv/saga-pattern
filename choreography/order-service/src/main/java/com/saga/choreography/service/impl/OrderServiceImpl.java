package com.saga.choreography.service.impl;

import com.saga.choreography.dto.OrderDTO;
import com.saga.choreography.entity.OrderEntity;
import com.saga.choreography.entity.mapper.OrderMapper;
import com.saga.choreography.event.OrderEvent;
import com.saga.choreography.repository.OrderRepository;
import com.saga.choreography.service.KafkaPublisherService;
import com.saga.choreography.service.OrderService;
import com.saga.choreography.util.InventoryStatus;
import com.saga.choreography.util.KafkaTopic;
import com.saga.choreography.util.OrderStatus;
import com.saga.choreography.util.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final KafkaPublisherService kafkaPublisherService;

    @Override
    public OrderDTO save(OrderDTO orderDto) {
        if (orderDto.getOrderStatus() == null) {
            orderDto.setOrderStatus(OrderStatus.ORDER_CREATED);
        }

        if (orderDto.getPaymentStatus() == null) {
            orderDto.setPaymentStatus(PaymentStatus.WAITING);
        }

        if (orderDto.getInventoryStatus() == null) {
            orderDto.setInventoryStatus(InventoryStatus.WAITING);
        }

        OrderEntity orderEntity = orderRepository.save(OrderMapper.INSTANCE.toEntity(orderDto));

        OrderEvent orderEvent = OrderEvent.builder()
                .status(OrderStatus.ORDER_CREATED)
                .orderDTO(OrderMapper.INSTANCE.toDto(orderEntity))
                .build();
        kafkaPublisherService.raiseEvent(KafkaTopic.ORDER_EVENT.getCode(), orderEvent);

        return OrderMapper.INSTANCE.toDto(orderEntity);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderRepository.findById(orderDTO.getUuid()).ifPresent(x -> {
            x.setInventoryStatus(orderDTO.getInventoryStatus());
            if (InventoryStatus.REJECTED.equals(orderDTO.getInventoryStatus())) {
                x.setOrderStatus(OrderStatus.ORDER_CANCELLED);
            }
            orderRepository.save(x);
        });
        return orderDTO;
    }
}
