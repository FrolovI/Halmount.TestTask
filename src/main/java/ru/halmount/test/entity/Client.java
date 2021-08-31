package ru.halmount.test.entity;

import javax.persistence.*;

// Clients entity
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClient")
    public Integer id;
    public Integer idBank;
    public String fullName;
    public String phoneNumber;
    public String email;
    public String passNumber;
}
