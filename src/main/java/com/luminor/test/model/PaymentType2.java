package com.luminor.test.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class PaymentType2 extends Payment {

    public PaymentType2(@NonNull Double amount,
                        @NonNull String debtorIBAN,
                        @NonNull String creditorIBAN,
                        String details) {
        super(PaymentType.TYPE2, amount, Payment.CurrencyType.USD, debtorIBAN, creditorIBAN, 0.1);

        if (details != null)
            this.details = details.trim();
    }

    private String details;
}

