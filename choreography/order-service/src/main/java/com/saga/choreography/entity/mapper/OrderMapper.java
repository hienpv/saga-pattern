package com.saga.choreography.entity.mapper;

import com.saga.choreography.dto.OrderDTO;
import com.saga.choreography.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "default")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO toDto(OrderEntity entity);

    OrderEntity toEntity(OrderDTO dto);
}
