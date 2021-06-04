package com.ingenia.projectbank.service;

import com.ingenia.projectbank.error.SaldoInsuficienteException;
import com.ingenia.projectbank.model.Account;
import com.ingenia.projectbank.model.BankCard;
import com.ingenia.projectbank.model.Prestam;
import com.ingenia.projectbank.model.User;

import java.util.List;
import java.util.Optional;

public interface PrestamService {

    List<Prestam> findAllPrestams();
    Optional<Prestam> findOnePrestamById(Long id);
    Prestam createPrestam(Prestam prestam);
    Prestam updatePrestam(Prestam prestam);
    void deleteOnePrestamById(Long id);
    void deleteAllPrestams();
    List<Prestam> findPrestamsByUserId(Long id);

    Boolean colletPrestam(String iban,Double cantidad) throws SaldoInsuficienteException;
}
