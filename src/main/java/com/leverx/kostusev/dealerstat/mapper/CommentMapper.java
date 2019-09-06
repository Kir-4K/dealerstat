package com.leverx.kostusev.dealerstat.mapper;

import com.leverx.kostusev.dealerstat.dto.CommentDto;
import com.leverx.kostusev.dealerstat.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends BaseMapper<Comment, CommentDto> {
}
