package ru.halmount.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.halmount.test.entity.Bank;
import ru.halmount.test.service.BankService;

import java.util.List;

@RestController
public class BankController {
    @Autowired
    BankService bankService;

    @GetMapping("/banks")
    public List<Bank> getBanks() {
        return bankService.getBanks();
    }
    @PostMapping("/banks")
    public Bank createBank(){
        return bankService.createBank();
    }
    @DeleteMapping("/banks/{idBank}")
    public void deleteBank(@PathVariable(name = "idBank") Integer idBank){
        bankService.deleteBank(idBank);
    }
}
