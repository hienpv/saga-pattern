package com.saga.choreography.entity.mapper;

import com.saga.choreography.dto.InventoryConsumptionDTO;
import com.saga.choreography.entity.UserBalanceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "default")
public interface UserBalanceMapper {

    UserBalanceMapper INSTANCE = Mappers.getMapper(UserBalanceMapper.class);

    InventoryConsumptionDTO toDto(UserBalanceEntity entity);

    UserBalanceEntity toEntity(InventoryConsumptionDTO dto);
}
