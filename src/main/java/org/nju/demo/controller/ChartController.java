package org.nju.demo.controller;

import org.nju.demo.constant.Type;
import org.nju.demo.entity.AVersion;
import net.sf.json.JSONArray;
import org.nju.demo.entity.IssueBasic;
import org.nju.demo.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class ChartController {

    @Autowired
    private HttpSession session;

    @Autowired
    private IssueService issueService;

    @GetMapping("/view/issues/charts")
    public String viewViolationCharts(){
        return "violation_charts";
    }

    @GetMapping("/view/issues/list")
    public String viewViolationList(){
        return "violation_list";
    }

    @ResponseBody
    @GetMapping("/issues/pattern")
    public int[] getIssuesByPattern(){
        AVersion version = (AVersion) session.getAttribute("version");
        List<IssueBasic> issueList = issueService.getIssueList(version.getId(),"","",0);
        int[] count = {0,0,0,0,0,0,0,0};
        for(IssueBasic issueBasic:issueList){
            String kingdom = issueBasic.getKingdom();
            for(int i=0;i<Type.fortify.length;++i){
                if (kingdom.equals(Type.fortify[i])){
                    count[i]++;
                    break;
                }
            }
        }
        return count;
    }

    @ResponseBody
    @GetMapping("/issues/priority")
    public int[] getIssuesByPriority(){
        AVersion version = (AVersion) session.getAttribute("version");
        List<IssueBasic> issueList = issueService.getIssueList(version.getId(),"","",0);
        int[] count = {0,0,0,0};
        for(IssueBasic issueBasic:issueList){
            String p = issueBasic.getPriority();
            if (p.equals("Low")) count[0]++;
            else if (p.equals("Medium")) count[1]++;
            else if (p.equals("High")) count[2]++;
            else count[3]++;
        }
        return count;
    }
}
