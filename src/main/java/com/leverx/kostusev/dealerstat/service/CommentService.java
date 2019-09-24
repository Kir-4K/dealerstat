package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.CommentDto;
import com.leverx.kostusev.dealerstat.entity.Comment;
import com.leverx.kostusev.dealerstat.mapper.CommentMapper;
import com.leverx.kostusev.dealerstat.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@Service
public class CommentService extends BaseService<Comment, CommentDto, CommentRepository, CommentMapper> {

    public CommentService(CommentRepository repo, CommentMapper mapper) {
        super(repo, mapper);
    }

    public Optional<CommentDto> findByCommentIdAndUserId(Long userId, Long commentId) {
        return repository.findByGameObject_User_IdAndId(userId, commentId)
                .map(mapper::entityToDto);
    }

    public List<CommentDto> findAllByUserId(Long id) {
        return repository.findAllByGameObject_User_Id(id)
                .stream()
                .map(mapper::entityToDto)
                .collect(toList());
    }
}
