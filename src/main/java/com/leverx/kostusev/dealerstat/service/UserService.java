package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.UserDto;
import com.leverx.kostusev.dealerstat.entity.User;
import com.leverx.kostusev.dealerstat.mapper.UserMapper;
import com.leverx.kostusev.dealerstat.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class UserService extends BaseService<User, UserDto, UserRepository, UserMapper> {

    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    public Optional<UserDto> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::entityToDto);
    }
}
