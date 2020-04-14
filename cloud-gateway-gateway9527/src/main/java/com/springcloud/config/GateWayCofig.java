package com.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: DELL
 * @Date: 2020/4/14 10:57
 * @Description:
 */
@Configuration
public class GateWayCofig {
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder RouteLocatorBuilder){

        RouteLocatorBuilder.Builder route = RouteLocatorBuilder.routes();
        route.route("path_route_com",r->{
            return r.path("/guonei").uri("http://news.baidu.com/guonei");}


        ).build();
        return route.build();
    }
}
