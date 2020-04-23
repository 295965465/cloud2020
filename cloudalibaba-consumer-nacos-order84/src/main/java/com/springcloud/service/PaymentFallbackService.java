package com.springcloud.service;

import com.springcloud.entities.CommontResult;
import com.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

/**
 * @author: DELL
 * @Date: 2020/4/23 17:18
 * @Description:
 */
@Service
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommontResult<Payment> PaymentSQL(Long id) {
        return new CommontResult<>(44444,"服务降级返回，----PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}