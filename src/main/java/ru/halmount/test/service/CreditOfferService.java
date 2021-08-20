package ru.halmount.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.halmount.test.entity.CreditOffer;
import ru.halmount.test.entity.Payment;
import ru.halmount.test.model.CreateCreditOfferDTO;
import ru.halmount.test.model.CreateUserCreditOfferDTO;
import ru.halmount.test.model.UpdateCreditDTO;
import ru.halmount.test.model.UpdateCreditOfferDTO;
import ru.halmount.test.repository.ClientRepository;
import ru.halmount.test.repository.CreditOfferRepository;
import ru.halmount.test.repository.CreditRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditOfferService {
    @Autowired
    CreditOfferRepository creditOfferRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CreditRepository creditRepository;

    public List<CreditOffer> getCreditOffer() {
        return creditOfferRepository.findAll();
    }

    public CreditOffer createCreditOffer(CreateCreditOfferDTO requestBody) {
        CreditOffer queueCreditOffer = new CreditOffer();
        queueCreditOffer.sumCredit = requestBody.sumCredit;
        queueCreditOffer.credit = creditRepository.findById(requestBody.idCredit).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        queueCreditOffer.client = clientRepository.findById(requestBody.idClient).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return creditOfferRepository.save(queueCreditOffer);
    }

    public CreditOffer putCreditOffer(Integer idCreditOffer, UpdateCreditOfferDTO requestBody) {
        CreditOffer updateCreditOffer = creditOfferRepository.findById(idCreditOffer).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateCreditOffer.sumCredit = requestBody.sumCredit;
        updateCreditOffer.credit = creditRepository.findById(requestBody.idCredit).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateCreditOffer.client = clientRepository.findById(requestBody.idClient).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return creditOfferRepository.save(updateCreditOffer);
    }

    public void deleteCreditOffer(Integer idCreditOffer) {
        creditOfferRepository.deleteById(idCreditOffer);
    }

    public CreditOffer createCreditOfferForUser(CreateUserCreditOfferDTO requestBody) {
        CreditOffer queueCreditOffer = new CreditOffer();
        queueCreditOffer.credit = creditRepository.findById(requestBody.idCredit).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        queueCreditOffer.client = clientRepository.findById(requestBody.idClient).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (requestBody.sumCredit.compareTo(queueCreditOffer.credit.creditLimit) < 0) {
            BigDecimal percentSum = requestBody.sumCredit.multiply(queueCreditOffer.credit.percentCredit).divide(BigDecimal.valueOf(requestBody.monthCount), RoundingMode.HALF_UP);
            BigDecimal resultSum = requestBody.sumCredit.add(percentSum);
            queueCreditOffer.sumCredit = resultSum;
            queueCreditOffer.payGraph = new ArrayList<Payment>();
            for (int i = 0; i<requestBody.monthCount; i++){
                Payment payment = new Payment();
                payment.sumPay = queueCreditOffer.sumCredit.divide(BigDecimal.valueOf(requestBody.monthCount), RoundingMode.HALF_UP);
                payment.sumPayCredit = requestBody.sumCredit.divide(BigDecimal.valueOf(requestBody.monthCount), RoundingMode.HALF_UP);
                payment.sumPercent = percentSum.divide(BigDecimal.valueOf(requestBody.monthCount), RoundingMode.HALF_UP);
                queueCreditOffer.payGraph.add(payment);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return creditOfferRepository.save(queueCreditOffer);
    }
}
