package com.leverx.kostusev.dealerstat.controller;

import com.leverx.kostusev.dealerstat.dto.UserDto;
import com.leverx.kostusev.dealerstat.entity.User;
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
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final String[] IGNORE_PROPERTIES = {"id", "createdAt", "role"};
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User save(@Valid @RequestBody UserDto user) {
        return userService.save(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,
                                       @Valid @RequestBody UserDto updatableUser) {
        return userService.findById(id)
                .map(entity -> {
                    copyProperties(updatableUser, entity, IGNORE_PROPERTIES);
                    User updated = userService.save(entity);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(entity -> {
                    userService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
