package ru.halmount.test.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBank")
    public Integer id;
    @OneToMany
    @JoinColumn(name = "idBank")
    public List<Credit> credits;
    @JoinColumn(name = "idBank")
    @OneToMany
    public List<Client> clients;
}
