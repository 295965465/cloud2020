package com.springcloud.service.impl;

import cn.hutool.core.lang.UUID;
import com.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @Auther: DELL
 * @Date: 2020/4/17 14:22
 * @Description:
 */
@EnableBinding(Source.class) //定义消息的推送管道
@Slf4j
public class IMessageProviderImpl implements IMessageProvider {
    @Autowired
    private MessageChannel output ;


    @Override
    public String send() {
        String id= UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(id).build());
        log.info("sendMessage->>{}",id);
        return id;
    }
}
