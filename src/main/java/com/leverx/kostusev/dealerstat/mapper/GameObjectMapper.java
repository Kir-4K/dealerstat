package com.leverx.kostusev.dealerstat.mapper;

import com.leverx.kostusev.dealerstat.dto.GameObjectDto;
import com.leverx.kostusev.dealerstat.entity.GameObject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameObjectMapper extends BaseMapper<GameObject, GameObjectDto> {
}
