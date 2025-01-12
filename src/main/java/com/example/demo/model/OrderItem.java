package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private int quantity;
    private BigDecimal productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_order_id")
    @JsonBackReference
    private CustomerOrder customerOrder;

    public OrderItem() {}

    public OrderItem(Long productId, int quantity, BigDecimal productPrice) {
        if (productId == null || productPrice == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid OrderItem: productId, productPrice, and quantity must be valid");
        }
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public BigDecimal getPrice() {
        return productPrice != null ? productPrice.multiply(BigDecimal.valueOf(quantity)) : BigDecimal.ZERO;
    }
}
