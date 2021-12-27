package org.nju.demo.pojo.vo;

import java.util.List;

public class PatternDocVO {
    private String patternName;
    private String explanation;
    private String recommendation;
    private String tip;
    private double likelihood;
    private List<IssueDocVO> issueDocVOList;

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(double likelihood) {
        this.likelihood = likelihood;
    }

    public List<IssueDocVO> getIssueDocVOList() {
        return issueDocVOList;
    }

    public void setIssueDocVOList(List<IssueDocVO> issueDocVOList) {
        this.issueDocVOList = issueDocVOList;
    }
}
