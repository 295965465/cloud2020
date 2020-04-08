package com.springcloud.service;

import com.springcloud.entities.CommontResult;
import com.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: DELL
 * @Date: 2020/4/8 10:54
 * @Description:
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "payment/get/{id}")
    CommontResult getPayment(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/FeignTimeout")
    public String PaymentFeignTimeout();
}
