package com.leverx.kostusev.dealerstat.mapper;

import com.leverx.kostusev.dealerstat.dto.CommentDto;
import com.leverx.kostusev.dealerstat.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface CommentMapper extends BaseMapper<Comment, CommentDto> {

    @Mappings({
            @Mapping(target = "createdAt", source = "dto.createdAt", defaultExpression = "java( LocalDateTime.now() )"),
            @Mapping(target = "approved", source = "dto.approved", defaultValue = "false")
    })
    Comment dtoToEntity(CommentDto dto);
}
