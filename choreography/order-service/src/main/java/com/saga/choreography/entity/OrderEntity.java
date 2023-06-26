package com.saga.choreography.entity;

import com.saga.choreography.event.InventoryStatus;
import com.saga.choreography.event.OrderStatus;
import com.saga.choreography.event.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Orders", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderEntity extends BaseEntity{
    @Column(name = "user_id")
    String userId;

    @Column(name = "product_id")
    String productId;
    Long price;
    Long quantity;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;

    @Column(name = "inventory_status")
    @Enumerated(EnumType.STRING)
    InventoryStatus inventoryStatus;
}
