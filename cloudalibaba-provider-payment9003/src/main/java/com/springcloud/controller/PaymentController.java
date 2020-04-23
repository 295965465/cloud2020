package com.springcloud.controller;

import com.springcloud.entities.CommontResult;
import com.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author: DELL
 * @Date: 2020/4/22 16:45
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "28a8c1e3bc2742d8848569891fb421811"));
        hashMap.put(2L, new Payment(2L, "28a4c1e3bc2742d8848569891fb421812"));
        hashMap.put(3L, new Payment(3L, "28a5c1e3bc2742d8848569891fb421813"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommontResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommontResult<Payment> result = new CommontResult<>(200, "from mysql,serverPort: " + serverPort, payment);
        return result;
    }
}
