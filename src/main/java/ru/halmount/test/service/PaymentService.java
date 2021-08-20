package ru.halmount.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.halmount.test.entity.Credit;
import ru.halmount.test.entity.Payment;
import ru.halmount.test.model.CreatePaymentDTO;
import ru.halmount.test.model.UpdatePaymentDTO;
import ru.halmount.test.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public List<Payment> getPayment() {
        return paymentRepository.findAll();
    }

    public Payment createPayment(CreatePaymentDTO requestBody) {
        Payment queuePayment = new Payment();
        queuePayment.sumPay = requestBody.sumPay;
        queuePayment.payDate = requestBody.payDate;
        queuePayment.sumPayCredit = requestBody.sumPayCredit;
        queuePayment.idCreditOffer = requestBody.idCreditOffer;
        queuePayment.sumPercent = requestBody.sumPercent;
        return paymentRepository.save(queuePayment);
    }

    public Payment putPayment(Integer idPayment, UpdatePaymentDTO requestBody) {
        Payment updatePayment = paymentRepository.findById(idPayment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updatePayment.sumPercent = requestBody.sumPercent;
        updatePayment.idCreditOffer = requestBody.idCreditOffer;
        updatePayment.sumPay = requestBody.sumPay;
        updatePayment.payDate = requestBody.payDate;
        updatePayment.sumPayCredit = requestBody.sumPayCredit;
        return paymentRepository.save(updatePayment);
    }

    public void deletePayment(Integer idPayment) {
        paymentRepository.deleteById(idPayment);
    }
}
