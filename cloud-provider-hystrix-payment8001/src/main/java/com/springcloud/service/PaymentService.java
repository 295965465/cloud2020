package com.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: DELL
 * @Date: 2020/4/8 14:48
 * @Description:
 */
@Service
public class PaymentService {
    /**
     * 正常访问
     * @param id
     * @return
     */
 public  String paymentInfo_Ok(Integer id){
     return  "线程池  "+Thread.currentThread().getName()+" paymentInfo_Ok" +id+"^_^";
 }
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public  String paymentInfo_TimeOut(Integer id){
         int time=3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  "线程池  "+Thread.currentThread().getName()+" paymentInfo_Ok" +id+"^_^"+"耗时"+time+"秒钟";
    }
    public  String paymentInfo_TimeOutHandler(Integer id){
        return  "线程池  "+Thread.currentThread().getName()+" paymentInfo_TimeOutHandler" +id+"请稍后尝试o(╥﹏╥)o";

    }
}
