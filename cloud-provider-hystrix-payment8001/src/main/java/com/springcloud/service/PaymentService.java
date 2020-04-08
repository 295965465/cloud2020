package com.springcloud.service;

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

    public  String paymentInfo_TimeOut(Integer id){
         int time=3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  "线程池  "+Thread.currentThread().getName()+" paymentInfo_Ok" +id+"^_^"+"耗时"+time+"秒钟";
    }

}
