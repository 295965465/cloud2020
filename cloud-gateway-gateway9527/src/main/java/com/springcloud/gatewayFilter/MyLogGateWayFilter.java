package com.springcloud.gatewayFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Auther: DELL
 * @Date: 2020/4/14 13:48
 * @Description:
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("********************* MyLogGateWayFilter"+new Date());
        String uname=exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null){
            log.info("****************用户名为null,非法用户,");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 加载过滤器的顺序，数字越小级别越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
