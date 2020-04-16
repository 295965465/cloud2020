package springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: DELL
 * @Date: 2020/4/15 16:21
 * @Description:
 */
@RestController
@Slf4j
@RefreshScope
public class CofigClientController {
    @Value("${config.info}")
    private String configInfo;
    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return "serverPort:"+serverPort+" config:"+ configInfo;
    }
}
