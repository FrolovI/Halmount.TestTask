package ru.halmount.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.halmount.test.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
