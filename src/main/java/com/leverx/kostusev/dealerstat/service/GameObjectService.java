package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.GameObjectDto;
import com.leverx.kostusev.dealerstat.mapper.GameObjectMapper;
import com.leverx.kostusev.dealerstat.repository.GameObjectRepository;
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
public class GameObjectService {

    private final GameObjectRepository gameObjectRepository;
    private final GameObjectMapper gameObjectMapper;

    public Optional<GameObjectDto> findById(Long id) {
        return gameObjectRepository.findById(id)
                .map(gameObjectMapper::entityToDto);
    }

    public List<GameObjectDto> findAll() {
        return gameObjectRepository.findAll()
                .stream()
                .map(gameObjectMapper::entityToDto)
                .collect(toList());
    }

    @Transactional
    public void save(GameObjectDto gameObject) {
        gameObjectRepository.save(gameObjectMapper.dtoToEntity(gameObject));
    }

    @Transactional
    public void delete(GameObjectDto gameObject) {
        gameObjectRepository.delete(gameObjectMapper.dtoToEntity(gameObject));
    }
}
