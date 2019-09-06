package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.GameDto;
import com.leverx.kostusev.dealerstat.entity.Game;
import com.leverx.kostusev.dealerstat.mapper.GameMapper;
import com.leverx.kostusev.dealerstat.repository.GameRepository;
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
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public Optional<GameDto> findById(Long id) {
        return gameRepository.findById(id)
                .map(gameMapper::entityToDto);
    }

    public List<GameDto> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::entityToDto)
                .collect(toList());
    }

    @Transactional
    public Game save(GameDto game) {
        return gameRepository.save(gameMapper.dtoToEntity(game));
    }

    @Transactional
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }
}
