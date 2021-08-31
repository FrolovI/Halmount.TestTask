package ru.halmount.test.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

// Entity for creating new Payments via GUI
public class CreatePaymentDTO {
    public Integer idCreditOffer;
    public LocalDate payDate;
    public BigDecimal sumPay;
    public BigDecimal sumPayCredit;
    public BigDecimal sumPercent;
}
