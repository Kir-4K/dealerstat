package com.leverx.kostusev.dealerstat.controller;

import com.leverx.kostusev.dealerstat.dto.UserDto;
import com.leverx.kostusev.dealerstat.entity.ConfirmationToken;
import com.leverx.kostusev.dealerstat.entity.User;
import com.leverx.kostusev.dealerstat.exception.EmailAlreadyExistsException;
import com.leverx.kostusev.dealerstat.exception.TokenNotExistException;
import com.leverx.kostusev.dealerstat.service.EmailSenderService;
import com.leverx.kostusev.dealerstat.service.TokenService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class UserController {

    private static final String[] IGNORE_PROPERTIES = {"id", "createdAt", "role"};
    private final UserService userService;
    private final TokenService tokenService;
    private final EmailSenderService emailSenderService;

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/users")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @PostMapping(value = "/auth")
    public void registration(@Valid @RequestBody UserDto user) {
        Optional<UserDto> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException();
        } else {
            user.setEnabled(false);
            ConfirmationToken token = tokenService.createConfirmationToken(user);
            tokenService.save(token);
            emailSenderService.sendMail(token);
        }
    }

    @GetMapping(value = "/auth/confirm")
    public void confirmation(@RequestParam("token") String token) {
        Optional<ConfirmationToken> confirmationToken = tokenService.findByToken(token);
        if (confirmationToken.isPresent()) {
            UserDto user = confirmationToken.get().getUser();
            user.setEnabled(true);
            userService.save(user);
        } else {
            throw new TokenNotExistException();
        }
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id,
                                       @Valid @RequestBody UserDto updatableUser) {
        return userService.findById(id)
                .map(entity -> {
                    copyProperties(updatableUser, entity, IGNORE_PROPERTIES);
                    User updated = userService.save(entity);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(entity -> {
                    userService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
