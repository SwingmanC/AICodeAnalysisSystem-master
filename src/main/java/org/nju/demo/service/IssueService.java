package org.nju.demo.service;

import org.nju.demo.entity.IssueBasic;
import org.nju.demo.entity.IssueSource;
import org.nju.demo.entity.VersionPatternRel;

import java.util.List;

public interface IssueService {

    List<IssueBasic> getIssueList(String versionId,String priority,String kingdom,String state,int flag);

    List<IssueBasic> getIssueListByPatternId(String versionId,String patternId);

    IssueBasic getIssue(String issueId);

    IssueSource getSourceInfo(String issueId);

    int countTrueIssueByPriority(String versionId,String priority);

    int countTrueIssueByPattern(String versionId,String patternId);

    int addIssue(IssueBasic issueBasic);

    int addSourceInfo(IssueSource issueSource);

    int addRelation(VersionPatternRel versionPatternRel);

    int updateIssue(IssueBasic issueBasic);

    void compare(List<IssueBasic> lastIssueList,List<IssueBasic> issueBasicList);
}