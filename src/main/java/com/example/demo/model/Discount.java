package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal discountPercentage;

    // Constructors
    public Discount() {}

    public Discount(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    // Getters and Setters
    // (Generar usando tu IDE o manualmente según se necesite)

    // Business Logic
    public BigDecimal applyDiscount(BigDecimal totalAmount) {
        return totalAmount.subtract(totalAmount.multiply(discountPercentage.divide(BigDecimal.valueOf(100))));
    }
}
