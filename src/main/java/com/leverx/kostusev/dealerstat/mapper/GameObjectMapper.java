package com.leverx.kostusev.dealerstat.mapper;

import com.leverx.kostusev.dealerstat.dto.GameObjectDto;
import com.leverx.kostusev.dealerstat.entity.GameObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface GameObjectMapper extends BaseMapper<GameObject, GameObjectDto> {

    @Mappings({
            @Mapping(target = "createdAt", source = "dto.createdAt", defaultExpression = "java( LocalDateTime.now() )"),
            @Mapping(target = "updatedAt", source = "dto.updatedAt", defaultExpression = "java( LocalDateTime.now() )"),
            @Mapping(target = "status", source = "dto.status", defaultValue = "UNPROCESSED")
    })
    GameObject dtoToEntity(GameObjectDto dto);
}
