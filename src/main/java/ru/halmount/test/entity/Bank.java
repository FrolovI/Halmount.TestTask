package ru.halmount.test.entity;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Bank {
    List<Credit> credits;
    List<Client> clients;
}
