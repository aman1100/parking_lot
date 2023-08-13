package models;

import enums.PaymentStatus;

import java.time.Instant;

public class Payment {
    private PaymentMode paymentMode;
    private Instant time;
    private int amount; //in financial we prefer Integer because of precision Integer provides precised value
    private PaymentStatus paymentStatus;
    private String referenceNumber;
}
