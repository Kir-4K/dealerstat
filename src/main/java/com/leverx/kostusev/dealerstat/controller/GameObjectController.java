package com.leverx.kostusev.dealerstat.controller;

import com.leverx.kostusev.dealerstat.dto.GameObjectDto;
import com.leverx.kostusev.dealerstat.entity.GameObject;
import com.leverx.kostusev.dealerstat.service.GameObjectService;
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
@RequestMapping(value = "/post")
public class GameObjectController {

    private static final String[] IGNORE_PROPERTIES = {"id", "createdAt", "user", "game"};

    private final GameObjectService gameObjectService;

    @GetMapping
    public List<GameObjectDto> findAll() {
        return gameObjectService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GameObjectDto> findById(@PathVariable("id") Long id) {
        return gameObjectService.findById(id)
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public GameObject save(@Valid @RequestBody GameObjectDto gameObject) {
        return gameObjectService.save(gameObject);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GameObject> update(@PathVariable Long id,
                                             @Valid @RequestBody GameObjectDto updatableGameObject) {
        return gameObjectService.findById(id)
                .map(entity -> {
                    copyProperties(updatableGameObject, entity, IGNORE_PROPERTIES);
                    GameObject updated = gameObjectService.save(entity);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return gameObjectService.findById(id)
                .map(entity -> {
                    gameObjectService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
