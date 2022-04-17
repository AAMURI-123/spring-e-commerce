package com.pheonix.ecommerce.service;

import com.pheonix.ecommerce.dto.Purchase;
import com.pheonix.ecommerce.dto.PurchaseReturnValue;
import com.pheonix.ecommerce.entity.Customer;
import com.pheonix.ecommerce.entity.Order;
import com.pheonix.ecommerce.entity.OrderItems;
import com.pheonix.ecommerce.repository.CustomerRepository;
import com.pheonix.ecommerce.service.ServiceControll;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseControllerImpl implements ServiceControll {

    private CustomerRepository customerRepository;

    public PurchaseControllerImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    };


    @Override
    @Transactional
    public PurchaseReturnValue palecOrder(Purchase purchase) {

        // set Order and assign from purchase
        Order order = purchase.getOrder();

        // generate OrderTrackingNumber
        String orderTrackingNumber = getOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // set billing and shipping Address values
        order.setShippingAddressId(purchase.getShippingAddress());
        order.setBillingAddressId(purchase.getBillingAddress());

        // TO-DO ...assign a payment mode
        //order.setPaymentMode(purchase.getPayment());

        // set OrderItems and assign a value
        //populate order with orderItem
        List<OrderItems> orderItems = new ArrayList<>();
        orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // set customeer and assign value
        Customer customer = new Customer();
    customer = purchase.getCustomer();
        // check if customer is alreay exist
        String theEmail = customer.getEmail();
        Customer customerFromDB = customerRepository.findByEmail(theEmail);
        if(customerFromDB != null){
            customer = customerFromDB;
        }

        customer.add(order);

        // save to the database
        customerRepository.save(customer);

        return new PurchaseReturnValue(orderTrackingNumber);
    }

    private String getOrderTrackingNumber() {

        return UUID.randomUUID().toString();
    }
}
