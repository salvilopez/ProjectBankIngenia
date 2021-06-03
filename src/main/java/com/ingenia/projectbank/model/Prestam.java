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


    @ManyToOne()
    @ApiModelProperty("Clave cuenta tipo Account")
    @JoinColumn(name = "id_account")
    @JsonIgnoreProperties("prestams")
    private Account account;


    public Prestam(InterestType interestType, Integer cantidad, Integer durationMonths, Account account) {
        this.interestType = interestType;
        this.cantidad = cantidad;
        this.durationMonths = durationMonths;
        this.account = account;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
