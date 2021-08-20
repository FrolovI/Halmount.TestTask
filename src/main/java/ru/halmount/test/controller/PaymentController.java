package ru.halmount.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.halmount.test.entity.Payment;
import ru.halmount.test.model.CreatePaymentDTO;
import ru.halmount.test.model.UpdatePaymentDTO;
import ru.halmount.test.service.PaymentService;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping("/payments")
    public List<Payment> getPayment() {
        return paymentService.getPayment();
    }

    @PostMapping("/payments")
    public Payment createPayment(@RequestBody CreatePaymentDTO requestBody) {
        return paymentService.createPayment(requestBody);
    }

    @PutMapping("/payments/{idPayment}")
    public Payment putPayment(@PathVariable(name = "idPayment") Integer idPayment, @RequestBody UpdatePaymentDTO requestBody) {
        return paymentService.putPayment(idPayment, requestBody);
    }

    @DeleteMapping("/payments/{idPayment}")
    public void deletePayment(@PathVariable(name = "idPayment") Integer idPayment) {
        paymentService.deletePayment(idPayment);
    }
}
