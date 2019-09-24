package com.leverx.kostusev.dealerstat.repository;

import com.leverx.kostusev.dealerstat.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Sql(value = {
        "classpath:drop_test_table.sql",
        "classpath:create_test_table",
        "classpath:insert_values_into_test_table.sql"
})
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testFindById() {
        Optional<Comment> comment = commentRepository.findById(1L);
        comment.ifPresent(value -> assertThat(value.getApproved(), equalTo(true)));
    }

    @Test
    public void testFindByIdAndUserId() {
        Optional<Comment> comment = commentRepository.findByGameObject_User_IdAndId(2L, 2L);
        comment.ifPresent(value -> assertThat(value.getId(), equalTo(2L)));
    }

    @Test
    public void testFindAll() {
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments, hasSize(7));
    }

    @Test
    public void testFindAllByUserId() {
        List<Comment> comments = commentRepository.findAllByGameObject_User_Id(2L);
        assertThat(comments, hasSize(5));
    }

    @Test
    public void testSave() {
        Comment save = Comment.builder()
                .message("Норм так, че")
                .rating(4)
                .createdAt(now())
                .approved(true)
                .build();
        commentRepository.save(save);

        Optional<Comment> comment = commentRepository.findById(save.getId());
        comment.ifPresent(value -> assertThat(value.getMessage(), equalTo("Норм так, че")));
    }

    @Test
    public void testUpdate() {
        Optional<Comment> update = commentRepository.findById(1L);
        if (update.isPresent()) {
            update.get().setMessage("Другой коммнетарий");
            commentRepository.save(update.get());
        }

        Optional<Comment> comment = commentRepository.findById(1L);
        comment.ifPresent(value -> assertThat(value.getMessage(), equalTo("Другой коммнетарий")));
    }
}
