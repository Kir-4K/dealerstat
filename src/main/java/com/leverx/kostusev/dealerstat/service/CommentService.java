package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.CommentDto;
import com.leverx.kostusev.dealerstat.entity.Comment;
import com.leverx.kostusev.dealerstat.mapper.CommentMapper;
import com.leverx.kostusev.dealerstat.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public Optional<CommentDto> findById(Long id) {
        return commentRepository.findById(id)
                .map(commentMapper::entityToDto);
    }

    public Optional<CommentDto> findByCommentIdAndUserId(Long userId, Long commentId) {
        return commentRepository.findByGameObject_User_IdAndId(userId, commentId)
                .map(commentMapper::entityToDto);
    }

    public List<CommentDto> findAll() {
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::entityToDto)
                .collect(toList());
    }

    public List<CommentDto> findAllByUserId(Long id) {
        return commentRepository.findAllByGameObject_User_Id(id)
                .stream()
                .map(commentMapper::entityToDto)
                .collect(toList());
    }

    @Transactional
    public CommentDto save(CommentDto comment) {
        Comment savedComments = commentRepository.save(commentMapper.dtoToEntity(comment));
        return commentMapper.entityToDto(savedComments);
    }

    @Transactional
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
