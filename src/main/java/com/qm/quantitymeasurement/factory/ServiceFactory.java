package com.qm.quantitymeasurement.factory;

import com.qm.quantitymeasurement.service.QuantityService;
import com.qm.quantitymeasurement.service.impl.QuantityServiceImpl;

public class ServiceFactory {
    private ServiceFactory() {
    }

    public static QuantityService
    createQuantityService() {

        return new QuantityServiceImpl();
    }
}
