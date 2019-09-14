package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.GameDto;
import com.leverx.kostusev.dealerstat.entity.Game;
import com.leverx.kostusev.dealerstat.mapper.GameMapper;
import com.leverx.kostusev.dealerstat.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class GameService extends CrudService<Game, GameDto, GameRepository, GameMapper> {

    public GameService(GameRepository repository, GameMapper mapper) {
        super(repository, mapper);
    }
}
