package com.saga.choreography.service.impl;

import com.saga.choreography.dto.InventoryConsumptionDTO;
import com.saga.choreography.dto.InventoryDTO;
import com.saga.choreography.dto.OrderDTO;
import com.saga.choreography.entity.InventoryConsumptionEntity;
import com.saga.choreography.entity.InventoryEntity;
import com.saga.choreography.entity.mapper.InventoryConsumptionMapper;
import com.saga.choreography.entity.mapper.InventoryMapper;
import com.saga.choreography.event.InventoryEvent;
import com.saga.choreography.repository.InventoryConsumptionRepository;
import com.saga.choreography.repository.InventoryRepository;
import com.saga.choreography.service.InventoryService;
import com.saga.choreography.util.InventoryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final InventoryConsumptionRepository inventoryConsumptionRepository;

    @Override
    @Transactional
    public InventoryEvent newOrderInventory(OrderDTO orderDTO) {
        Optional<InventoryEntity> opInventory = inventoryRepository.findByProductId(orderDTO.getProductId());
        if (opInventory.isPresent() && opInventory.get().getQuantity() - orderDTO.getQuantity() >= 0) {
            InventoryEntity inventoryEntity = opInventory.get();
            inventoryEntity.setQuantity(inventoryEntity.getQuantity() - orderDTO.getQuantity());
            inventoryRepository.save(inventoryEntity);

            InventoryConsumptionDTO inventoryConsumptionDTO = InventoryConsumptionDTO.builder()
                    .orderId(orderDTO.getUuid())
                    .productId(orderDTO.getProductId())
                    .quantity(orderDTO.getQuantity())
                    .build();

            inventoryConsumptionRepository.save(InventoryConsumptionMapper.INSTANCE.toEntity(inventoryConsumptionDTO));
            return InventoryEvent.builder()
                    .status(InventoryStatus.RESERVED)
                    .orderId(orderDTO.getUuid())
                    .build();
        } else {
            return InventoryEvent.builder()
                    .status(InventoryStatus.REJECTED)
                    .orderId(orderDTO.getUuid())
                    .build();
        }
    }

    @Override
    @Transactional
    public void cancelOrderInventory(OrderDTO orderDTO) {
        Optional<InventoryConsumptionEntity> opIc = inventoryConsumptionRepository.findByOrderId(orderDTO.getUuid());
        if (opIc.isPresent()) {
            Optional<InventoryEntity> opInventory = inventoryRepository.findByProductId(orderDTO.getProductId());
            if (opInventory.isPresent()) {
                InventoryEntity inventoryEntity = opInventory.get();
                inventoryEntity.setQuantity(inventoryEntity.getQuantity() + orderDTO.getQuantity());
                inventoryRepository.save(inventoryEntity);
            }

            inventoryConsumptionRepository.delete(opIc.get());
        }
    }
}
