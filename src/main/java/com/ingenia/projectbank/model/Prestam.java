package com.ingenia.projectbank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;

@Entity
@Table(name = "prestam")
public class Prestam {


    @Id
    @Column(name = "id")
    @ApiModelProperty("Clave primaria tipo Long")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ApiModelProperty("Clave tipo tipoInteres tipo Enum")
    @Enumerated(EnumType.STRING)
    private InterestType interestType;


    @Column(name = "cantidad")
    @ApiModelProperty("Clave primaria tipo Long")
    private Double cantidad;


    @ApiModelProperty("meses de duracion")
    @Column(name = "durationMonths")
    private Integer durationMonths;



    @ManyToOne(optional = true)
    @JoinColumn(name = "id_accountPayment")
    //@JsonIgnoreProperties("prestamsPayments")
    private Account accountPayment;


    @ManyToOne(optional = true)
    @JoinColumn(name = "id_accountIncome")
    //@JsonIgnoreProperties("prestamsIncomes")
    private Account accountIncome;

    public Prestam(InterestType interestType, Double cantidad, Integer durationMonths, Account accountPayment, Account accountIncome) {
        this.interestType = interestType;
        this.cantidad = cantidad;
        this.durationMonths = durationMonths;
        this.accountPayment = accountPayment;
        this.accountIncome = accountIncome;
    }

    public Prestam() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InterestType getInterestType() {
        return interestType;
    }

    public void setInterestType(InterestType interestType) {
        this.interestType = interestType;
    }


    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }

    public Account getAccountPayment() {
        return accountPayment;
    }

    public void setAccountPayment(Account accountPayment) {
        this.accountPayment = accountPayment;
    }

    public Account getAccountIncome() {
        return accountIncome;
    }

    public void setAccountIncome(Account accountIncome) {
        this.accountIncome = accountIncome;
    }
}
