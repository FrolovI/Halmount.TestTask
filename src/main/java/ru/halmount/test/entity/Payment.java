package ru.halmount.test.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPayment")
    public Integer id;
    @Column(insertable = false, updatable = false)
    public Integer idCreditOffer;
    public LocalDate payDate;
    public BigDecimal sumPay; // Общая платежка
    public BigDecimal sumPayCredit; // Платежка за тело кредита
    public BigDecimal sumPercent; // платежка за процент
}
