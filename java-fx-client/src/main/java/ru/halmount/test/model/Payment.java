package ru.halmount.test.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Payment {
    public Integer id;
    public Integer idCreditOffer;
    public LocalDate payDate;
    public BigDecimal sumPay; // Общая платежка
    public BigDecimal sumPayCredit; // Платежка за тело кредита
    public BigDecimal sumPercent; // платежка за процент

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCreditOffer() {
        return idCreditOffer;
    }

    public void setIdCreditOffer(Integer idCreditOffer) {
        this.idCreditOffer = idCreditOffer;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getSumPay() {
        return sumPay;
    }

    public void setSumPay(BigDecimal sumPay) {
        this.sumPay = sumPay;
    }

    public BigDecimal getSumPayCredit() {
        return sumPayCredit;
    }

    public void setSumPayCredit(BigDecimal sumPayCredit) {
        this.sumPayCredit = sumPayCredit;
    }

    public BigDecimal getSumPercent() {
        return sumPercent;
    }

    public void setSumPercent(BigDecimal sumPercent) {
        this.sumPercent = sumPercent;
    }
}
