package com.pgx.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/sentinel/*")
public class DemoController {

    private static final String RESOURCE_NAME = "hello";

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        try(Entry entry = SphU.entry(RESOURCE_NAME);){
            // 资源名称定义，与下面的initFlowRules 中的资源保持一致
            String message = "Hello sentinel!";
            log.info("hello sentinel");
            return message;
        } catch (BlockException e) {
            e.printStackTrace();
            log.warn("每秒请求数大于1，所以流控");
            return "每秒请求数大于1，所以流控";
        }

       /* try (Entry entry = SphU.entry("HelloWorld")) {
            // Your business logic here.
            System.out.println("hello world");
            return "hello sentinel";
        } catch (BlockException e) {
            // Handle rejected request.
            e.printStackTrace();
            return "refused";
        }*/

    }

    @PostConstruct
    private static void initFlowRules(){
        // 流控规则队列
        List<FlowRule> rules = new ArrayList<>();
        // 流控规则
        FlowRule flowRule = new FlowRule();
        // 需要流控的资源
        flowRule.setResource(RESOURCE_NAME);
        // 流控的规则
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 一秒内可请求数
        flowRule.setCount(1);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);

/*        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
// set limit qps to 20
        rule.setCount(1);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);*/


    }
}
