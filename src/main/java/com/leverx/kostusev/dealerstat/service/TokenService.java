package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.UserDto;
import com.leverx.kostusev.dealerstat.entity.ConfirmationToken;
import com.leverx.kostusev.dealerstat.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class TokenService {

    private static final Long DAYS = 1L;
    private final TokenRepository tokenRepository;

    public ConfirmationToken createConfirmationToken(UserDto user) {
        return ConfirmationToken.builder()
                .user(user)
                .token(getUniqueToken())
                .expiration(DAYS)
                .build();
    }

    private String getUniqueToken() {
        return UUID.randomUUID().toString();
    }

    public Optional<ConfirmationToken> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void save(ConfirmationToken token) {
        tokenRepository.save(token);
    }
}
