package org.nju.demo.service.impl;

import org.nju.demo.constant.Constant;
import org.nju.demo.dao.PatternStatisticsMapper;
import org.nju.demo.dao.PriorityStatisticsMapper;
import org.nju.demo.entity.PatternStatistics;
import org.nju.demo.entity.PriorityStatistics;
import org.nju.demo.entity.PriorityStatisticsExample;
import org.nju.demo.pojo.dto.IssueInfoDTO;
import org.nju.demo.pojo.dto.PatternInfoDTO;
import org.nju.demo.pojo.dto.PatternStatisticsDTO;
import org.nju.demo.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private PatternStatisticsMapper patternStatisticsMapper;

    @Autowired
    private PriorityStatisticsMapper priorityStatisticsMapper;

    @Override
    public List<PatternStatisticsDTO> getPatternStatisticsByVersionId(String versionId) {
        return patternStatisticsMapper.selectPatternStatisticsByVersionId(versionId);
    }

    @Override
    public PriorityStatistics getPriorityStatisticsByVersionId(String versionId) {
        PriorityStatisticsExample example = new PriorityStatisticsExample();
        PriorityStatisticsExample.Criteria criteria = example.createCriteria();

        criteria.andVersionIdEqualTo(versionId);

        return priorityStatisticsMapper.selectByExample(example).get(0);
    }

    @Override
    public int addPatternStatistics(PatternStatistics patternStatistics) {
        return patternStatisticsMapper.insert(patternStatistics);
    }

    @Override
    public int addPriorityStatistics(PriorityStatistics priorityStatistics) {
        return priorityStatisticsMapper.insert(priorityStatistics);
    }

    @Override
    public Map<String, Integer> countIssueByPattern(List<IssueInfoDTO> issueInfoList) {
        HashMap<String,Integer> hm = new HashMap<>();
        for(IssueInfoDTO issueInfo : issueInfoList) hm.put(issueInfo.getPatternName(),hm.getOrDefault(issueInfo.getPatternName(),0)+1);
        return hm;
    }

    @Override
    public PriorityStatistics countIssueByPriority(List<IssueInfoDTO> issueInfoList) {
        PriorityStatistics priorityStatistics = new PriorityStatistics();
        for(IssueInfoDTO issueInfo : issueInfoList){
            if (issueInfo.getPriority().equals(Constant.Priority.LOW)) priorityStatistics.setLowNum(priorityStatistics.getLowNum()+1);
            else if (issueInfo.getPriority().equals(Constant.Priority.MEDIUM)) priorityStatistics.setMediumNum(priorityStatistics.getMediumNum()+1);
            else if (issueInfo.getPriority().equals(Constant.Priority.HIGH)) priorityStatistics.setHighNum(priorityStatistics.getHighNum()+1);
            else priorityStatistics.setCriticalNum(priorityStatistics.getCriticalNum()+1);
        }
        return priorityStatistics;
    }
}
