package com.springcloud.controller;

import com.springcloud.entities.CommontResult;
import com.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Auther: DELL
 * @Date: 2020/4/2 10:51
 * @Description:
 */
@RestController
@Slf4j
public class OrderController {
    @Resource
    private RestTemplate RestTemplate;
    private static final String urls = "http://CLOUD-PAYMENT-SERVICE";

    @PostMapping("/cousumer/payment/create")
    public CommontResult<Payment> create(Payment payment) {
        return RestTemplate.postForObject(urls + "/payment/create", payment, CommontResult.class);
    }
    @GetMapping("/cousumer/payment/get/{id}")
    public CommontResult<Payment> getPayment(@PathVariable("id") Long id) {
        return RestTemplate.getForObject(urls + "/payment/get/"+id, CommontResult.class);
    }


    @GetMapping("/cousumer/payment/getForEntity/{id}")
    public CommontResult<Payment> getForEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommontResult> forEntity = RestTemplate.getForEntity(urls + "/payment/get/" + id, CommontResult.class);
        log.info(forEntity.getStatusCodeValue()+"a");
        if (forEntity.getBody().getCode().equals(200)&&forEntity.getStatusCode().is2xxSuccessful()){

            return forEntity.getBody() ;
        }else{
            return new CommontResult<>(404,"查询失败",null);

        }

    }
}
