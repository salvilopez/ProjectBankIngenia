package com.ingenia.projectbank.controller;


import com.ingenia.projectbank.model.Account;
import com.ingenia.projectbank.model.BankCard;
import com.ingenia.projectbank.model.User;
import com.ingenia.projectbank.service.AccountService;
import com.ingenia.projectbank.service.BankCardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * method return all accounts
     * @return List<Account>
     */
    @GetMapping("/accounts")
    @ApiOperation(value = "encuentra todas las Cuentas ")
    public List<Account> findAllAccounts() {
        log.debug("Rest request all accounts");
        return accountService.findAllAccounts();
    }


    /**
     * method return One account for ID
     *
     * @param id
     * @return
     */
    @GetMapping("/account/{id}")
    @ApiOperation(value = "encuentra una Cuenta Bancaria por su id")
    public ResponseEntity<Account> findOneAccount(@ApiParam("Clave primaria de la Cuenta Bancaria") @PathVariable Long id) {
        log.debug("Rest request an Account with id: " + id);
        Optional<Account> accountOpt = accountService.findAccountById(id);
        if (accountOpt != null)
            return ResponseEntity.ok().body(accountOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/accountbyiban/{iban}")
    @ApiOperation(value = "encuentra una Cuenta Bancaria por su iban")
    public ResponseEntity<Account> findOneAccount(@ApiParam("Clave primaria de la Cuenta Bancaria") @PathVariable String iban) {
        log.debug("Rest request an Account with iban: " + iban);
        Optional<Account> accountOpt = accountService.findAccountByIban(iban);
        if (accountOpt.isPresent())
            return ResponseEntity.ok().body(accountOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * create account
     *
     * @param account
     * @return ResponseEntity
     * @throws URISyntaxException
     */
    @PostMapping("/account")
    @ApiOperation(value = "crea una Account")
    public ResponseEntity<Account> createAccount(@ApiParam("Objeto Account para Crearlo") @RequestBody Account account) throws URISyntaxException {
        log.debug("Create Account");
        Account resultado = null;
        if (account.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        resultado = accountService.createAccount(account);
        if(resultado==null)return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.created(new URI("/api/account/" + resultado.getId())).body(resultado);
    }


    /**
     * Modificar Account
     * Modify Account
     *
     * @param account
     * @return
     */
    @PutMapping(value = "/account")
    @ApiOperation(value = "modificar  cuenta bancaria")
    public ResponseEntity<Account> modifyAccount(@ApiParam("Objeto account a modificar") @RequestBody Account account) {
        log.debug("Modify account");
        Account resultado = null;
        if (account.getId() == null) {
            log.warn("Updating account without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        resultado = accountService.updateAccount(account);
        if(resultado==null)return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok().body(resultado);
    }

    /**
     * method Delete One  Account
     *
     * @param id
     * @return no content
     */

    @DeleteMapping(value = "/account/{id}")
    @ApiOperation(value = "Borra una  cuenta bancaria por id")
    @Transactional
    public ResponseEntity<Void> deleteOne(@ApiParam("Clave primaria de la account") @PathVariable("id") Long id) {
        log.debug("Delete account");
        accountService.deleteOneAccountById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * method Delete all  Accounts
     *
     * @return no content
     */
    @ApiOperation(value = "Borra todas las cuentas")
    @DeleteMapping(value = "/accounts")
    @Transactional
    public ResponseEntity<Void> deleteAllAccounts() {
        log.debug("DeleteAll");
        accountService.deleteAllAccounts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     * method to get available Balance in credit/account/debit/global by user
     * @param balanceType that can be card/account/global
     * @return
     */
    @GetMapping(value = "/account-balancetype-{balanceType}")
    @ApiOperation(value = "encuentra el balance según parámetro balanceType credit/account/debit/global según usuario")
    public ResponseEntity<Double> getAccountBalanceByTypeAndUser(@ApiParam("Objeto User del que queremos consultar balance")@RequestBody User user, @ApiParam("Clave balanceType que queremos consultar")@PathVariable("balanceType") String balanceType){
        log.debug("getAccountBalance");
        Double accountOpt = accountService.getAccountBalanceByTypeAndUser(user,balanceType);
        if (accountOpt != null)
            return ResponseEntity.ok().body(accountOpt);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

}

    /**
     * method return currentBalance for ID account
     *
     * @param id
     * @return
     */
    @GetMapping("/account-balance/{id}")
    @ApiOperation(value = "retorna saldo de una Cuenta Bancaria por su id")
    public Double getCurrentBalanceByAccountId(@ApiParam("Clave primaria de la Cuenta Bancaria") @PathVariable Long id) {
        log.debug("Rest request current salary in Account with id: " + id);
        Optional<Account> accountOpt = accountService.findAccountById(id);

        if (accountOpt != null) {
            return accountService.getCurrentBalanceByAccountId(id);
        } else {
            throw new EntityNotFoundException("Can't find Account by ID "
                    + id);
        }
    }

    /**
     * method return account by user
     *
     * @param user
     *
     * @return
     */
    @GetMapping("/account-user")
    @ApiOperation(value = "retorna  una/s Cuenta Bancaria por usuario")
    public ResponseEntity<List<Account>> getAccountByUser(@ApiParam("Objeto user a consultar") @RequestBody User user) {
        log.debug("Rest request current Account by user"+user);
        List<Account> accountsOpt = accountService.findAccountsByUser(user);
        if (accountsOpt != null) {
            return ResponseEntity.ok().body(accountsOpt);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}