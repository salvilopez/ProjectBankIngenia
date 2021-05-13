package com.ingenia.projectbank.dao;

import com.ingenia.projectbank.model.CategoryType;
import com.ingenia.projectbank.model.Movement;
import com.ingenia.projectbank.model.OperationType;
import com.ingenia.projectbank.model.PaymentType;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface MovementDao {
    List<Movement> findMovementsInterval(LocalDate firstDay, LocalDate lastDay);
    List<Movement> findMovementsByCategory(CategoryType categoryType);
    List<Movement> findMovementsByOperation(OperationType operationType);
    List<Movement> findMovementsByPayment(PaymentType paymentType);

    List<Movement> findMovementsIntervalByAccountId(Long accountId, LocalDate firstDay, LocalDate lastDay);
    List<Movement> findMovementsByCategoryAccountId(Long accountId,CategoryType categoryType);
    List<Movement> findMovementsByOperationAccountId(Long accountId,OperationType operationType);
    List<Movement> findMovementsByPaymentAccountId(Long accountId,PaymentType paymentType);
    List<Movement> findMovementsAllAccountId(Long accountId);
}
