package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommontResult;
import com.springcloud.entities.Payment;
import com.springcloud.myhandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: DELL
 * @Date: 2020/4/22 15:18
 * @Description:
 */
@RestController
@Slf4j
public class RateLimitController {


    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommontResult byResource(){
        return new CommontResult(200, "按资源名称限流测试ok", new Payment(2020L, "serial001"));
    }

    public CommontResult handleException(BlockException exception) {
        return new CommontResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl",blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException")
    public CommontResult byUrl(){
        return new CommontResult(200,"按url限流测试OK", new Payment(2020L, "serial001"));
    }

}
