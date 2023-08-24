package com.pgx.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/sentinel/*")
public class DemoController {

    private static final String RESOURCE_NAME = "hello";
    private static final String RESOURCE_NAME_ANNOTATE = "annotate";

    private static final String DEGRADE_RESOURCE_NAME = "degradeRule";

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

    /**
     * valne = 资源名称，blockHandler指定流控处理方法，fallback指定捕获方法发生异常时的方法 【且blockHandler优先级大于fallback】
     * @param message
     * @return
     */
    @RequestMapping("/byAnnotate")
    @ResponseBody
    @SentinelResource(value = RESOURCE_NAME_ANNOTATE,blockHandler = "getByAnnotateHandler",fallback = "byAnnotateExceptionHandler")
    public Map<String, String> byAnnotate(String message){
        int a = 10/0;
        Map resultMap = new HashMap<String, String>();
        resultMap.put("code","0");
        resultMap.put("description","访问成功！");
        return resultMap;
    }

    public Map<String, String> getByAnnotateHandler(String message ,BlockException ex){
        Map resultMap = new HashMap<String, String>();
        resultMap.put("code","-1");
        resultMap.put("description","流量控制");
        log.error(message + " exception = " + ex.getMessage());
        return resultMap;
    }
    public Map<String,String> byAnnotateExceptionHandler(String message,Throwable ex){
        Map resultMap = new HashMap<String, String>();
        resultMap.put("code","-1");
        resultMap.put("description","发生异常");
        log.error(message + " exception = " + ex.getMessage());
        return resultMap;
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

        // 流控规则
        FlowRule flowRule2 = new FlowRule();
        // 需要流控的资源
        flowRule2.setResource(RESOURCE_NAME_ANNOTATE);
        // 流控的规则
        flowRule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 一秒内可请求数
        flowRule2.setCount(1);
        rules.add(flowRule2);
        FlowRuleManager.loadRules(rules);




    }

    @PostMapping("degradeRuleTest")
    @ResponseBody
    @SentinelResource(value = DEGRADE_RESOURCE_NAME,entryType = EntryType.IN, blockHandler = "degradeHandler")
    public Map<String,String> degradeRules() {
        log.warn(DEGRADE_RESOURCE_NAME + " method in ");
        int i = 10/0;
        Map result = new HashMap<String,String>();
        result.put("code","0");
        result.put("desc","访问成功");
        return result;
    }

    // 参数最后必须加BlockException ex 这个参数，否则无效
    public Map<String,String> degradeHandler(BlockException ex){
        log.warn("发生易容降级");
        Map<String,String> result = new HashMap<String,String>();
        result.put("code","-1");
        result.put("desc","熔断降级");
        return result;
    }

    @PostConstruct
    public void initDegradeRule(){
        List<DegradeRule> ruleList = new ArrayList<DegradeRule>();
        DegradeRule rule = new DegradeRule();
        rule.setResource(DEGRADE_RESOURCE_NAME);
        // 设置降级监控类型
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        // 触发熔断的异常数
        rule.setCount(2);
        // 降级多久秒恢复
        rule.setTimeWindow(10);
        // 触发熔断的最小请求数，MinRequestAmount优先级大于Count
        rule.setMinRequestAmount(10);
        // 单位时间 ms      5min
        rule.setStatIntervalMs(5 * 60 * 1000);

        ruleList.add(rule);
        DegradeRuleManager.loadRules(ruleList);

    }
}
