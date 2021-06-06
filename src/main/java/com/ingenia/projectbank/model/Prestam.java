package com.ingenia.projectbank.model;



import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "prestam")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
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



    @ManyToOne()
    @JoinColumn(name = "id_accountPayment")
    private Account accountPayment;


    @ManyToOne()
    @JoinColumn(name = "id_accountIncome")
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
