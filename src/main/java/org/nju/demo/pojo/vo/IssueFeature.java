package org.nju.demo.pojo.vo;

public class IssueFeature {
    private String issueId;
    private String projectName;
    private String versionName;
    private int priority;
    private int kingdom;
    private int issueNumMethod;
    private int issueNumClass;
    private int startLine;
    private int lineLength;
    private double patternLikelihood;
    private int forCount;
    private int label;

    public IssueFeature(){
        this.label = -1;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getKingdom() {
        return kingdom;
    }

    public void setKingdom(int kingdom) {
        this.kingdom = kingdom;
    }

    public int getIssueNumMethod() {
        return issueNumMethod;
    }

    public void setIssueNumMethod(int issueNumMethod) {
        this.issueNumMethod = issueNumMethod;
    }

    public int getIssueNumClass() {
        return issueNumClass;
    }

    public void setIssueNumClass(int issueNumClass) {
        this.issueNumClass = issueNumClass;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public double getPatternLikelihood() {
        return patternLikelihood;
    }

    public void setPatternLikelihood(double patternLikelihood) {
        this.patternLikelihood = patternLikelihood;
    }

    public int getForCount() {
        return forCount;
    }

    public void setForCount(int forCount) {
        this.forCount = forCount;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
}
