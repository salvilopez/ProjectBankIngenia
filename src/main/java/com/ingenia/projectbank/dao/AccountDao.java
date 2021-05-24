package com.ingenia.projectbank.dao;

import com.ingenia.projectbank.model.Account;
import com.ingenia.projectbank.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountDao {

    Double getCurrentBalanceByAccountId(Long id);

    List<Account> findAccountsByUser(User user);

    Double getAccountBalanceByTypeAndUser(User user, String balanceType);

    ResponseEntity<Void> deleteAccountById(Long id);

    void deleteAllAccounts();
}
