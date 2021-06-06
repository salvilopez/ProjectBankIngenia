package com.ingenia.projectbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ingenia.projectbank.error.SaldoInsuficienteException;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "id")
    @ApiModelProperty("Clave primaria tipo Long")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("Clave iban tipo String")
    @Column(unique = true)
    private String iban;

    @ApiModelProperty("Clave saldo actual cuenta tipo Double")
    private Double currentBalance;

    @ApiModelProperty("Clave saldo actual tarjeta crédito tipo Double")
    private Double currentCreditCardBalance;

    @ApiModelProperty("Clave saldo global tipo Double")
    private Double currentGlobalBalance;

    @ApiModelProperty("Clave usuario tipo User")
    @ManyToMany(mappedBy="accounts", cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnoreProperties("accounts")
    private List<User> users = new ArrayList<>();


    @ApiModelProperty("Clave movimiento tipo Movement")
    @OneToMany(mappedBy = "account",orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("account")
    private List<Movement> movements = new ArrayList<>();

    @ApiModelProperty("Clave Tarjeta tipo Card")
    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("account")
    private List<BankCard> cards = new ArrayList<>();



    @OneToMany(mappedBy = "accountPayment", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Prestam> prestamsPayments = new ArrayList<>();


    @OneToMany(mappedBy = "accountIncome", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Prestam> prestamsIncomes = new ArrayList<>();

    public Account() {
    }

    public Account(String iban, Double currentBalance, Double currentCreditCardBalance) {
        this.iban = iban;
        this.currentBalance = currentBalance;
        this.currentCreditCardBalance = currentCreditCardBalance;
    }


    public List<Prestam> getPrestamsPayments() {
        return prestamsPayments;
    }

    public void setPrestamsPayments(List<Prestam> prestamsPayments) {
        this.prestamsPayments = prestamsPayments;
    }

    public List<Prestam> getPrestamsIncomes() {
        return prestamsIncomes;
    }

    public void setPrestamsIncomes(List<Prestam> prestamsIncomes) {
        this.prestamsIncomes = prestamsIncomes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

    public List<BankCard> getCards() {
        return cards;
    }

    public void setCards(List<BankCard> cards) {
        this.cards = cards;
    }

    public Double getCurrentCreditCardBalance() {
        return currentCreditCardBalance;
    }

    public void setCurrentCreditCardBalance(Double currentCreditCardBalance) {
        this.currentCreditCardBalance = currentCreditCardBalance;
    }

    public Double getCurrentGlobalBalance() {
        return this.currentGlobalBalance =  this.currentBalance + this.currentCreditCardBalance;
    }

    public void setCurrentGlobalBalance(Double currentGlobalBalance) {
        this.currentGlobalBalance = currentGlobalBalance;
    }

    public void addMovimiento(Movement movement) throws SaldoInsuficienteException {




        if(movement.getOperationType()==OperationType.REST && movement.getPaymentType()== PaymentType.ACCOUNT){
            if(movement.getQuantity()>this.currentCreditCardBalance){
                throw new SaldoInsuficienteException("Saldo Insuficiente ");

            }
            this.currentBalance=this.currentBalance-movement.getQuantity();
            movement.setRemainingBalance(this.currentBalance);
            this.getMovements().add(movement);
        }else if(movement.getOperationType()==OperationType.SUM && movement.getPaymentType()== PaymentType.ACCOUNT){
            this.currentBalance=this.currentBalance+movement.getQuantity();
            movement.setRemainingBalance(this.currentBalance);
            this.getMovements().add(movement);
        }

        if(movement.getOperationType()==OperationType.REST && movement.getPaymentType()== PaymentType.CREDIT){
            this.currentCreditCardBalance=this.currentCreditCardBalance-movement.getQuantity();
            movement.setRemainingCreditBalance(this.currentCreditCardBalance);
            this.getMovements().add(movement);
        }else if(movement.getOperationType()==OperationType.SUM && movement.getPaymentType()== PaymentType.CREDIT){
            this.currentCreditCardBalance=this.currentCreditCardBalance+movement.getQuantity();
            movement.setRemainingCreditBalance(this.currentBalance);
            this.getMovements().add(movement);
        }
        if(movement.getOperationType()==OperationType.REST && movement.getPaymentType()== PaymentType.DEBIT){
            if(movement.getQuantity()>this.currentCreditCardBalance){
                throw new SaldoInsuficienteException("Saldo Insuficiente ");

            }
            this.currentBalance=this.currentBalance-movement.getQuantity();
            movement.setRemainingBalance(this.currentBalance);
            this.getMovements().add(movement);
        }else if(movement.getOperationType()==OperationType.SUM && movement.getPaymentType()== PaymentType.DEBIT){
            this.currentBalance=this.currentBalance+movement.getQuantity();
            movement.setRemainingBalance(this.currentBalance);
            this.getMovements().add(movement);
        }

    }
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", currentBalance=" + currentBalance +
                ", users=" + users +
                ", movements=" + movements +
                ", cards=" + cards +
                '}';
    }
}