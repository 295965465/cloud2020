package com.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Auther: DELL
 * @Date: 2020/4/13 17:34
 * @Description:
 */
@Component
public class PaymentHystrixFallbackService implements  PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "Fallback paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "Fallback paymentInfo_TimeOut";
    }
}
