package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private BigDecimal amount;
    private Date paymentDate;
    private String paymentStatus;

    // Constructors
    public Payment() {}

    public Payment(BigDecimal amount, Date paymentDate) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = "Pending";
    }

    // Getters and Setters
    // (Generar usando tu IDE o manualmente según se necesite)

    // Business Logic
    public void processPayment() {
        if (!paymentStatus.equals("Pending")) {
            throw new IllegalStateException("Payment is already processed.");
        }
        this.paymentStatus = "Completed";
    }
}
