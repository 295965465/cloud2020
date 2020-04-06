package com.springcloud.controller;

import com.springcloud.entities.CommontResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Auther: DELL
 * @Date: 2020/4/1 17:32
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
   private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping(value = "payment/create")
    public CommontResult Create(@RequestBody Payment payment){
      int result= paymentService.create(payment);
      log.info("结果是啊:{}"+result);
      if (result>0){
          return new CommontResult(200,"数据库插入成功:server.port:"+serverPort,result);
      }else{
          return new CommontResult(440,"数据库插入失败");
      }
    }
    @GetMapping(value = "payment/get/{id}")
    public CommontResult Create(@PathVariable("id") Long id){
        Payment payment= paymentService.getPaymentById(id);
        log.info("结果:{}"+payment);
        if (!StringUtils.isEmpty(payment)){
            return new CommontResult(200,"查询成功:server.port:"+serverPort,payment);
        }else{
            return new CommontResult(440,"查询失败，ID->"+id,null);
        }
    }

}
