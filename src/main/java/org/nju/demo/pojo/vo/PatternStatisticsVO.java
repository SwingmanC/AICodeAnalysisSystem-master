package org.nju.demo.pojo.vo;

public class PatternStatisticsVO {
    private String patternName;
    private int preIssueNum;
    private int nowIssueNum;

    public int getPreIssueNum() {
        return preIssueNum;
    }

    public void setPreIssueNum(int preIssueNum) {
        this.preIssueNum = preIssueNum;
    }

    public int getNowIssueNum() {
        return nowIssueNum;
    }

    public void setNowIssueNum(int nowIssueNum) {
        this.nowIssueNum = nowIssueNum;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }
}
