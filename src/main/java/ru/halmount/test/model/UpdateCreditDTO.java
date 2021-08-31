package ru.halmount.test.model;

import java.math.BigDecimal;

// Entity for updating Credits in the database
public class UpdateCreditDTO {
    public Integer idBank;
    public BigDecimal creditLimit;
    public BigDecimal percentCredit;
}
