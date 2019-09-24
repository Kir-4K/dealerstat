package com.leverx.kostusev.dealerstat.repository;

import com.leverx.kostusev.dealerstat.entity.GameObject;
import com.leverx.kostusev.dealerstat.entity.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
public class GameObjectRepositoryTest {

    @Autowired
    private GameObjectRepository gameObjectRepository;

    @Test
    public void testFindById() {
        Optional<GameObject> gameObject = gameObjectRepository.findById(1L);
        gameObject.ifPresent(value -> assertThat(value.getText(), equalTo("Самый эпический автомат, который вы могли бы видеть")));
    }

    @Test
    public void testFindAll() {
        List<GameObject> gameObjects = gameObjectRepository.findAll();
        assertThat(gameObjects, hasSize(6));
    }

    @Test
    public void testFindAllByUser() {
        List<GameObject> gameObjects = gameObjectRepository.findAllByUser_Id(2L);
        assertThat(gameObjects, hasSize(3));
    }

    @Test
    public void testSave() {
        GameObject save = GameObject.builder()
                .title("Меч смерти")
                .text("Смертоносное оружие")
                .status(Status.APPROVED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now().minusHours(4))
                .build();
        gameObjectRepository.save(save);

        Optional<GameObject> gameObject = gameObjectRepository.findById(save.getId());
        gameObject.ifPresent(value -> assertThat(value.getTitle(), equalTo("Меч смерти")));
    }

    @Test
    public void testUpdate() {
        Optional<GameObject> update = gameObjectRepository.findById(1L);
        if (update.isPresent()) {
            update.get().setTitle("Новый супер Тайтл для АК-74");
            gameObjectRepository.save(update.get());
        }

        Optional<GameObject> gameObject = gameObjectRepository.findById(1L);
        gameObject.ifPresent(value -> assertThat(value.getTitle(), equalTo("Новый супер Тайтл для АК-74")));
    }
}
