package com.leverx.kostusev.dealerstat.repository;

import com.leverx.kostusev.dealerstat.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByGameObject_User_Id(Long id);

    Optional<Comment> findByGameObject_User_IdAndId(Long userId, Long commentId);
}
