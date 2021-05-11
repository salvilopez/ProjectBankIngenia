package com.ingenia.projectbank.service;

import com.ingenia.projectbank.model.Account;
import com.ingenia.projectbank.model.Movement;

import java.util.List;
import java.util.Optional;

public interface MovementService {

    List<Movement> findAllMovements();

    Optional<Movement> findOneMovementById(Long id);

    Movement createAccount(Movement movement);

    Movement updateAccount(Movement movement);

    void deleteOneMovementById(Long id);

    void deleteAllMovements();
}