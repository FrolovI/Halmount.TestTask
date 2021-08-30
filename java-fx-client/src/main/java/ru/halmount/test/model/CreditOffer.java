package ru.halmount.test.model;

import java.math.BigDecimal;
import java.util.List;

public class CreditOffer {
    public Integer id;
    public Client client;
    public Credit credit;
    public BigDecimal sumCredit;
    public List<Payment> payGraph;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public BigDecimal getSumCredit() {
        return sumCredit;
    }

    public void setSumCredit(BigDecimal sumCredit) {
        this.sumCredit = sumCredit;
    }

    public List<Payment> getPayGraph() {
        return payGraph;
    }

    public void setPayGraph(List<Payment> payGraph) {
        this.payGraph = payGraph;
    }
}
