package com.ingenia.projectbank.service.ServiceImpl;

import com.ingenia.projectbank.dao.MovementDao;
import com.ingenia.projectbank.model.CategoryType;
import com.ingenia.projectbank.model.Movement;
import com.ingenia.projectbank.model.OperationType;
import com.ingenia.projectbank.model.PaymentType;
import com.ingenia.projectbank.repository.AccountRepository;
import com.ingenia.projectbank.repository.MovementRepository;
import com.ingenia.projectbank.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class MovementServiceImpl  implements MovementService {

    @Autowired
    MovementRepository movementRepository;

    @Autowired
    MovementDao movementDao;

    @Override
    public List<Movement> findAllMovements() {
        return movementRepository.findAll();
    }

    @Override
    public Optional<Movement> findOneMovementById(Long id) {
        if(id!=null)return movementRepository.findById(id);
        return Optional.empty();
    }

    @Override
    public Movement createMovement(Movement movement) {
        movement.getAccount().addMovimiento(movement);
        return movementRepository.save(movement);
    }

    @Override
    public Movement updateMovement(Movement movement) {
        if(movement.getId()!=null)return movementRepository.save(movement);
        return null;
    }

    @Override
    public void deleteOneMovementById(Long id) {
        if(id!=null)movementRepository.deleteById(id);
    }

    @Override
    public void deleteAllMovements() {
        movementRepository.deleteAll();
    }

    @Override
    public List<Movement> findMovementsInterval(LocalDate firstDay, LocalDate lastDay) {
        return movementDao.findMovementsInterval(firstDay,lastDay);
    }

    @Override
    public List<Movement> findMovementsByCategory(String categoryType) {
        switch(categoryType.toUpperCase()) {
            case "RESTAURANTS":
                return movementDao.findMovementsByCategory(CategoryType.RESTAURANTS);
            case "FUEL":
                return movementDao.findMovementsByCategory(CategoryType.FUEL);
            case "CLOTHES":
                return movementDao.findMovementsByCategory(CategoryType.CLOTHES);
            case "UTILITIES":
                return movementDao.findMovementsByCategory(CategoryType.UTILITIES);
            case "PAID":
                return movementDao.findMovementsByCategory(CategoryType.PAID);
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public List<Movement> findMovementsByOperation(String operationType) {
        switch(operationType.toUpperCase()) {
            case "SUM":
                return movementDao.findMovementsByOperation(OperationType.SUM);
            case "REST":
                return movementDao.findMovementsByOperation(OperationType.REST);
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public List<Movement> findMovementsByPayment(String paymentType) {
        switch(paymentType.toUpperCase()) {
            case "CREDIT":
                return movementDao.findMovementsByPayment(PaymentType.CREDIT);
            case "DEBIT":
                return movementDao.findMovementsByPayment(PaymentType.DEBIT);
            case "ACCOUNT":
                return movementDao.findMovementsByPayment(PaymentType.ACCOUNT);
            default:
                return new ArrayList<>();
        }
    }


    @Override
    public List<Movement> findMovementsIntervalByAccountId(Long accountId, LocalDate firstDay, LocalDate lastDay) {
        return movementDao.findMovementsIntervalByAccountId(accountId,firstDay,lastDay);
    }

    @Override
    public List<Movement> findMovementsByCategoryAccountId(Long accountId, String categoryType) {
        switch(categoryType.toUpperCase()) {
            case "RESTAURANTS":
                return movementDao.findMovementsByCategoryAccountId(accountId,CategoryType.RESTAURANTS);
            case "FUEL":
                return movementDao.findMovementsByCategoryAccountId(accountId,CategoryType.FUEL);
            case "CLOTHES":
                return movementDao.findMovementsByCategoryAccountId(accountId,CategoryType.CLOTHES);
            case "UTILITIES":
                return movementDao.findMovementsByCategoryAccountId(accountId,CategoryType.UTILITIES);
            case "PAID":
                return movementDao.findMovementsByCategoryAccountId(accountId,CategoryType.PAID);
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public List<Movement> findMovementsByOperationAccountId(Long accountId, String operationType) {

        switch(operationType.toUpperCase()) {
            case "SUM":
                return movementDao.findMovementsByOperationAccountId(accountId,OperationType.SUM);
            case "REST":
                return movementDao.findMovementsByOperationAccountId(accountId,OperationType.REST);
            default:
                return new ArrayList<>();
        }

    }

    @Override
    public List<Movement> findMovementsByPaymentAccountId(Long accountId, String paymentType) {
        switch(paymentType.toUpperCase()) {
            case "CREDIT":
                return movementDao.findMovementsByPaymentAccountId(accountId,PaymentType.CREDIT);
            case "DEBIT":
                return movementDao.findMovementsByPaymentAccountId(accountId,PaymentType.DEBIT);
            case "ACCOUNT":
                return movementDao.findMovementsByPaymentAccountId(accountId,PaymentType.ACCOUNT);
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public List<Movement> findMovementsAllAccountId(Long accountId) {
        return movementDao.findMovementsAllAccountId(accountId);
    }

    @Override
    public List<Movement> findMovementsByOperationAndCategoryAccountId(Long accountId, String operationType, String categoryType) {
        switch(categoryType.toUpperCase()) {
            case "RESTAURANTS":
                switch(operationType.toUpperCase()) {
                    case "SUM":
                        return movementDao.findMovementsByOperationAndCategoryAccountId(accountId,OperationType.SUM,CategoryType.RESTAURANTS);
                    case "REST":
                        return movementDao.findMovementsByOperationAndCategoryAccountId(accountId,OperationType.REST,CategoryType.RESTAURANTS);
                    default:
                        return new ArrayList<>();
                }
            case "FUEL":

                switch(operationType.toUpperCase()) {
                    case "SUM":
                        return movementDao.findMovementsByOperationAndCategoryAccountId(accountId,OperationType.SUM,CategoryType.FUEL);
                    case "REST":
                        return movementDao.findMovementsByOperationAndCategoryAccountId(accountId,OperationType.REST,CategoryType.FUEL);
                    default:
                        return new ArrayList<>();
                }

            case "CLOTHES":
                switch(operationType.toUpperCase()) {
                    case "SUM":
                        return movementDao.findMovementsByOperationAndCategoryAccountId(accountId,OperationType.SUM,CategoryType.CLOTHES);
                    case "REST":
                        return movementDao.findMovementsByOperationAndCategoryAccountId(accountId,OperationType.REST,CategoryType.CLOTHES);
                    default:
                        return new ArrayList<>();
                }

            case "UTILITIES":
                switch(operationType.toUpperCase()) {
                    case "SUM":
                        return movementDao.findMovementsByOperationAndCategoryAccountId(accountId,OperationType.SUM,CategoryType.UTILITIES);
                    case "REST":
                        return movementDao.findMovementsByOperationAndCategoryAccountId(accountId,OperationType.REST,CategoryType.UTILITIES);
                    default:
                        return new ArrayList<>();
                }
            case "PAID":
                switch(operationType.toUpperCase()) {
                    case "SUM":
                        return movementDao.findMovementsByOperationAndCategoryAccountId(accountId,OperationType.SUM,CategoryType.PAID);
                    case "REST":
                        return movementDao.findMovementsByOperationAndCategoryAccountId(accountId,OperationType.REST,CategoryType.PAID);
                    default:
                        return new ArrayList<>();
                }

            default:
                return new ArrayList<>();
        }

    }

}
