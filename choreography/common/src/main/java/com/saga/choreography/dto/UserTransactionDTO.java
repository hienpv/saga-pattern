package com.saga.choreography.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserTransactionDTO {
    String uuid;

    String orderId;

    String userId;

    Long balance;
}
