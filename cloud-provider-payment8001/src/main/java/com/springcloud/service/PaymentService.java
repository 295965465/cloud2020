package com.springcloud.service;

import com.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: DELL
 * @Date: 2020/4/1 17:24
 * @Description:
 */
public interface PaymentService {
    public int create(Payment payment);
    public  Payment getPaymentById(@Param("id") Long id);
}
