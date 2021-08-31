package ru.halmount.test.model;

import java.math.BigDecimal;

// Entity for creating new Credits in the database
public class CreateCreditDTO {
    public Integer idBank;
    public BigDecimal creditLimit;
    public BigDecimal percentCredit;
}
