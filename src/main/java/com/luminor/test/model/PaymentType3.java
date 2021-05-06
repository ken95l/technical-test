package com.luminor.test.model;

import com.luminor.test.util.Utilities;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import org.springframework.util.StringUtils;

@Getter @Setter
public class PaymentType3 extends Payment {

    public PaymentType3(@NonNull Double amount,
                        @NonNull Payment.CurrencyType currency,
                        @NonNull String debtorIBAN,
                        @NonNull String creditorIBAN,
                        @NonNull String creditorBIC) {
        super(PaymentType.TYPE3, amount, currency, debtorIBAN, creditorIBAN, 0.15);

        setCreditorBIC(creditorBIC);
    }

    public void setCreditorBIC(String creditorBIC) {
        String creditorBICTrimmed = StringUtils.trimAllWhitespace(creditorBIC);

        if (!Utilities.hasCorrectBICFormat(creditorBICTrimmed))
            throw new IllegalArgumentException("The provided BIC is not in a valid format.");

        this.creditorBIC = creditorBICTrimmed;
    }

    @NonNull
    private String creditorBIC;
}

