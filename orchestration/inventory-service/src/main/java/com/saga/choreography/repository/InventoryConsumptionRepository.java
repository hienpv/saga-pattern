package com.saga.choreography.repository;

import com.saga.choreography.entity.InventoryConsumptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryConsumptionRepository extends JpaRepository<InventoryConsumptionEntity, UUID> {
    Optional<InventoryConsumptionEntity> findByOrderId(String orderId);
}
