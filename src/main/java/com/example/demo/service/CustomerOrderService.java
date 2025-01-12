package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.Discount;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CustomerOrderService implements ICustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    @Transactional
    public CustomerOrder createOrder(String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items) {
        CustomerOrder order = new CustomerOrder(customerEmail, customerAddress, orderDate);
        order.setItems(items);
        return customerOrderRepository.save(order);
    }

    @Override
    @Transactional
    public void addOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = getOrderById(orderId);
        order.addOrderItem(item);
        customerOrderRepository.save(order);
    }

    @Override
    @Transactional
    public void removeOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = getOrderById(orderId);
        order.removeOrderItem(item);
        customerOrderRepository.save(order);
    }

    @Override
    @Transactional
    public void applyDiscount(Long orderId, Discount discount) {
        CustomerOrder order = getOrderById(orderId);
        order.applyDiscount(discount);
        customerOrderRepository.save(order);
    }

    @Override
    @Transactional
    public void sendForDelivery(Long orderId) {
        CustomerOrder order = getOrderById(orderId);
        order.markAsDelivered();
        customerOrderRepository.save(order);
    }

    @Override
    @Transactional
    public CustomerOrder getOrderById(Long orderId) {
        CustomerOrder order = customerOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("CustomerOrder not found"));
        // Asegurarse de que la colección `items` esté cargada
        if (order.getItems() != null) {
            order.getItems().size(); // Esto fuerza la inicialización de la colección
        }
        return order;
    }
}
