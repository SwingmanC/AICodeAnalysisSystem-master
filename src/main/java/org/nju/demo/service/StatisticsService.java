package org.nju.demo.service;

import org.nju.demo.entity.PatternStatistics;
import org.nju.demo.entity.PriorityStatistics;
import org.nju.demo.entity.VersionPatternRel;
import org.nju.demo.pojo.dto.IssueInfoDTO;
import org.nju.demo.pojo.dto.PatternInfoDTO;
import org.nju.demo.pojo.dto.PatternStatisticsDTO;

import java.util.List;
import java.util.Map;

public interface StatisticsService {

    List<PatternStatisticsDTO> getPatternStatisticsByVersionId(String versionId);

    PriorityStatistics getPriorityStatisticsByVersionId(String versionId);

    int addPatternStatistics(PatternStatistics patternStatistics);

    int addPriorityStatistics(PriorityStatistics priorityStatistics);

    Map<String,Integer> countIssueByPattern(List<IssueInfoDTO> issueInfoList);

    PriorityStatistics countIssueByPriority(List<IssueInfoDTO> issueInfoList);
}
