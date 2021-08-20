package ru.halmount.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.halmount.test.entity.CreditOffer;
import ru.halmount.test.model.CreateCreditOfferDTO;
import ru.halmount.test.model.CreateUserCreditOfferDTO;
import ru.halmount.test.model.UpdateCreditOfferDTO;
import ru.halmount.test.service.CreditOfferService;

import java.util.List;

@RestController
public class CreditOfferController {
    @Autowired
    CreditOfferService creditOfferService;

    @GetMapping("/creditOffers")
    public List<CreditOffer> getCreditOffer() {
        return creditOfferService.getCreditOffer();
    }

    @PostMapping("/creditOffers")
    public CreditOffer createCreditOffer(@RequestBody CreateCreditOfferDTO requestBody) {
        return creditOfferService.createCreditOffer(requestBody);
    }

    @PutMapping("/creditOffers/{idCreditOffer}")
    public CreditOffer putCreditOffer(@PathVariable(name = "idCreditOffer") Integer idCreditOffer, @RequestBody UpdateCreditOfferDTO requestBody) {
        return creditOfferService.putCreditOffer(idCreditOffer, requestBody);
    }
    @DeleteMapping("/creditOffers/{idCreditOffer}")
    public void deleteCreditOffer(@PathVariable(name = "idCreditOffer") Integer idCreditOffer){

        creditOfferService.deleteCreditOffer(idCreditOffer);
    }
    @PostMapping("/users/creditOffers")
    public CreditOffer createCreditOfferForUser(@RequestBody CreateUserCreditOfferDTO requestBody){
        return creditOfferService.createCreditOfferForUser(requestBody);
    }
}
