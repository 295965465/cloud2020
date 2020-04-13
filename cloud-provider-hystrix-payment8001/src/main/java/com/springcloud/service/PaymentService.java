package com.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"),//超时等待

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
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties ={
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "1000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+ "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，o(╥﹏╥)o id：" + id;
    }


}
