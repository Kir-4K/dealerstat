package com.leverx.kostusev.dealerstat.repository;

import com.leverx.kostusev.dealerstat.entity.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Sql("classpath:test_script.sql")
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void testFindById() {
        Optional<Game> game = gameRepository.findById(1L);
        game.ifPresent(value -> assertThat(value.getName(), equalTo("CS:GO")));
    }

    @Test
    public void testFindAll() {
        List<Game> games = gameRepository.findAll();
        List<String> list = games.stream().map(Game::getName).collect(toList());
        assertThat(games, hasSize(2));
        assertThat(list, containsInAnyOrder("CS:GO", "DOTA"));
    }

    @Test
    public void testSave() {
        Game save = Game.builder()
                .name("The Witcher 3: Wild Hunt")
                .build();
        gameRepository.save(save);

        Optional<Game> game = gameRepository.findById(save.getId());
        game.ifPresent(value -> assertThat(value.getName(), equalTo("The Witcher 3: Wild Hunt")));
    }

    @Test
    public void testUpdate() {
        Optional<Game> update = gameRepository.findById(1L);
        if (update.isPresent()) {
            update.get().setName("CS:GO PRO");
            gameRepository.save(update.get());
        }

        Optional<Game> game = gameRepository.findById(1L);
        game.ifPresent(value -> assertThat(value.getName(), equalTo("CS:GO PRO")));
    }
}
