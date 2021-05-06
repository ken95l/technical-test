package com.luminor.test.model;

import com.luminor.test.util.Utilities;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

@Entity @Getter @Setter
public abstract class Payment {

    @Id @NonNull @Setter(AccessLevel.NONE)
    private final UUID uuid;

    @NonNull @Setter(AccessLevel.NONE)
    private final Instant paymentCreationTime;

    public enum PaymentType {
        TYPE1,
        TYPE2,
        TYPE3
    }

    @NonNull @Setter(AccessLevel.PRIVATE)
    private PaymentType paymentType;

    /**
     * Value is of currency type EUR
     */
    private Double cancellationFee;

    @NonNull
    private final Double cancellationFeeCoefficient;

    /**
     * Must be a positive value.
     * Used a wrapper type so the default value gets set to null instead of 0.0.
     * */
    @NonNull
    private Double amount;

    public enum CurrencyType {
        EUR,
        USD
    }

    @NonNull
    private Payment.CurrencyType currencyType;

    @NonNull
    private String debtorIBAN;

    @NonNull
    private String creditorIBAN;

    public Payment(@NonNull Payment.PaymentType paymentType,
                   @NonNull Double amount,
                   @NonNull Payment.CurrencyType currencyType,
                   @NonNull String debtorIBAN,
                   @NonNull String creditorIBAN,
                   @NonNull Double cancellationFeeCoefficient) {
        this.cancellationFeeCoefficient = cancellationFeeCoefficient;
        this.uuid = UUID.randomUUID();
        this.paymentCreationTime = Instant.now(Clock.systemUTC());
        setPaymentType(paymentType);
        setAmount(amount);
        setCurrencyType(currencyType);
        setDebtorIBAN(debtorIBAN);
        setCreditorIBAN(creditorIBAN);
    }

    public void setDebtorIBAN(String debtorIBAN) {
        String debtorIBANTrimmed = StringUtils.trimAllWhitespace(debtorIBAN);

        if (!Utilities.hasCorrectIBANFormat(debtorIBANTrimmed))
            throw new IllegalArgumentException("The provided IBAN is not in a valid format.");

        this.debtorIBAN = debtorIBANTrimmed;
    }

    public void setCreditorIBAN(String creditorIBAN) {
        String creditorIBANTrimmed = StringUtils.trimAllWhitespace(creditorIBAN);

        if (!Utilities.hasCorrectIBANFormat(creditorIBANTrimmed))
            throw new IllegalArgumentException("The provided IBAN is not in a valid format.");

        this.creditorIBAN = creditorIBANTrimmed;
    }

    public void setAmount(Double amount) {
        if (amount < 0.0)
        if (amount < 0.0)
            throw new IllegalArgumentException("Payment amount value can't be negative.");

        this.amount = amount;
    }


}
