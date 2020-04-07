package com.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: DELL
 * @Date: 2020/4/7 15:10
 * @Description:
 */
@Component
public class MyLB implements LoadBalance {
    private AtomicInteger atomicInteger=new AtomicInteger(0);

    public  final int getAndIncrement(){
        int current;
        int next;
        do {
            current=this.atomicInteger.getAndIncrement();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        }while (this.atomicInteger.compareAndSet(current, next));
            System.out.println("****next: " + next);
            return next;

    }
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstanceList) {
        int index = getAndIncrement()%serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}
