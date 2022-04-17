package com.pheonix.ecommerce.dto;

import com.pheonix.ecommerce.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class Purchase {

    private Customer customer;
    private Order order;
    private Address billingAddress;
    private Address shippingAddress;
    private List<OrderItems> orderItems;

}
