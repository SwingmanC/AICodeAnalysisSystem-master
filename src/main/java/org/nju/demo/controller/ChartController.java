package org.nju.demo.controller;

import org.nju.demo.constant.Type;
import org.nju.demo.entity.AVersion;
import org.nju.demo.entity.FViolation;
import org.nju.demo.service.FViolationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
    private FViolationService fViolationService;

    @GetMapping("/view/violations/charts")
    public String viewViolationCharts(){
        return "violation_charts";
    }

    @GetMapping("/view/violations/list")
    public String viewViolationList(){
        return "violation_list";
    }

    @ResponseBody
    @GetMapping("/f_violations/type")
    public String getFViolationsType(){
        AVersion version = (AVersion) session.getAttribute("version");
        List<FViolation> violationList = fViolationService.getFViolationsByVersionId(version.getId());
        int[] count = {0,0,0,0,0,0,0,0,0};
        for(FViolation violation:violationList){
            String category = violation.getCategory();
            for(int i=0;i< Type.findBugs.length;++i){
                if (category.equals(Type.findBugs[i])){
                    count[i]++;
                    break;
                }
            }
        }
        List<Map> outputs = new ArrayList<>();
        for(int i=0;i<count.length;++i){
            Map output = new TreeMap();
            output.put("value",count[i]);
            output.put("name",Type.findBugs[i]);
            outputs.add(output);
        }
        JSONArray jsonArray = JSONArray.fromObject(outputs);
        return jsonArray.toString();
    }

    @ResponseBody
    @GetMapping("/f_violations/priority")
    public int[] getFViolationsPriority(){
        AVersion version = (AVersion) session.getAttribute("version");
        List<FViolation> violationList = fViolationService.getFViolationsByVersionId(version.getId());
        int[] count = {0,0,0,0};
        for(FViolation violation:violationList){
            int p = violation.getPriority();
            if (p>=4) count[3]++;
            else count[p-1]++;
        }
        return count;
    }
}
