package com.saga.choreography.service;

public interface KafkaPublisherService {
    void raiseEvent(String topic, Object payload);
}
