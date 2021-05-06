package com.luminor.test.controller;

import com.luminor.test.model.Payment;
import com.luminor.test.model.PaymentType1;
import com.luminor.test.model.PaymentType2;
import com.luminor.test.model.PaymentType3;
import com.luminor.test.service.PaymentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/v1/payment")
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<UUID> getAllPaymentIds() {
        return paymentService.getAllNotCanceledPaymentIds();
    }
    @GetMapping("/id/{id}")
    public Map<UUID, Double> getPaymentIdAndCancellationFeeById(@PathVariable("id") String id) {
        return paymentService.getPaymentIdAndCancellationFeeById(id);
    }

    @GetMapping("/filter/{botLimit}/{topLimit}")
    public List<UUID> getAllNotCanceledPaymentIdsByAmountLimit(@PathVariable("botLimit") double bottomLimit,
                                                               @PathVariable("topLimit") double topLimit) {
        return paymentService.getAllNotCanceledPaymentIdsByAmountLimit(bottomLimit, topLimit);
    }

    @PostMapping("/1")
    public void addPaymentType1(@RequestBody PaymentType1 payment) {

        paymentService.addPaymentType(payment);
    }

    @PostMapping("/2")
    public void addPaymentType2(@RequestBody PaymentType2 payment) {

        paymentService.addPaymentType(payment);
    }

    @PostMapping("/3")
    public void addPaymentType3(@RequestBody PaymentType3 payment) {

        paymentService.addPaymentType(payment);
    }

    @PostMapping("/cancel")
    public void cancelPayment(@RequestBody @NonNull String id) {
        paymentService.cancelPaymentById(id);
    }
}
