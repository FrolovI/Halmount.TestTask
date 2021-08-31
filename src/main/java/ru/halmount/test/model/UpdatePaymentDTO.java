package ru.halmount.test.model;

import java.math.BigDecimal;
import java.time.LocalDate;

// Entity for updating Payments in the database
public class UpdatePaymentDTO {
    public Integer idCreditOffer;
    public LocalDate payDate;
    public BigDecimal sumPay;
    public BigDecimal sumPayCredit;
    public BigDecimal sumPercent;
}
