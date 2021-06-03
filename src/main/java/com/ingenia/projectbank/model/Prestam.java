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
    private Integer cantidad;


    @ApiModelProperty("meses de duracion")
    @Column(name = "duration_months")
    private Integer durationMonths;

    @OneToOne
    @JoinColumn(name = "FK_PAYMENT_ACCOUNT", updatable = false, nullable = false)
    private Account paymentAccount;
    @OneToOne
    @JoinColumn(name = "FK_INCOME_ACCOUNT", updatable = false, nullable = false)
    private Account incomeAccount;

    public Prestam(InterestType interestType, Integer cantidad, Integer durationMonths, Account paymentAccount, Account incomeAccount) {
        this.interestType = interestType;
        this.cantidad = cantidad;
        this.durationMonths = durationMonths;
        this.paymentAccount = paymentAccount;
        this.incomeAccount = incomeAccount;
    }

    public Account getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(Account paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public Account getIncomeAccount() {
        return incomeAccount;
    }

    public void setIncomeAccount(Account incomeAccount) {
        this.incomeAccount = incomeAccount;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }


}
