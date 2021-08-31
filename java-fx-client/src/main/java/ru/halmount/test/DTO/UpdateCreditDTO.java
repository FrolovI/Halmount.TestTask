package ru.halmount.test.DTO;

import java.math.BigDecimal;

// Entity for updating Credits via GUI
public class UpdateCreditDTO {
    public Integer idBank;
    public BigDecimal creditLimit;
    public BigDecimal percentCredit;
}
