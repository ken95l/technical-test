package com.luminor.test.util;

import java.util.regex.Pattern;

public class Utilities {
    // International Bank Account Number https://bank.codes/iban/structure/
    final static Pattern IBAN_REGEX_PATTERN = Pattern.compile("^[a-zA-Z]{2}\\d{2}\\d{12,30}$");
    // Business Identifier Code https://wise.com/us/swift-codes/
    final static Pattern BIC_REGEX_PATTERN = Pattern.compile("^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}[a-zA-Z0-9]{3}$");

    public static boolean hasCorrectIBANFormat(String ibanString){
        return IBAN_REGEX_PATTERN.matcher(ibanString).matches();
    }

    public static boolean hasCorrectBICFormat(String bicString){
        return BIC_REGEX_PATTERN.matcher(bicString).matches();
    }
}
