package org.nju.demo.controller;

import org.nju.demo.entity.*;
import org.nju.demo.service.IssueService;
import org.nju.demo.service.PatternService;
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
    private IssueService issueService;

    @Autowired
    private PatternService patternService;

    @Autowired
    private HttpSession session;

    @ResponseBody
    @RequestMapping("/rules")
    public List<ARule> getRules(){
        AVersion version = (AVersion) session.getAttribute("version");
        return ruleService.getRules(version.getVersionId());
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
        AVersion version = (AVersion) session.getAttribute("version");
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

        rule.setVersionId(version.getVersionId());
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

    @ResponseBody
    @RequestMapping("/update/rule/{ruleId}/{flag}")
    public int updateRule(@PathVariable("ruleId") String ruleId,
                          @PathVariable("flag") int flag){
        ARule rule = ruleService.getRule(ruleId);
        rule.setState(flag);
        int f = flag == 1 ? 0 : 1;
        List<IssueBasic> issueList = issueService.getIssueList(rule.getVersionId(),"","","",f);
        for (IssueBasic issueBasic : issueList){
            PatternLk patternLk = patternService.getPatternByPatternName(rule.getPatternName());
            String patternId;
            if(patternLk != null) patternId = patternLk.getPatternLkId();
            else patternId = "";
            if(issueBasic.getPatternId().equals(patternId)||
               issueBasic.getPriority().equals(rule.getPriority())||
               issueBasic.getKingdom().equals(rule.getKingdom())||
               issueBasic.getFileName().equals(rule.getFileName())||
               issueBasic.getTargetFunction().equals(rule.getFunctionName())){
                issueBasic.setFlag(flag);
                issueService.updateIssue(issueBasic);
            }
        }
        return ruleService.updateRule(rule);
    }

}
