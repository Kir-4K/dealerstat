package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.CommentDto;
import com.leverx.kostusev.dealerstat.dto.UserDto;
import com.leverx.kostusev.dealerstat.entity.User;
import com.leverx.kostusev.dealerstat.mapper.UserMapper;
import com.leverx.kostusev.dealerstat.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Function;

import static com.leverx.kostusev.dealerstat.entity.Role.TRADER;
import static java.util.stream.Collectors.toMap;

@Transactional(readOnly = true)
@Service
public class UserService extends BaseService<User, UserDto, UserRepository, UserMapper> {

    private final CommentService commentService;

    public UserService(UserRepository repository, UserMapper mapper, CommentService commentService) {
        super(repository, mapper);
        this.commentService = commentService;
    }

    public Optional<UserDto> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::entityToDto);
    }

    public Map<UserDto, Double> getTradersWithRatings() {
        return repository.findAll()
                .stream()
                .filter(userDto -> TRADER.equals(userDto.getRole()))
                .map(mapper::entityToDto)
                .collect(toMap(Function.identity(),
                        userDto -> getTraderRating(userDto.getId()).orElse(0D)));
    }

    public OptionalDouble getTraderRating(Long userId) {
        return commentService.findAllByUserId(userId)
                .stream()
                .filter(CommentDto::getApproved)
                .mapToInt(CommentDto::getRating)
                .average();
    }
}
