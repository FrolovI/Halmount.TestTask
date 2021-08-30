package ru.halmount.test.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class CreditOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCreditOffer")
    public Integer id;
    @ManyToOne
    @JoinColumn(name = "idClient")
    public Client client;
    @ManyToOne
    @JoinColumn(name = "idCredit")
    public Credit credit;
    public BigDecimal sumCredit;
    @OneToMany (cascade = {CascadeType.ALL})
    @JoinColumn(name = "idCreditOffer")
    public List<Payment> payGraph;
}
