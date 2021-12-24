package org.nju.demo.controller;

import org.nju.demo.entity.ARule;
import org.nju.demo.entity.AUser;
import org.nju.demo.service.RuleService;
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
    @RequestMapping("/rule/{id}")
    public ARule getRule(@PathVariable("id") int id){
        return ruleService.getRule(id);
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
    public int editRule(@RequestParam("id") int id,
                        @RequestParam("ruleName") String ruleName,
                        @RequestParam(value = "patternName",required = false) String patternName,
                        @RequestParam(value = "priority",required = false) String priority,
                        @RequestParam(value = "kingdom",required = false) String kingdom,
                        @RequestParam(value = "fileName",required = false) String fileName,
                        @RequestParam(value = "functionName",required = false) String functionName){
        ARule rule = ruleService.getRule(id);
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

    @RequestMapping("/start/r/{id}")
    public String startRule(@PathVariable("id") int id){
        ARule rule = ruleService.getRule(id);
        rule.setState(1);
        ruleService.updateRule(rule);
        return "redirect:/view/rules";
    }

    @RequestMapping("/end/r/{id}")
    public String endRule(@PathVariable("id") int id){
        ARule rule = ruleService.getRule(id);
        rule.setState(0);
        ruleService.updateRule(rule);
        return "redirect:/view/rules";
    }
}
