package ru.halmount.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.halmount.test.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
}
