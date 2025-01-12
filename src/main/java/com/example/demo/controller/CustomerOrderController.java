package com.example.demo.controller;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Discount;
import com.example.demo.service.ICustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/customer-orders")
public class CustomerOrderController {

    @Autowired
    private ICustomerOrderService customerOrderService;

    // Create a new customer order
    @PostMapping
    public CustomerOrder createCustomerOrder(@RequestParam String customerEmail,
                                             @RequestParam String customerAddress,
                                             @RequestParam String orderDate, // Recibir como String
                                             @RequestBody List<OrderItem> items) {
        Date parsedDate = parseDate(orderDate);
        return customerOrderService.createOrder(customerEmail, customerAddress, parsedDate, items);
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString);
        }
    }

    // Add an order item to an existing customer order
    @PostMapping("/{orderId}/items")
    public void addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem item) {
        customerOrderService.addOrderItem(orderId, item);
    }

    // Remove an order item from an existing customer order
    @DeleteMapping("/{orderId}/items")
    public void removeOrderItem(@PathVariable Long orderId, @RequestBody OrderItem item) {
        customerOrderService.removeOrderItem(orderId, item);
    }

    // Apply a discount to a customer order
    @PostMapping("/{orderId}/discount")
    public void applyDiscount(@PathVariable Long orderId, @RequestBody Discount discount) {
        customerOrderService.applyDiscount(orderId, discount);
    }

    // Update the delivery status of a customer order
    @PutMapping("/{orderId}/delivery")
    public void sendForDelivery(@PathVariable Long orderId) {
        customerOrderService.sendForDelivery(orderId);
    }

    // Get a customer order by its ID
    @GetMapping("/{orderId}")
    public CustomerOrder getCustomerOrderById(@PathVariable Long orderId) {
        return customerOrderService.getOrderById(orderId);
    }
}
