package com.leverx.kostusev.dealerstat.controller;

import com.leverx.kostusev.dealerstat.dto.PasswordRecoveryDto;
import com.leverx.kostusev.dealerstat.dto.UserDto;
import com.leverx.kostusev.dealerstat.entity.ConfirmationToken;
import com.leverx.kostusev.dealerstat.exception.EmailAlreadyExistsException;
import com.leverx.kostusev.dealerstat.exception.TokenNotExistException;
import com.leverx.kostusev.dealerstat.exception.UserNotFoundException;
import com.leverx.kostusev.dealerstat.service.EmailSenderService;
import com.leverx.kostusev.dealerstat.service.TokenService;
import com.leverx.kostusev.dealerstat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
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

    @GetMapping(value = "/confirm")
    public void confirmation(@RequestParam("token") String token) {
        Optional<ConfirmationToken> confirmationToken = tokenService.findByToken(token);
        if (confirmationToken.isPresent()) {
            UserDto user = confirmationToken.get().getUser();
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            tokenService.delete(confirmationToken.get());
        } else {
            throw new TokenNotExistException();
        }
    }

    @PostMapping(value = "/forgot_password")
    public void forgotPass(@Valid @RequestBody PasswordRecoveryDto passwordRecoveryDto) {
        UserDto existingUser = userService.findByEmail(passwordRecoveryDto.getEmail())
                .orElseThrow(UserNotFoundException::new);

        ConfirmationToken token = tokenService.createConfirmationToken(existingUser);
        tokenService.save(token);
        emailSenderService.sendMail(token);
    }

    @PostMapping(value = "/reset")
    public void resetPass(@Valid @RequestBody PasswordRecoveryDto passwordRecoveryDto) {
        Optional<ConfirmationToken> confirmationToken = tokenService.findByToken(passwordRecoveryDto.getToken());
        if (confirmationToken.isPresent()) {
            UserDto user = confirmationToken.get().getUser();
            user.setPassword(passwordEncoder.encode(passwordRecoveryDto.getPassword()));
            userService.save(user);
            tokenService.delete(confirmationToken.get());
        } else {
            throw new TokenNotExistException();
        }
    }

    @GetMapping(value = "/check_code")
    public ResponseEntity<Object> checkCode(@RequestParam("token") String token) {
        return tokenService.findByToken(token)
                .map(entity -> ResponseEntity.ok().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
