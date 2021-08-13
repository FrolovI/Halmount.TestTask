package ru.halmount.test.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Payment {
    LocalDate payDate;
    BigDecimal sumPay;
    BigDecimal sumPayCredit;
    BigDecimal sumPercent;
}
