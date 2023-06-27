package com.saga.choreography.service.impl;


import com.saga.choreography.dto.EventDTO;
import com.saga.choreography.service.KafkaPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaPublisherServiceImpl implements KafkaPublisherService {
    private final KafkaTemplate<String, EventDTO> kafkaTemplate;

    @Override
    public void raiseEvent(String topic, Object payload) {
        EventDTO eventDto = EventDTO.builder()
                .payload(payload)
                .build();
        kafkaTemplate.send(topic, eventDto);
    }
}
