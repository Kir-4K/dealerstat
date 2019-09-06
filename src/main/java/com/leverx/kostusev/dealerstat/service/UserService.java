package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.UserDto;
import com.leverx.kostusev.dealerstat.entity.User;
import com.leverx.kostusev.dealerstat.mapper.UserMapper;
import com.leverx.kostusev.dealerstat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::entityToDto);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::entityToDto)
                .collect(toList());
    }

    @Transactional
    public User save(UserDto user) {
        return userRepository.save(userMapper.dtoToEntity(user));
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
