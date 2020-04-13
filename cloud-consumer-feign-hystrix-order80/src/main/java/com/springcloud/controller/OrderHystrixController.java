package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.entities.CommontResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: DELL
 * @Date: 2020/4/9 14:45
 * @Description:
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "DefultFallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {

        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod ="paymentTimeOutFallbackMethod" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })*/
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {


        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
    public  String paymentTimeOutFallbackMethod(Integer id){
        return  "我是80:   线程池  "+Thread.currentThread().getName()+" paymentTimeOutFallbackMethod" +id+"请稍后尝试o(╥﹏╥)o";

    }
   //下面是全局的 要用通用性！
   public  String DefultFallbackMethod(){
       return  "我是全局的的80:   线程池  "+Thread.currentThread().getName()+"请稍后尝试o(╥﹏╥)o";

   }
}
