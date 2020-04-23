package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommontResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: DELL
 * @Date: 2020/4/23 15:37
 * @Description:
 */
@RestController
@Slf4j
public class CircleBreakerController {
    @Value("${service-url.nacos-user-service}")
    private String providerServer;
    @Resource
    private RestTemplate RestTemplate;
    @Resource
    private PaymentService paymentService;

    @RequestMapping("/consumer/fallback/{id}")
   // @SentinelResource(value = "fallback")
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback只负责业务异常
   //  @SentinelResource(value = "fallback",blockHandler = "blockHandler")
    @SentinelResource(value = "fallback",fallback = "handlerFallback",
           blockHandler = "blockHandler") //同时配被限流只找的是blochandler  优先级openfeign的上的fallback>blockhandler>handlerfallback
   //         ,exceptionsToIgnore = {IllegalArgumentException.class}
    public CommontResult<Payment> fallback(@PathVariable("id") Long id){

        //CommontResult<Payment> result = RestTemplate.getForObject(providerServer + "/paymentSQL/" + id, CommontResult.class, id);
        CommontResult<Payment> result =paymentService.PaymentSQL(id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return result;
    }
    public CommontResult handlerFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommontResult(444, "兜底异常handlerFallback，exception内容" + e.getMessage(), payment);
    }
    public CommontResult blockHandler(@PathVariable("id") Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommontResult(445, "blockHandler-sentinel限流，无此流水：blockException" + blockException.getClass().getCanonicalName(), payment);
    }
}
