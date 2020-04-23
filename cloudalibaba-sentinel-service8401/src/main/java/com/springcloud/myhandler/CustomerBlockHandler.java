package com.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommontResult;
import com.springcloud.entities.Payment;

/**
 * @author: DELL
 * @Date: 2020/4/22 15:37
 * @Description:
 */
public class CustomerBlockHandler {
    public static CommontResult handlerException(BlockException exception) {
        return new CommontResult(4444, "按客户自定义,global handlerException", new Payment(2020L, "serial0003"));
    }
    public static CommontResult handlerException2(BlockException exception) {
        return new CommontResult(5555, "按客户自定义2,global handlerException", new Payment(2020L, "serial0003"));
    }
}
