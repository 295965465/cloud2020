package com.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: DELL
 * @Date: 2020/4/2 10:59
 * @Description:
 */
@Configuration
public class ApplicationContextConfig {

@Bean
//@LoadBalanced
public RestTemplate getRestTemplate(){
    return new RestTemplate();
}
}
