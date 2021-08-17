package ru.halmount.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.halmount.test.entity.Credit;
import ru.halmount.test.model.CreateCreditDTO;
import ru.halmount.test.model.UpdateCreditDTO;
import ru.halmount.test.repository.ClientRepository;
import ru.halmount.test.repository.CreditRepository;

import java.util.List;

@Service
public class CreditService {
    @Autowired
    CreditRepository creditRepository;

    public List<Credit> getCredits() {
        return creditRepository.findAll();
    }

    public Credit createCredit(CreateCreditDTO requestBody) {
        Credit queueCredit = new Credit();
        queueCredit.creditLimit = requestBody.creditLimit;
        queueCredit.idBank = requestBody.idBank;
        queueCredit.percentCredit = requestBody.percentCredit;
        return creditRepository.save(queueCredit);
    }

    public Credit putCredit(Integer idCredit, UpdateCreditDTO requestBody) {
        Credit updateCredit = creditRepository.findById(idCredit).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateCredit.percentCredit = requestBody.percentCredit;
        updateCredit.creditLimit = requestBody.creditLimit;
        updateCredit.idBank = requestBody.idBank;
        return creditRepository.save(updateCredit);
    }

    public void deleteCredit(Integer idCredit) {
        creditRepository.deleteById(idCredit);
    }
}
