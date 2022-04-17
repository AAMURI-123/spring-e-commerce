package com.pheonix.ecommerce.controller;

import com.pheonix.ecommerce.dto.Purchase;
import com.pheonix.ecommerce.dto.PurchaseReturnValue;
import com.pheonix.ecommerce.service.ServiceControll;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/checkout")
public class CheckoutController {

    private ServiceControll serviceControll;

    public CheckoutController(ServiceControll serviceControll){
        this.serviceControll = serviceControll;
    }

    @PostMapping("/purchase")
    public PurchaseReturnValue placeOrder (@RequestBody Purchase purchase){

        PurchaseReturnValue  purchaseReturnValue = serviceControll.palecOrder(purchase);
        return  purchaseReturnValue;
    }
}
