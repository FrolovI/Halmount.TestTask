package ru.halmount.test.entity;

import javax.persistence.Entity;

@Entity
public class Client {
    String fullName;
    String phoneNumber;
    String email;
    String passNumber;
}
