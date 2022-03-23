package org.nju.demo.pojo.vo;

import java.util.List;

public class ProblemItem {
    private String patternName;
    private List<IssueItem> issueItemList;
    private int issueNum;

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public List<IssueItem> getIssueItemList() {
        return issueItemList;
    }

    public void setIssueItemList(List<IssueItem> issueItemList) {
        this.issueItemList = issueItemList;
    }

    public int getIssueNum() {
        return issueNum;
    }

    public void setIssueNum() {
        this.issueNum = this.issueItemList.size();
    }
}
