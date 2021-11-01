package org.nju.demo.service;

import org.nju.demo.entity.FIssue;
import org.nju.demo.entity.FIssueWithBLOBs;

import java.util.List;

public interface FIssueService {

    long countByReportId(int reportId);

    List<FIssue> getIssueByReportId(int reportId);

    FIssueWithBLOBs getIssueByIssueId(int issueId);

    int addIssue(FIssueWithBLOBs issue);

}