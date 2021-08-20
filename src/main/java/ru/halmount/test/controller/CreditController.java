package ru.halmount.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.halmount.test.entity.Credit;
import ru.halmount.test.model.CreateCreditDTO;
import ru.halmount.test.model.UpdateCreditDTO;
import ru.halmount.test.service.CreditService;

import java.util.List;

@RestController
public class CreditController {
    @Autowired
    CreditService creditService;

    @GetMapping("/credits")
    public List<Credit> getCredits() {
        return creditService.getCredits();
    }

    @PostMapping("/credits")
    public Credit createCredit(@RequestBody CreateCreditDTO requestBody) {
        return creditService.createCredit(requestBody);
    }

    @PutMapping("/credits/{idCredit}")
    public Credit putCredit(@PathVariable(name = "idCredit") Integer idCredit, @RequestBody UpdateCreditDTO requestBody) {
        return creditService.putCredit(idCredit, requestBody);
    }

    @DeleteMapping("/credits/{idCredit}")
    public void deleteCredit(@PathVariable(name = "idCredit") Integer idCredit) {
        creditService.deleteCredit(idCredit);
    }
}
