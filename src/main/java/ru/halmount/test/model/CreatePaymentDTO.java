package ru.halmount.test.model;

import java.math.BigDecimal;
import java.time.LocalDate;

// Entity for creating new Payments in the database
public class CreatePaymentDTO {
    public Integer idCreditOffer;
    public LocalDate payDate;
    public BigDecimal sumPay;
    public BigDecimal sumPayCredit;
    public BigDecimal sumPercent;
}
