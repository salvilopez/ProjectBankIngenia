package com.ingenia.projectbank.dao.DaoImpl;

import com.ingenia.projectbank.dao.MovementDao;
import com.ingenia.projectbank.model.*;
import com.ingenia.projectbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class MovementDaoImpl implements MovementDao {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Movement> findMovementsInterval(LocalDate firstDay, LocalDate lastDay) {
      if (firstDay != null && lastDay != null) {
          String sql="SELECT m FROM Movement m WHERE m.date BETWEEN '"+firstDay+"' AND '"+lastDay+"'";
          Query query = manager.createQuery(sql);
         return query.getResultList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Movement> findMovementsByCategory(CategoryType categoryType) {
        if(categoryType!=null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Movement> criteria = builder.createQuery(Movement.class);
            Root<Movement> root = criteria.from(Movement.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("categoryType"),categoryType));
            Query query = manager.createQuery(criteria);
            manager.close();
            return query.getResultList();
        }
        return new ArrayList<>();
    }


    @Override
    public List<Movement> findMovementsByOperation(OperationType operationType) {
        if(operationType!=null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Movement> criteria = builder.createQuery(Movement.class);
            Root<Movement> root = criteria.from(Movement.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("operationType"),operationType));
            Query query = manager.createQuery(criteria);
            manager.close();
            return query.getResultList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Movement> findMovementsByPayment(PaymentType paymentType) {
        if(paymentType!=null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Movement> criteria = builder.createQuery(Movement.class);
            Root<Movement> root = criteria.from(Movement.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("paymentType"),paymentType));
            Query query = manager.createQuery(criteria);
            manager.close();
            return query.getResultList();
        }
        return new ArrayList<>();
    }





    @Override
    public List<Movement> findMovementsIntervalByAccountId(Long accountId, LocalDate firstDay, LocalDate lastDay) {
        if (firstDay != null&&lastDay!=null) {
            String sql="SELECT m FROM Movement m JOIN Account a on m.account.id = a.id WHERE  a.id ="+accountId+" AND m.date BETWEEN '"+firstDay+"' AND '"+lastDay+"'";
            Query query = manager.createQuery(sql);
            return query.getResultList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Movement> findMovementsByCategoryAccountId(Long accountId, CategoryType categoryType) {
        if (categoryType != null&&accountId!=null) {
            String sql="SELECT m FROM Movement m JOIN Account a on m.account.id = a.id WHERE a.id ="+accountId;
            Query query = manager.createQuery(sql);
            List<Movement> movementList = query.getResultList();
            List<Movement> movementListF =new ArrayList<>();
            for (int i = 0; i < movementList.size(); i++) {
                if(movementList.get(i).getCategoryType().equals(categoryType)){
                    movementListF.add(movementList.get(i));
                }
            }
            return movementListF;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Movement> findMovementsByOperationAccountId(Long accountId, OperationType operationType) {

        if (operationType != null&&accountId!=null) {
            String sql="SELECT m FROM Movement m JOIN Account a on m.account.id = a.id WHERE a.id ="+accountId;
            Query query = manager.createQuery(sql);
            List<Movement> movementList = query.getResultList();
            List<Movement> movementListF =new ArrayList<>();
            for (int i = 0; i < movementList.size(); i++) {
                if(movementList.get(i).getOperationType().equals(operationType)){
                    movementListF.add(movementList.get(i));
                }
            }
            return movementListF;

        }
        return new ArrayList<>();
    }

    @Override
    public List<Movement> findMovementsByOperationAndCategoryAccountId(Long accountId, OperationType operationType, CategoryType categoryType) {
        if (operationType != null&&accountId!=null&&categoryType!=null) {
            String sql="SELECT m FROM Movement m JOIN Account a on m.account.id = a.id WHERE a.id ="+accountId;
            Query query = manager.createQuery(sql);
            List<Movement> movementList = query.getResultList();
            List<Movement> movementListF =new ArrayList<>();
            for (int i = 0; i < movementList.size(); i++) {
                if(movementList.get(i).getOperationType().equals(operationType)){
                    movementListF.add(movementList.get(i));
                }
            }

            List<Movement> resultado =new ArrayList<>();
            for (int i = 0; i < movementListF.size(); i++) {
                if(movementListF.get(i).getCategoryType().equals(categoryType)){
                    resultado.add(movementListF.get(i));
                }
            }
            return resultado;

        }
        return new ArrayList<>();
    }


    @Override
    public List<Movement> findMovementsByPaymentAccountId(Long accountId, PaymentType paymentType) {

        if (paymentType != null&&accountId!=null) {
            String sql="SELECT m FROM Movement m JOIN Account a on m.account.id = a.id WHERE a.id ="+accountId;
            Query query = manager.createQuery(sql);
            List<Movement> movementList = query.getResultList();
            List<Movement> movementListF =new ArrayList<>();

            for (int i = 0; i < movementList.size(); i++) {
                if(movementList.get(i).getPaymentType().equals(paymentType)){
                    movementListF.add(movementList.get(i));
                }
            }
            return movementListF;

        }
        return new ArrayList<>();
    }

    @Override
    public List<Movement> findMovementsAllAccountId(Long accountId) {
        if (accountId != null) {
            Query query = manager.createQuery("select m FROM Movement m where m.account.id ="+accountId);
            return query.getResultList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Movement> findMovementsIntervalAndPaymentByAccountId(Long accountId, LocalDate firstDay, LocalDate lastDay, PaymentType paymentType) {
        if (firstDay != null&&lastDay!=null&accountId!=null&&paymentType!=null) {
            String sql="SELECT m FROM Movement m JOIN Account a on m.account.id = a.id WHERE  a.id ="+accountId+" AND m.date BETWEEN '"+firstDay+"' AND '"+lastDay+"'";
            Query query = manager.createQuery(sql);
            List<Movement>movements= query.getResultList();
            List<Movement>movementFinal= new ArrayList<>();
            for (int i = 0; i < movements.size(); i++) {
                if(movements.get(i).getPaymentType().equals(paymentType)){
                    movementFinal.add(movements.get(i));
                }
            }
            return movementFinal;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Movement> findMovementsByCategoryAccountIdAndPaymentType(Long accountId, String categoryType, String paymentType) {
        if (paymentType != null&&accountId!=null&&categoryType != null) {
            String sql="SELECT m FROM Movement m JOIN Account a on m.account.id = a.id WHERE a.id ="+accountId;
            Query query = manager.createQuery(sql);
            List<Movement> movementList = query.getResultList();
            List<Movement> movementListF =new ArrayList<>();
            for (int i = 0; i < movementList.size(); i++) {
                if(movementList.get(i).getPaymentType().equals(paymentType) && movementList.get(i).getCategoryType().equals(categoryType)){
                    movementListF.add(movementList.get(i));
                }
            }
            return movementListF;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Movement> findMovementsByOperationAndCategoryAccountIdAndDate(Long accountId, OperationType operationType, CategoryType categoryType, LocalDate firstDay, LocalDate lastDay) {
        if (operationType != null&&accountId!=null&&categoryType!=null&&firstDay != null&&lastDay!=null) {
            String sql="SELECT m FROM Movement m JOIN Account a on m.account.id = a.id WHERE  a.id ="+accountId+" AND m.date BETWEEN '"+firstDay+"' AND '"+lastDay+"'";
            Query query = manager.createQuery(sql);
            List<Movement> movementList = query.getResultList();
            List<Movement> movementListF =new ArrayList<>();
            for (int i = 0; i < movementList.size(); i++) {
                if(movementList.get(i).getOperationType().equals(operationType)){
                    movementListF.add(movementList.get(i));
                }
            }

            List<Movement> resultado =new ArrayList<>();
            for (int i = 0; i < movementListF.size(); i++) {
                if(movementListF.get(i).getCategoryType().equals(categoryType)){
                    resultado.add(movementListF.get(i));
                }
            }
            return resultado;

        }
        return new ArrayList<>();
    }

    @Override
    public List<Movement> findMovementsByUserId(Long id) {
        if(id!=null) {
            User userOpt = manager.find(User.class, id);
            if (userOpt != null) {
                List<Account> accounts = userOpt.getAccounts();
                for (Account account : accounts) {
                    List<Movement> movements = account.getMovements();
                    return movements;
                }
            }
        }
        return null;

    }

    @Override
    public List<Movement> findMovementsByUserIdDateAndOperation(Long id, LocalDate startdate, LocalDate finishdate, OperationType operation) {
        if (id != null&&startdate!=null&&finishdate!=null&&operation != null) {

            List<Movement> allMovementsList = new ArrayList<>();
                 User user = userRepository.findById(id).get();
            for (int i = 0; i < user.getAccounts().size(); i++) {
                String sql = "SELECT m FROM Movement m JOIN User a on m.account.id = a.id WHERE  a.id =" + user.getAccounts().get(i).getId() + " AND m.date BETWEEN '" + startdate + "' AND '" + finishdate + "'";
                Query query = manager.createQuery(sql);
                List<Movement> movements=query.getResultList();
                for (int j = 0; j < movements.size(); j++) {
                    if(movements.get(j).getOperationType().equals(operation)) {
                        allMovementsList.add(movements.get(j));
                    }

                }

            }
            return allMovementsList;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Movement> findMovementsByUserIdDateAndOperation(Long id, LocalDate startdate, LocalDate finishdate, OperationType operation, CategoryType categoryType) {
        if (id != null&&startdate!=null&&finishdate!=null&&operation != null&&categoryType != null) {

            List<Movement> allMovementsList = new ArrayList<>();
            User user = userRepository.findById(id).get();
            for (int i = 0; i < user.getAccounts().size(); i++) {
                String sql = "SELECT m FROM Movement m JOIN User a on m.account.id = a.id WHERE  a.id =" + user.getAccounts().get(i).getId() + " AND m.date BETWEEN '" + startdate + "' AND '" + finishdate + "'";
                Query query = manager.createQuery(sql);
                List<Movement> movements=query.getResultList();
                for (int j = 0; j < movements.size(); j++) {
                    if(movements.get(j).getOperationType().equals(operation)&&movements.get(j).getCategoryType().equals(categoryType)) {
                        allMovementsList.add(movements.get(j));
                    }

                }

            }
            return allMovementsList;
        }
        return new ArrayList<>();
    }
    }











