package ru.halmount.test.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCredit")
    public Integer id;
    public Integer idBank;
    public BigDecimal creditLimit;
    public BigDecimal percentCredit;
}
