package ru.halmount.test.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Credit {
    BigDecimal creditLimit;
    BigDecimal percentCredit;
}
