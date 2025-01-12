package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerEmail;
    private String customerAddress;
    private Date orderDate;
    private boolean delivered;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderItem> items = new ArrayList<>();

    public CustomerOrder() {}

    public CustomerOrder(String customerEmail, String customerAddress, Date orderDate) {
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
    }

    // Métodos para gestionar los items
    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items.clear();
        if (items != null) {
            for (OrderItem item : items) {
                addOrderItem(item);
            }
        }
    }

    public void addOrderItem(OrderItem item) {
        items.add(item);
        item.setCustomerOrder(this);
    }

    public void removeOrderItem(OrderItem item) {
        items.remove(item);
        item.setCustomerOrder(null);
    }

    public void applyDiscount(Discount discount) {
        // Lógica para aplicar un descuento
    }

    public void markAsDelivered() {
        this.delivered = true;
    }

    // Getters y Setters generales
    public Long getId() {
        return id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}
