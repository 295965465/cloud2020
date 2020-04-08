package com.springcloud.controller;

import com.springcloud.entities.CommontResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: DELL
 * @Date: 2020/4/8 11:04
 * @Description:
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/cousumer/payment/get/{id}")
    public CommontResult<Payment> getPayment(@PathVariable("id") Long id) {
        return paymentFeignService.getPayment(id);
    }

    @GetMapping(value = "/cousumer/payment/FeignTimeout")
    public String PaymentFeignTimeout(){
        return  paymentFeignService.PaymentFeignTimeout();
    }
}
