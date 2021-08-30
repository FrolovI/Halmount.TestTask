package ru.halmount.test.model;

import java.math.BigDecimal;

public class Credit {
    public Integer id;
    public Integer idBank;
    public BigDecimal creditLimit;
    public BigDecimal percentCredit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdBank() {
        return idBank;
    }

    public void setIdBank(Integer idBank) {
        this.idBank = idBank;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getPercentCredit() {
        return percentCredit;
    }

    public void setPercentCredit(BigDecimal percentCredit) {
        this.percentCredit = percentCredit;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

