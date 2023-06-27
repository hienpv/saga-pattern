package com.saga.choreography.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EventDTO {
    private Object payload;
}
