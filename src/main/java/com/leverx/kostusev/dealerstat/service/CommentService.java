package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.CommentDto;
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

    public List<CommentDto> findAll() {
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::entityToDto)
                .collect(toList());
    }

    @Transactional
    public void save(CommentDto comment) {
        commentRepository.save(commentMapper.dtoToEntity(comment));
    }

    @Transactional
    public void delete(CommentDto comment) {
        commentRepository.delete(commentMapper.dtoToEntity(comment));
    }
}
