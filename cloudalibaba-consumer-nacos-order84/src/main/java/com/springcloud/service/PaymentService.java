package com.springcloud.service;

import com.springcloud.entities.CommontResult;
import com.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: DELL
 * @Date: 2020/4/23 17:16
 * @Description:
 */
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")
    CommontResult<Payment> PaymentSQL(@PathVariable("id") Long id);
}
