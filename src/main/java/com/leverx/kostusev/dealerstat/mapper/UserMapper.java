package com.leverx.kostusev.dealerstat.mapper;

import com.leverx.kostusev.dealerstat.dto.UserDto;
import com.leverx.kostusev.dealerstat.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface UserMapper extends BaseMapper<User, UserDto> {

    @Mappings({
            @Mapping(target = "createdAt", source = "dto.createdAt", defaultExpression = "java( LocalDateTime.now() )"),
            @Mapping(target = "role", source = "dto.role", defaultValue = "TRADER")
    })
    User dtoToEntity(UserDto dto);
}
