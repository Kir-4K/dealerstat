package com.leverx.kostusev.dealerstat.mapper;

import com.leverx.kostusev.dealerstat.dto.GameDto;
import com.leverx.kostusev.dealerstat.entity.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper extends BaseMapper<Game, GameDto> {
}
