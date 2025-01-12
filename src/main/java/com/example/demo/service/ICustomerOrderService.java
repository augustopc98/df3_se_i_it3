package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Discount;

import java.util.Date;
import java.util.List;

public interface ICustomerOrderService {
    CustomerOrder createOrder(String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items);
    void addOrderItem(Long orderId, OrderItem item);
    void removeOrderItem(Long orderId, OrderItem item);
    void applyDiscount(Long orderId, Discount discount);
    void sendForDelivery(Long orderId);
    CustomerOrder getOrderById(Long orderId);
}
