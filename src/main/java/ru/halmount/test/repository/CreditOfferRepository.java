package ru.halmount.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.halmount.test.entity.CreditOffer;

@Repository
public interface CreditOfferRepository extends JpaRepository<CreditOffer, Integer> {
}
