package com.saga.choreography.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.choreography.dto.EventDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@Slf4j
public class CustomDeserializerConfig implements Deserializer<EventDTO> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public EventDTO deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                log.info("Null received at deserializing");
                return null;
            }
            log.info("Deserializing...");
            return objectMapper.readValue(new String(data, "UTF-8"), EventDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to EventDto");
        }
    }

    @Override
    public void close() {
    }
}