package ru.halmount.test.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

// Entity for updating Payments via GUI
public class UpdatePaymentDTO {
    public Integer idCreditOffer;
    public LocalDate payDate;
    public BigDecimal sumPay;
    public BigDecimal sumPayCredit;
    public BigDecimal sumPercent;
}
