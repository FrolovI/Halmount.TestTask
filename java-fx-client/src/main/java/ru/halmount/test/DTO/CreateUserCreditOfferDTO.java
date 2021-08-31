package ru.halmount.test.DTO;

import java.math.BigDecimal;

// Entity for creating the ability to manually create a loan proposal in the GUI
public class CreateUserCreditOfferDTO {
    public BigDecimal sumCredit;
    public Integer idClient;
    public Integer idCredit;
    public Integer monthCount;
}
