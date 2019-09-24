package com.leverx.kostusev.dealerstat.mapper;

import com.leverx.kostusev.dealerstat.dto.BaseDto;
import com.leverx.kostusev.dealerstat.entity.BaseEntity;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {

    E dtoToEntity(D dto);

    D entityToDto(E entity);
}
