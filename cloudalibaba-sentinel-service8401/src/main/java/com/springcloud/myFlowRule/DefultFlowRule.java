package com.springcloud.myFlowRule;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: DELL
 * @Date: 2020/4/24 09:43
 * @Description: 这个是自己定义默认加载的
 */
@Component
@Slf4j
public class DefultFlowRule  implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            List<FlowRule> rules = FlowRuleManager.getRules();
            if (rules == null) {
                rules = new ArrayList<>();
            }
            FlowRule flowRule = new FlowRule("/refreshRoutes");
            flowRule.setCount(5).setClusterMode(false).setControlBehavior(0);
            flowRule.setLimitApp("default");
            rules.add(flowRule);
            log.info("FlowRuleRunner loadRules reules:{}  ", rules);
            FlowRuleManager.loadRules(rules);
        } catch (Exception e) {
            log.error("FlowRuleRunner 加载异常 :{}", e.getMessage());
        }
    }
}
