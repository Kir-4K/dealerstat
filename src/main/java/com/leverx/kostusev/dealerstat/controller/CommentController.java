package com.leverx.kostusev.dealerstat.controller;

import com.leverx.kostusev.dealerstat.dto.CommentDto;
import com.leverx.kostusev.dealerstat.entity.GameObject;
import com.leverx.kostusev.dealerstat.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.springframework.beans.BeanUtils.copyProperties;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class CommentController {

    private static final String[] IGNORE_PROPERTIES = {"id", "createdAt", "user", "gameObject"};
    private final CommentService commentService;

    @GetMapping(value = "/users/{userId}/comments/{commentId}")
    public ResponseEntity<CommentDto> findByCommentIdAndUserId(@PathVariable("userId") Long userId,
                                                               @PathVariable("commentId") Long commentId) {
        return commentService.findByCommentIdAndUserId(userId, commentId)
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/users/{id}/comments")
    public List<CommentDto> findAllCommentsByUserId(@PathVariable("id") Long id) {
        return commentService.findAllByUserId(id);
    }

    @PostMapping(value = "/articles/{id}/comments")
    public CommentDto save(@PathVariable("id") Long id,
                           @Valid @RequestBody CommentDto comment) {
        comment.setGameObject(GameObject.builder().id(id).build());
        return commentService.save(comment);
    }

    @PutMapping(value = "/articles/{articlesId}/comments/{commentId}")
    public ResponseEntity<CommentDto> update(@PathVariable("articlesId") Long articlesId,
                                             @PathVariable("commentId") Long commentId,
                                             @Valid @RequestBody CommentDto updatableComment) {
        return commentService.findById(commentId)
                .map(entity -> {
                    copyProperties(updatableComment, entity, IGNORE_PROPERTIES);
                    CommentDto updated = commentService.save(entity);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/users/{userId}/comments/{commentId}")
    public ResponseEntity<?> delete(@PathVariable("userId") Long userId,
                                    @PathVariable("commentId") Long commentId) {
        return commentService.findById(commentId)
                .filter(comment -> isNotEmpty(comment.getUser()))
                .filter(comment -> userId.equals(comment.getUser().getId()))
                .map(entity -> {
                    commentService.deleteById(commentId);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
