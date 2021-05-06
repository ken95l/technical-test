package com.luminor.test.service;

import com.luminor.test.repository.PaymentRepository;
import com.luminor.test.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    //  paymentRepository bean wouldn't get initiated, couldn't find a solution so far
    private final PaymentRepository paymentRepository = new PaymentRepository();

//    private final PaymentRepository paymentRepository;
//
//    @Autowired
//    public PaymentService(PaymentRepository paymentRepository) {
//        this.paymentRepository = paymentRepository;
//    }

    public void addPaymentType(Payment payment) {
        paymentRepository.save(payment);
    }
    public void cancelPaymentById(String id) {
        Optional<Payment> payment = paymentRepository.findById(UUID.fromString(id));

        if (payment.isEmpty())
            throw new IllegalArgumentException("The payment with the provided ID doesn't exist.");

        OffsetDateTime paymentCreationTime = payment.get().getPaymentCreationTime().atOffset(ZoneOffset.UTC);
        OffsetDateTime currentTime = OffsetDateTime.now(Clock.systemUTC());

        if (paymentCreationTime.getYear() != currentTime.getYear() || paymentCreationTime.getDayOfYear() != currentTime.getDayOfYear())
            throw new IllegalArgumentException("The payment is too old to cancel.");

        var cancellationFee = (currentTime.getHour() - paymentCreationTime.getHour()) * payment.get().getCancellationFeeCoefficient();
        payment.get().setCancellationFee(cancellationFee);
        paymentRepository.save(payment.get());
    }

    // Exclusive
    public List<UUID> getAllNotCanceledPaymentIdsByAmountLimit(double bottomLimit, double topLimit) {
        return paymentRepository.findAll().stream()
                .filter(payment -> payment.getCancellationFee() == null
                        || payment.getAmount() > bottomLimit
                        || payment.getAmount() < topLimit)
                .map(Payment::getUuid)
                .collect(Collectors.toList());
    }

    public List<UUID> getAllNotCanceledPaymentIds() {
        return paymentRepository.findAll().stream()
                .filter(payment -> payment.getCancellationFee() == null)
                .map(Payment::getUuid)
                .collect(Collectors.toList());
    }

    public Map<UUID, Double> getPaymentIdAndCancellationFeeById(String id) {
        Payment payment = paymentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("Payment ID was invalid."));

        return Collections.singletonMap(payment.getUuid(), payment.getCancellationFee());
    }
}
