package ru.halmount.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.halmount.test.entity.CreditOffer;
import ru.halmount.test.model.CreateCreditOfferDTO;
import ru.halmount.test.repository.ClientRepository;
import ru.halmount.test.repository.CreditOfferRepository;
import ru.halmount.test.repository.CreditRepository;

import java.util.List;

@Service
public class CreditOfferService {
    @Autowired
    CreditOfferRepository creditOfferRepository;
    ClientRepository clientRepository;
    CreditRepository creditRepository;

    public List<CreditOffer> getCreditOffer() {
        return creditOfferRepository.findAll();
    }
    //public  CreditOffer createCreditOffer(CreateCreditOfferDTO requestBody){
    //    CreditOffer queueCreditOffer = new CreditOffer();

    //}
}
