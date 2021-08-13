package ru.halmount.test.entity;

import jdk.dynalink.linker.LinkerServices;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class CreditOffer {
    Client client;
    Credit credit;
    BigDecimal sumCredit;
    List<Payment> payGraph;
}
