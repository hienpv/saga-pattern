package com.saga.choreography.repository;

import com.saga.choreography.entity.UserBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalanceEntity, String> {
    Optional<UserBalanceEntity> findByUserId(String userId);
}
