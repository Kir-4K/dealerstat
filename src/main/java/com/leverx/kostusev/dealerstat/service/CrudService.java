package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.BaseDto;
import com.leverx.kostusev.dealerstat.entity.BaseEntity;
import com.leverx.kostusev.dealerstat.mapper.BaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class CrudService<E extends BaseEntity, D extends BaseDto, R extends JpaRepository<E, Long>, M extends BaseMapper<E, D>> {

    protected final R repository;
    protected final M mapper;

    public Optional<D> findById(Long id) {
        return repository.findById(id)
                .map(mapper::entityToDto);
    }

    public List<D> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(toList());
    }

    @Transactional
    public E save(D dto) {
        return repository.save(mapper.dtoToEntity(dto));
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
