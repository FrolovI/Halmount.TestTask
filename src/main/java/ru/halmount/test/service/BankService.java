package ru.halmount.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.halmount.test.entity.Bank;
import ru.halmount.test.repository.BankRepository;

import java.util.List;

@Service
public class BankService {
    @Autowired
    BankRepository bankRepository;

    public List<Bank> getBanks() {
        return bankRepository.findAll();
    }

    public Bank createBank() {
        Bank queueBank = new Bank();
        return bankRepository.save(queueBank);
    }
    public void deleteBank(Integer idBank){
        bankRepository.deleteById(idBank);
    }
}
