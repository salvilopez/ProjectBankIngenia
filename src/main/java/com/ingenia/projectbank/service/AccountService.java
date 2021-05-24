package com.ingenia.projectbank.service;

import com.ingenia.projectbank.model.Account;
import com.ingenia.projectbank.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> findAllAccounts();

    Account createAccount(Account account);

    Account updateAccount(Account account);

    ResponseEntity<Void> deleteOneAccountById(Long id);

    void deleteAllAccounts();

    Double getAccountBalanceByTypeAndUser(User user, String balanceType);

    Double getCurrentBalanceByAccountId(Long id);

    List<Account> findAccountsByUser(User user);

   Optional<Account> findAccountById(Long id);

    Optional<Account> findAccountByIban(String iban);
}
