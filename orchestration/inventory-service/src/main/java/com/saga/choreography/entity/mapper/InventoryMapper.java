package com.saga.choreography.entity.mapper;

import com.saga.choreography.dto.InventoryDTO;
import com.saga.choreography.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "default")
public interface InventoryMapper {

    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    InventoryDTO toDto(InventoryEntity entity);

    InventoryEntity toEntity(InventoryDTO dto);
}
