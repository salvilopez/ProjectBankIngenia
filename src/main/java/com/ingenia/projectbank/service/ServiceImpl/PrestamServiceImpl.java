package com.ingenia.projectbank.service.ServiceImpl;

import com.ingenia.projectbank.dao.BankCardDao;
import com.ingenia.projectbank.dao.PrestamDao;
import com.ingenia.projectbank.error.SaldoInsuficienteException;
import com.ingenia.projectbank.model.*;
import com.ingenia.projectbank.repository.BankCardRepository;
import com.ingenia.projectbank.repository.PrestamRepository;
import com.ingenia.projectbank.service.AccountService;
import com.ingenia.projectbank.service.PrestamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class PrestamServiceImpl implements PrestamService {

    @Autowired
    PrestamRepository repository;

    @Autowired
    PrestamDao prestamDao;

    @Autowired
    AccountService accountService;


    @Override
    public List<Prestam> findAllPrestams() {
        return repository.findAll();
    }

    @Override
    public Optional<Prestam> findOnePrestamById(Long id) {
        if(id!=null) return repository.findById(id);
        return null;
    }

    @Override
    public Prestam createPrestam(Prestam prestam) {
        return repository.save(prestam);
    }

    @Override
    public Prestam updatePrestam(Prestam prestam) {
        return repository.save(prestam);
    }

    @Override
    public void deleteOnePrestamById(Long id) {
        if(id!=null){
            repository.deleteById(id);
        }
    }

    @Override
    public void deleteAllPrestams() {
        repository.deleteAll();
    }

    @Override
    public List<Prestam> findPrestamsByUserId(Long id) {
        return prestamDao.findPrestamsByUserId(id);
    }

    @Override
    public Boolean colletPrestam(String iban, Double cantidad) throws SaldoInsuficienteException {
        if(iban!=null&&cantidad!=null){
            Account account=accountService.findAccountByIban(iban).get();
            if (account != null) {
                if(cantidad<account.getCurrentBalance()){
                Movement movement = new Movement(OperationType.REST, PaymentType.ACCOUNT, Instant.now(),LocalDate.now(),cantidad,account,CategoryType.PAID);
                        account.addMovimiento(movement);
                        accountService.updateAccount(account);
                        return true;
                }
            }
        }
        return false;
    }
}
