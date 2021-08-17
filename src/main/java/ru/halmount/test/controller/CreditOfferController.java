package ru.halmount.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.halmount.test.entity.CreditOffer;
import ru.halmount.test.service.CreditOfferService;

import java.util.List;

@RestController
public class CreditOfferController {
    @Autowired
    CreditOfferService creditOfferService;

    @GetMapping("/CreditOffers")
    public List<CreditOffer> getCreditOffer() {
        return creditOfferService.getCreditOffer();
    }

}
