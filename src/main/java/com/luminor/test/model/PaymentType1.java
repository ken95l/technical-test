package com.luminor.test.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

@Getter @Setter
public class PaymentType1 extends Payment {

    public PaymentType1(@NonNull Double amount,
                        @NonNull String debtorIBAN,
                        @NonNull String creditorIBAN,
                        @NonNull String details) {
        super(PaymentType.TYPE1, amount, Payment.CurrencyType.EUR, debtorIBAN, creditorIBAN, 0.05);

        setDetails(details.trim());
    }

    @NonNull
    private String details;
}

