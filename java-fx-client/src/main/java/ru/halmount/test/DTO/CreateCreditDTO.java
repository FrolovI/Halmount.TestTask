package ru.halmount.test.DTO;

import java.math.BigDecimal;

// Entity for creating new Credits via GUI
public class CreateCreditDTO {
    public Integer idBank;
    public BigDecimal creditLimit;
    public BigDecimal percentCredit;
}
