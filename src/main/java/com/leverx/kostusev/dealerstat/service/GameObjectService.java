package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.GameObjectDto;
import com.leverx.kostusev.dealerstat.entity.GameObject;
import com.leverx.kostusev.dealerstat.mapper.GameObjectMapper;
import com.leverx.kostusev.dealerstat.repository.GameObjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@Service
public class GameObjectService extends CrudService<GameObject, GameObjectDto, GameObjectRepository, GameObjectMapper> {

    public GameObjectService(GameObjectRepository repository, GameObjectMapper mapper) {
        super(repository, mapper);
    }

    public List<GameObjectDto> findAllByUserId(Long id) {
        return repository.findAllByUser_Id(id)
                .stream()
                .map(mapper::entityToDto)
                .collect(toList());
    }
}
