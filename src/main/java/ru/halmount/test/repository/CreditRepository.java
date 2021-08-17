package ru.halmount.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.halmount.test.entity.Credit;

@Repository
public interface CreditRepository extends JpaRepository<Credit,Integer> {
}
