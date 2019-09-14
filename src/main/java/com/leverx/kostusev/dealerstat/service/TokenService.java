package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class TokenService {

    private final TokenRepository tokenRepository;
}
