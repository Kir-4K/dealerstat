package com.leverx.kostusev.dealerstat.controller;

import com.leverx.kostusev.dealerstat.dto.GameDto;
import com.leverx.kostusev.dealerstat.entity.Game;
import com.leverx.kostusev.dealerstat.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping(value = "/games")
public class GameController {

    private static final String[] IGNORE_PROPERTIES = {"id"};

    private final GameService gameService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<GameDto> findById(@PathVariable("id") Long id) {
        return gameService.findById(id)
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<GameDto> findAll() {
        return gameService.findAll();
    }

    @PostMapping
    public Game save(@Valid @RequestBody GameDto game) {
        return gameService.save(game);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Game> update(@PathVariable Long id,
                                       @Valid @RequestBody GameDto updatableGame) {
        return gameService.findById(id)
                .map(entity -> {
                    copyProperties(updatableGame, entity, IGNORE_PROPERTIES);
                    Game updated = gameService.save(entity);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return gameService.findById(id)
                .map(entity -> {
                    gameService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
