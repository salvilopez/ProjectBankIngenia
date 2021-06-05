package com.ingenia.projectbank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "bankcard")
public class BankCard {


    @Id
    @Column(name = "id")
    @ApiModelProperty("Clave primaria tipo Long")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("Clave pan tipo String")
    private String pan;

    @ApiModelProperty("Clave ccv tipo String")
    private String ccv;

    @ApiModelProperty("Clave fecha caducidad tipo Date")
    private Instant expirationDate;

    @ApiModelProperty("Clave cuenta tipo Cuenta")
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_account")
    @JsonIgnoreProperties("cards")
    private Account account;


    @ApiModelProperty("Clave tipo de TARJETA tipo Enum")
    @Enumerated(EnumType.STRING)
    private CardType bankCardType;

    public BankCard() {
    }

    public BankCard(String pan, String ccv, Instant expirationDate,CardType bankCardType) {
        this.pan = pan;
        this.ccv = ccv;
        this.expirationDate = expirationDate;
        this.bankCardType= bankCardType;
    }

    public CardType getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(CardType bankCardType) {
        this.bankCardType = bankCardType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "id=" + id +
                ", pan='" + pan + '\'' +
                ", ccv='" + ccv + '\'' +
                ", expirationDate=" + expirationDate +
                ", account=" + account +
                '}';
    }
}

