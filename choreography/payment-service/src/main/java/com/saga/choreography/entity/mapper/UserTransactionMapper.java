package com.saga.choreography.entity.mapper;

import com.saga.choreography.dto.InventoryDTO;
import com.saga.choreography.entity.UserTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "default")
public interface UserTransactionMapper {

    UserTransactionMapper INSTANCE = Mappers.getMapper(UserTransactionMapper.class);

    InventoryDTO toDto(UserTransactionEntity entity);

    UserTransactionEntity toEntity(InventoryDTO dto);
}
