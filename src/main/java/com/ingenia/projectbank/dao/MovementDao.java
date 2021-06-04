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
import java.util.Optional;

public interface MovementDao {

    List<Movement> findMovementsInterval(LocalDate firstDay, LocalDate lastDay);
    List<Movement> findMovementsByCategory(CategoryType categoryType);
    List<Movement> findMovementsByOperation(OperationType operationType);
    List<Movement> findMovementsByPayment(PaymentType paymentType);

    List<Movement> findMovementsIntervalByAccountId(Long accountId, LocalDate firstDay, LocalDate lastDay);
    List<Movement> findMovementsByCategoryAccountId(Long accountId,CategoryType categoryType);
    List<Movement> findMovementsByOperationAccountId(Long accountId,OperationType operationType);
    List<Movement> findMovementsByOperationAndCategoryAccountId(Long accountId,OperationType operationType,CategoryType categoryType);
    List<Movement> findMovementsByPaymentAccountId(Long accountId,PaymentType paymentType);
    List<Movement> findMovementsAllAccountId(Long accountId);
    List<Movement> findMovementsIntervalAndPaymentByAccountId(Long accountId, LocalDate firstDay, LocalDate lastDay,PaymentType paymentType);
    List<Movement> findMovementsByOperationAndCategoryAccountIdAndDate(Long accountId,OperationType operationType,CategoryType categoryType, LocalDate firstDay, LocalDate lastDay);
    List<Movement> findMovementsByCategoryAccountIdAndPaymentType(Long accountId, String categoryType, String paymentType);
    List<Movement> findMovementsByUserId(Long id);
    List<Movement> findMovementsByUserIdDateAndOperation(Long id, LocalDate startdate, LocalDate finishdate, OperationType operation);
    List<Movement> findMovementsByUserIdDateAndOperation(Long id, LocalDate startdate, LocalDate finishdate, OperationType operation ,CategoryType categoryType);
}
