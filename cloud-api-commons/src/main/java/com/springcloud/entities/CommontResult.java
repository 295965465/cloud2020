package com.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: DELL
 * @Date: 2020/4/1 10:50
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommontResult<T> {
    private Integer code;
    private String message;

    private T    data;
    //创建一个构造器
    public CommontResult(Integer code,String message) {
        //返回的是默认构造器
       this(code,message,null);
    }
}
