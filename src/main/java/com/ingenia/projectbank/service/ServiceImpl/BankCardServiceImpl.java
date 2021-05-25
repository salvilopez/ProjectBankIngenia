package com.ingenia.projectbank.service.ServiceImpl;

import com.ingenia.projectbank.dao.BankCardDao;
import com.ingenia.projectbank.model.Account;
import com.ingenia.projectbank.model.BankCard;
import com.ingenia.projectbank.model.User;
import com.ingenia.projectbank.repository.BankCardRepository;
import com.ingenia.projectbank.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BankCardServiceImpl implements BankCardService {

    @Autowired
    BankCardRepository repository;

    @Autowired
    BankCardDao bankCardDao;

    @Override
    public List<BankCard> findAllBankCards() {
        return repository.findAll();
    }

    @Override
    public Optional<BankCard> findOneBankCardById(Long id) {
        if(id!=null) return repository.findById(id);
        return null;
    }

    @Override
    public BankCard createBankCard(BankCard bankCard) {
        return repository.save(bankCard);
    }

    @Override
    public BankCard updateBankCard(BankCard bankCard) {
        if(bankCard.getId()!=null)return repository.save(bankCard);
        return null;
    }

    @Override
    public void deleteOneBankCardById(Long id) {
        if(id!=null)repository.deleteById(id);
    }

    @Override
    public void deleteAllBankCards() {
        repository.deleteAll();
    }

    @Override
    public List<BankCard> findBankCardsByUser(User user) {
        return bankCardDao.findBankCardsByUser(user);
    }

    @Override
    public List<BankCard> findBankCardsByUserId(Long id) {
        return bankCardDao.findBankCardsByUserId(id);
    }

    @Override
    public List<BankCard> findBankCardsByAccount(Account account) {
        return bankCardDao.findBankCardsByAccount(account);
    }
}
