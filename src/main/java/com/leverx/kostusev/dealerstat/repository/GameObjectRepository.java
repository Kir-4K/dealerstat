package com.leverx.kostusev.dealerstat.repository;

import com.leverx.kostusev.dealerstat.entity.GameObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameObjectRepository extends JpaRepository<GameObject, Long> {
}
