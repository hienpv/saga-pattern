package com.saga.choreography.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_balance", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserBalanceEntity extends BaseEntity {
    @Column(name = "user_id")
    String userId;

    Long balance;
}
