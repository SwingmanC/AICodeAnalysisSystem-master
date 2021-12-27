package org.nju.demo.controller;

import org.nju.demo.entity.ARule;
import org.nju.demo.entity.AUser;
import org.nju.demo.service.RuleService;
import org.nju.demo.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private HttpSession session;

    @RequestMapping("/view/rules")
    public String viewRules(){
        return "rule_list";
    }

    @ResponseBody
    @RequestMapping("/rules")
    public List<ARule> getRules(){
        AUser user = (AUser) session.getAttribute("user");
        return ruleService.getRules(user.getId());
    }

    @ResponseBody
    @RequestMapping("/rule/{ruleId}")
    public ARule getRule(@PathVariable("ruleId") String ruleId){
        return ruleService.getRule(ruleId);
    }

    @RequestMapping(value = "/addRule",method = RequestMethod.POST)
    public String addRule(@RequestParam("ruleName") String ruleName,
                          @RequestParam(value = "patternName",required = false) String patternName,
                          @RequestParam(value = "priority",required = false) String priority,
                          @RequestParam(value = "kingdom",required = false) String kingdom,
                          @RequestParam(value = "fileName",required = false) String fileName,
                          @RequestParam(value = "functionName",required = false) String functionName){
        ARule rule = new ARule();
        AUser user = (AUser) session.getAttribute("user");
        rule.setRuleId(StringUtil.generateStringId());
        rule.setRuleName(ruleName);
        if (patternName == null) rule.setPatternName("");
        else rule.setPatternName(patternName);

        if (kingdom == null) rule.setKingdom("");
        else rule.setKingdom(kingdom);

        if (priority == null) rule.setPriority("");
        else rule.setPriority(priority);

        if (fileName == null) rule.setFileName("");
        else rule.setFileName(fileName);

        if (functionName == null) rule.setFunctionName("");
        else rule.setFunctionName(functionName);

        rule.setUserId(user.getId());
        ruleService.addRule(rule);
        return "redirect:/view/rules";
    }

    @ResponseBody
    @RequestMapping(value = "/editRule",method = RequestMethod.POST)
    public int editRule(@RequestParam("ruleId") String ruleId,
                        @RequestParam("ruleName") String ruleName,
                        @RequestParam(value = "patternName",required = false) String patternName,
                        @RequestParam(value = "priority",required = false) String priority,
                        @RequestParam(value = "kingdom",required = false) String kingdom,
                        @RequestParam(value = "fileName",required = false) String fileName,
                        @RequestParam(value = "functionName",required = false) String functionName){
        ARule rule = ruleService.getRule(ruleId);
        rule.setRuleName(ruleName);
        if (patternName == null) rule.setPatternName("");
        else rule.setPatternName(patternName);

        if (kingdom == null) rule.setKingdom("");
        else rule.setKingdom(kingdom);

        if (priority == null) rule.setPriority("");
        else rule.setPriority(priority);

        if (fileName == null) rule.setFileName("");
        else rule.setFileName(fileName);

        if (functionName == null) rule.setFunctionName("");
        else rule.setFunctionName(functionName);

        return ruleService.updateRule(rule);
    }

    @RequestMapping("/start/r/{ruleId}")
    public String startRule(@PathVariable("ruleId") String ruleId){
        ARule rule = ruleService.getRule(ruleId);
        rule.setState(1);
        ruleService.updateRule(rule);
        return "redirect:/view/rules";
    }

    @RequestMapping("/end/r/{ruleId}")
    public String endRule(@PathVariable("ruleId") String ruleId){
        ARule rule = ruleService.getRule(ruleId);
        rule.setState(0);
        ruleService.updateRule(rule);
        return "redirect:/view/rules";
    }
}
