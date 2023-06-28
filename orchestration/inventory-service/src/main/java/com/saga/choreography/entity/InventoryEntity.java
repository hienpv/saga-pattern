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
@Table(name = "inventory", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class InventoryEntity extends BaseEntity {
    @Column(name = "product_id")
    String productId;
    Long quantity;
}
