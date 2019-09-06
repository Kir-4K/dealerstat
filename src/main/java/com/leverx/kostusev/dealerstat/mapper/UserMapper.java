package com.leverx.kostusev.dealerstat.mapper;

import com.leverx.kostusev.dealerstat.dto.UserDto;
import com.leverx.kostusev.dealerstat.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
}
