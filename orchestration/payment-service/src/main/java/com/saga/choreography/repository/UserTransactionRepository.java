package com.saga.choreography.repository;

import com.saga.choreography.entity.UserTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransactionEntity, String> {
    Optional<UserTransactionEntity> findByOrderId(String orderId);

    int deleteByOrderId(String orderId);
}
