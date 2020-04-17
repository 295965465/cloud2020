package com.springcloud.controller;

import com.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: DELL
 * @Date: 2020/4/17 14:35
 * @Description:
 */

@RestController
@Slf4j
public class IMessageController {
    @Resource
    private IMessageProvider  IMessageProvider;
    @GetMapping(value = "/sendMessage")
    public String sendMessage(){

        return IMessageProvider.send();
    }
}
