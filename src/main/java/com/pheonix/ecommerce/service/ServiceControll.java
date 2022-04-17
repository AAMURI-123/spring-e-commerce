package com.pheonix.ecommerce.service;

import com.pheonix.ecommerce.dto.Purchase;
import com.pheonix.ecommerce.dto.PurchaseReturnValue;

public interface ServiceControll {

    PurchaseReturnValue palecOrder(Purchase purchase);
}
