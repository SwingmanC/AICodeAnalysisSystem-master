package org.nju.demo.service;

import org.nju.demo.entity.IssueBasic;
import org.nju.demo.entity.IssueSource;

import java.util.List;

public interface IssueService {

    List<IssueBasic> getIssueList(int versionId,String priority,String kingdom,int flag);

    List<IssueBasic> getIssueListByPatternId(int versionId,String patternId);

    IssueBasic getIssue(int id);

    IssueSource getSourceInfo(String issueId);

    int addIssue(IssueBasic issueBasic);

    int addSourceInfo(IssueSource issueSource);

    int updateIssue(IssueBasic issueBasic);

    void compare(List<IssueBasic> lastIssueList,List<IssueBasic> issueBasicList);
}