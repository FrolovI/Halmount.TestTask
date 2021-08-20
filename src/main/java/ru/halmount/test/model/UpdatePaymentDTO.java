package ru.halmount.test.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdatePaymentDTO {
    public Integer idCreditOffer;
    public LocalDate payDate;
    public BigDecimal sumPay;
    public BigDecimal sumPayCredit;
    public BigDecimal sumPercent;
}
