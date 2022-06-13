package org.nju.demo.controller;

import org.nju.demo.config.Constants;
import org.nju.demo.entity.AVersion;
import org.nju.demo.entity.IssueBasic;
import org.nju.demo.entity.PriorityStatistics;
import org.nju.demo.pojo.dto.PatternStatisticsDTO;
import org.nju.demo.pojo.vo.PatternStatisticsVO;
import org.nju.demo.service.IssueService;
import org.nju.demo.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChartController {

    @Autowired
    private HttpSession session;

    @Autowired
    private IssueService issueService;

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/view/issues/charts")
    public String viewViolationCharts(){
        return "issue_charts";
    }

    @GetMapping("/view/issues/list")
    public String viewViolationList(){
        return "issue_list";
    }

    @ResponseBody
    @GetMapping("/issues/category")
    public int[] countIssuesByCategory(){
        AVersion version = (AVersion) session.getAttribute("version");
        List<IssueBasic> issueList = issueService.getIssueList(version.getVersionId(),"","","",Constants.IsFilter.IGNORE);
        int[] count = {0,0,0,0,0,0,0,0};
        for(IssueBasic issueBasic:issueList){
            String kingdom = issueBasic.getKingdom();
            for(int i = 0; i< Constants.Type.fortify.length; ++i){
                if (kingdom.equals(Constants.Type.fortify[i])){
                    count[i]++;
                    break;
                }
            }
        }
        return count;
    }

    @ResponseBody
    @GetMapping("/issues/priority")
    public int[][] countIssuesByPriority(){
        AVersion version = (AVersion) session.getAttribute("version");
        List<IssueBasic> issueList = issueService.getIssueList(version.getVersionId(),"","", Constants.IssueState.TRUE, Constants.IsFilter.IGNORE);
        int[][] res = new int[2][4];
        int[] initCounts = new int[4];
        int[] nowCounts = {0,0,0,0};
        for(IssueBasic issueBasic:issueList){
            String p = issueBasic.getPriority();
            if (p.equals(Constants.Priority.LOW)) nowCounts[0]++;
            else if (p.equals(Constants.Priority.MEDIUM)) nowCounts[1]++;
            else if (p.equals(Constants.Priority.HIGH)) nowCounts[2]++;
            else nowCounts[3]++;
        }
        PriorityStatistics priorityStatistics = statisticsService.getPriorityStatisticsByVersionId(version.getVersionId());
        initCounts[0] = priorityStatistics.getLowNum();
        initCounts[1] = priorityStatistics.getMediumNum();
        initCounts[2] = priorityStatistics.getHighNum();
        initCounts[3] = priorityStatistics.getCriticalNum();
        res[0]=initCounts;
        res[1]=nowCounts;
        return res;
    }

    @ResponseBody
    @GetMapping("/issues/pattern")
    public List<PatternStatisticsVO> countIssuesByPattern(){
        AVersion version = (AVersion) session.getAttribute("version");
        List<PatternStatisticsVO> patternStatisticsVOList = new ArrayList<>();
        List<PatternStatisticsDTO> patternStatisticsDTOList = statisticsService.getPatternStatisticsByVersionId(version.getVersionId());
        for(PatternStatisticsDTO data : patternStatisticsDTOList){
            PatternStatisticsVO patternStatisticsVO = new PatternStatisticsVO();
            patternStatisticsVO.setPatternName(data.getPatternName());
            patternStatisticsVO.setPreIssueNum(data.getIssueNum());
            int nowNum = issueService.countTrueIssueByPattern(data.getVersionId(),data.getPatternId());
            patternStatisticsVO.setNowIssueNum(nowNum);
            patternStatisticsVOList.add(patternStatisticsVO);
        }
        return patternStatisticsVOList;
    }

}
