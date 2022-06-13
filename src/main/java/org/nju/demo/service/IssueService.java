package org.nju.demo.service;

import org.nju.demo.entity.IssueBasic;
import org.nju.demo.entity.IssueSource;
import org.nju.demo.entity.VersionPatternRel;
import org.nju.demo.pojo.dto.IssueDTO;
import org.nju.demo.pojo.vo.IssueFeature;
import org.nju.demo.pojo.vo.ProblemItem;

import java.util.List;

public interface IssueService {

    List<IssueBasic> getIssueList(String versionId,String priority,String patternId,String state,int flag);

    List<IssueBasic> getIssueList(String versionId,String state,int flag);

    List<IssueDTO> getIssueItemList(String versionId, String priority, int flag);

    List<IssueBasic> getIssueListByPatternId(String versionId,String patternId);

    List<IssueBasic> getClassifiedIssueList();

    IssueBasic getIssue(String issueId);

    IssueSource getSourceInfo(String issueId);

    int countTrueIssueByPriority(String versionId,String priority);

    int countTrueIssueByPattern(String versionId,String patternId);

    int addIssue(IssueBasic issueBasic);

    int addSourceInfo(IssueSource issueSource);

    int addRelation(VersionPatternRel versionPatternRel);

    int updateIssue(IssueBasic issueBasic);

    void compare(List<IssueBasic> lastIssueList,List<IssueBasic> issueBasicList);

    List<ProblemItem> getProblemItemList(List<IssueDTO> issueList);
}