package com.ingenia.projectbank.controller;


import com.ingenia.projectbank.model.Account;
import com.ingenia.projectbank.model.BankCard;
import com.ingenia.projectbank.model.User;
import com.ingenia.projectbank.service.BankCardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class BankCardController {

    private final Logger log = LoggerFactory.getLogger(BankCardController.class);
    private final BankCardService bankCardService;
    public BankCardController(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }

    /**
     * method return all BankCards
     * @return List<BankCard>
     */
    @GetMapping("/bankcards")
    @ApiOperation(value = "encuentra todas las Tarjetas ")
    public List<BankCard> findAllBankCard(){
        log.debug("Rest request all bankCard");
        return bankCardService.findAllBankCards();
    }



    /**
     *method return One bankcards for ID
     * @param id
     * @return
     */
    @GetMapping("/bankcard/{id}")
    @ApiOperation(value = "encuentra una Tarjeta Bancaria por su id")
    public ResponseEntity<BankCard> findOneBankCard(@ApiParam("Clave primaria de la Tarjeta Bancaria") @PathVariable Long id) {
        log.debug("Rest request a Bank Cards with id: "+id);
        Optional<BankCard> bankCardOpt = bankCardService.findOneBankCardById(id);
        if (bankCardOpt.isPresent())
            return ResponseEntity.ok().body(bankCardOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     *method return all bankcards By User
     * @param user
     * @return
     */
    @GetMapping("/bankcard-user")
    @ApiOperation(value = "encuentrar tarjetas bancarias por usuario")
    public ResponseEntity <List<BankCard>> findBankCardsByUser(@ApiParam("Objeto User a consultar")@RequestBody User user) {
        log.debug("Rest request  Bank Cards of user: "+ user);
        List<BankCard> bankCardsOpt = bankCardService.findBankCardsByUser(user);
        if (bankCardsOpt != null) {
            return ResponseEntity.ok().body(bankCardsOpt);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /**
     *method return all bankcards By Account
     * @param account
     * @return
     */
    @GetMapping("/bankcard-account")
    @ApiOperation(value = "encuentrar tarjetas bancarias por cuenta")
    public ResponseEntity <List<BankCard>> findBankCardsByAccount(@ApiParam("Objeto Account a consultar")@RequestBody Account account) {
        log.debug("Rest request  Bank Cards of account: "+ account);
        List<BankCard> bankCardsOpt = bankCardService.findBankCardsByAccount(account);
        if (bankCardsOpt != null) {
            return ResponseEntity.ok().body(bankCardsOpt);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /**
     * create bankCard
     * @param bankCard
     * @return ResponseEntity
     * @throws URISyntaxException
     */
    @PostMapping("/bankcard")
    @ApiOperation(value = "crea una BankCard")
    public ResponseEntity<BankCard> createBankCard(@ApiParam("Objeto BankCard para Crearlo")@RequestBody BankCard bankCard) throws URISyntaxException{
        log.debug("Create BankCard");
        BankCard resultado=null;
        if (bankCard.getId()!=null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resultado=bankCardService.createBankCard(bankCard);
        return  ResponseEntity.created(new URI("/api/bankcard/"+resultado.getId())).body(resultado);
    }

    /**
     * Modificar BankCard
     * Modify BankCard
     * @param bankCard
     * @return
     */
    @PutMapping(value = "/bankcard")
    @ApiOperation(value = "modificar  tarjeta bancaria")
    public ResponseEntity<BankCard> modifyBankCard(@ApiParam("Objeto BankCard a modificar")@RequestBody BankCard bankCard){
        log.debug("Modify bankCard");
        BankCard resultado=null;
        if (bankCard.getId()==null) {
            log.warn("Updating bankCard without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        resultado = bankCardService.updateBankCard(bankCard);
        return ResponseEntity.ok().body(resultado);
    }
    /**
     *method Delete One  BankCard
     * @param id
     * @return no content
     */
    @DeleteMapping(value = "/bankcard/{id}")
    @ApiOperation(value = "Borra una  tarjeta bancaria por id")
    public ResponseEntity<Void> deleteOne(@ApiParam("Clave primaria de la bankCard")@PathVariable("id") Long id) {
        log.debug("Delete bankCard");
        bankCardService.deleteOneBankCardById(id);
        return ResponseEntity.noContent().build();
    }
    /**
     * method Delete all  BankCard
     * @return no content
     */
    @ApiOperation(value = "Borra todas las tarjetas")
    @DeleteMapping(value = "/bankcards")
    public ResponseEntity<Void> deleteAll() {
        log.debug("DeleteAll");
        bankCardService.deleteAllBankCards();
        return  ResponseEntity.noContent().build();
    }
}