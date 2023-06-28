package com.saga.choreography.service.impl;

import com.saga.choreography.dto.OrderDTO;
import com.saga.choreography.entity.UserBalanceEntity;
import com.saga.choreography.entity.UserTransactionEntity;
import com.saga.choreography.event.PaymentEvent;
import com.saga.choreography.repository.UserBalanceRepository;
import com.saga.choreography.repository.UserTransactionRepository;
import com.saga.choreography.service.PaymentService;
import com.saga.choreography.util.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final UserBalanceRepository userBalanceRepository;
    private final UserTransactionRepository userTransactionRepository;
    @Override
    @Transactional
    public PaymentEvent newOrderPayment(OrderDTO orderDTO) {
        Optional<UserBalanceEntity> opUb = userBalanceRepository.findByUserId(orderDTO.getUserId());
        if (opUb.isPresent()) {
            UserBalanceEntity userBalanceEntity = opUb.get();
            Long totalBill = orderDTO.getQuantity() * orderDTO.getPrice();
            if (userBalanceEntity.getBalance() >= totalBill) {
                userBalanceEntity.setBalance(userBalanceEntity.getBalance() - totalBill);
                userBalanceRepository.save(userBalanceEntity);

                UserTransactionEntity userTransactionEntity = UserTransactionEntity.builder()
                        .userId(orderDTO.getUserId())
                        .orderId(orderDTO.getUuid())
                        .amount(totalBill)
                        .build();
                userTransactionRepository.save(userTransactionEntity);
                return PaymentEvent.builder()
                        .status(PaymentStatus.RESERVED)
                        .orderId(orderDTO.getUuid())
                        .build();
            } else {
                return PaymentEvent.builder()
                        .status(PaymentStatus.REJECTED)
                        .orderId(orderDTO.getUuid())
                        .build();
            }
        }
        return PaymentEvent.builder().build();
    }

    @Override
    @Transactional
    public void cancelOrderPayment(OrderDTO orderDTO) {
        Optional<UserTransactionEntity> opUt = userTransactionRepository.findByOrderId(orderDTO.getUuid());
        if (opUt.isPresent()) {
            Optional<UserBalanceEntity> opUb = userBalanceRepository.findByUserId(orderDTO.getUserId());
            if (opUb.isPresent()) {
                UserBalanceEntity userBalanceEntity = opUb.get();
                Long totalBill = orderDTO.getQuantity() * orderDTO.getPrice();
                userBalanceEntity.setBalance(userBalanceEntity.getBalance() + totalBill);
                userBalanceRepository.save(userBalanceEntity);
            }

            userTransactionRepository.deleteByOrderId(orderDTO.getUuid());
        }
    }
}
