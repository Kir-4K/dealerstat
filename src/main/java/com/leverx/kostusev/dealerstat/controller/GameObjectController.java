package com.leverx.kostusev.dealerstat.controller;

import com.leverx.kostusev.dealerstat.dto.GameObjectDto;
import com.leverx.kostusev.dealerstat.dto.UserDto;
import com.leverx.kostusev.dealerstat.entity.GameObject;
import com.leverx.kostusev.dealerstat.exception.UserNotFoundException;
import com.leverx.kostusev.dealerstat.mapper.GameObjectMapper;
import com.leverx.kostusev.dealerstat.service.GameObjectService;
import com.leverx.kostusev.dealerstat.service.UserService;
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
import java.security.Principal;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.springframework.beans.BeanUtils.copyProperties;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping(value = "/articles")
public class GameObjectController {

    private static final String[] IGNORE_PROPERTIES = {"id", "createdAt", "user", "game"};

    private final UserService userService;
    private final GameObjectService gameObjectService;
    private final GameObjectMapper gameObjectMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<GameObjectDto> findById(@PathVariable("id") Long id) {
        return gameObjectService.findById(id)
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<GameObjectDto> findAll() {
        return gameObjectService.findAll();
    }

    @PostMapping
    public GameObject save(@Valid @RequestBody GameObjectDto gameObject, Principal principal) {
        UserDto user = userService.findByEmail(principal.getName()).orElseThrow(UserNotFoundException::new);
        gameObject.setUser(user);
        return gameObjectService.save(gameObject);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GameObjectDto> update(@PathVariable Long id,
                                                @Valid @RequestBody GameObjectDto updatableGameObject,
                                                Principal principal) {
        return gameObjectService.findById(id)
                .filter(gameObject -> isNotEmpty(principal))
                .filter(gameObject -> gameObject.getUser().getEmail().equals(principal.getName()))
                .map(entity -> {
                    copyProperties(updatableGameObject, entity, IGNORE_PROPERTIES);
                    GameObjectDto updated = gameObjectMapper.entityToDto(gameObjectService.save(entity));
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id, Principal principal) {
        return gameObjectService.findById(id)
                .filter(gameObject -> isNotEmpty(principal))
                .filter(gameObject -> gameObject.getUser().getEmail().equals(principal.getName()))
                .map(entity -> {
                    gameObjectService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
