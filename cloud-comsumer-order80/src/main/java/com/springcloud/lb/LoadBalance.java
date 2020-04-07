package com.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Auther: DELL
 * @Date: 2020/4/7 15:07
 * @Description:
 */
public interface LoadBalance {
    ServiceInstance instance(List<ServiceInstance> serviceInstanceList);
}
