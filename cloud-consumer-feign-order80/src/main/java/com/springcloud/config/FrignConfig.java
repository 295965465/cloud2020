package com.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Auther: DELL
 * @Date: 2020/4/8 13:37
 * @Description:
 */
@Configuration
public class FrignConfig {
@Bean
  Logger.Level myfeignConfig(){
    return Logger.Level.FULL;
}

}
