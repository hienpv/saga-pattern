package com.saga.choreography.entity.mapper;

import com.saga.choreography.dto.InventoryConsumptionDTO;
import com.saga.choreography.entity.InventoryConsumptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "default")
public interface InventoryConsumptionMapper {

    InventoryConsumptionMapper INSTANCE = Mappers.getMapper(InventoryConsumptionMapper.class);

    InventoryConsumptionDTO toDto(InventoryConsumptionEntity entity);

    InventoryConsumptionEntity toEntity(InventoryConsumptionDTO dto);
}
