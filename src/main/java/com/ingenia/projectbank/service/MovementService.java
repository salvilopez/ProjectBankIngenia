package com.ingenia.projectbank.service;

import com.ingenia.projectbank.error.SaldoInsuficienteException;
import com.ingenia.projectbank.model.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovementService {

    List<Movement> findAllMovements();

    Optional<Movement> findOneMovementById(Long id);

    Movement createMovement(Movement movement) throws SaldoInsuficienteException;

    Movement updateMovement(Movement movement);

    void deleteOneMovementById(Long id);

    void deleteAllMovements();
    List<Movement> findMovementsInterval(LocalDate firstDay, LocalDate lastDay);
    List<Movement> findMovementsByCategory(String categoryType);
    List<Movement> findMovementsByOperation(String operationType);
    List<Movement> findMovementsByPayment(String paymentType);

    List<Movement> findMovementsIntervalByAccountId(Long accountId, LocalDate firstDay, LocalDate lastDay);
    List<Movement> findMovementsIntervalAndPaymentByAccountId(Long accountId, LocalDate firstDay, LocalDate lastDay,String paymentType);
    List<Movement> findMovementsByCategoryAccountId(Long accountId,String categoryType);
    List<Movement> findMovementsByOperationAccountId(Long accountId,String operationType);
    List<Movement> findMovementsByPaymentAccountId(Long accountId,String paymentType);
    List<Movement> findMovementsAllAccountId(Long accountId);
    List<Movement> findMovementsByOperationAndCategoryAccountId(Long accountId,String operationType,String categoryType);
    List<Movement> findMovementsByOperationAndCategoryAccountIdAndDate(Long accountId,String operationType,String categoryType, LocalDate firstDay, LocalDate lastDay);
    List<Movement>  findMovementsByUserId(Long id);

    List<Movement> findMovementsByUserIdDateAndOperation(Long id, LocalDate startdate, LocalDate finishdate, String operation);
    List<Movement> findMovementsByUserIdDateAndOperation(Long id, LocalDate startdate, LocalDate finishdate, String operation, String categoryType);
}
