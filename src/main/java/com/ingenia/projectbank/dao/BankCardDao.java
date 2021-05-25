package com.ingenia.projectbank.dao;

import com.ingenia.projectbank.model.Account;
import com.ingenia.projectbank.model.BankCard;
import com.ingenia.projectbank.model.User;

import java.util.List;

public interface BankCardDao {
    List<BankCard> findBankCardsByUser(User user);

    List<BankCard> findBankCardsByAccount(Account account);
    List<BankCard> findBankCardsByUserId(Long id);
}
