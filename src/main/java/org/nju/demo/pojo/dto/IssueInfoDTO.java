package org.nju.demo.pojo.dto;

public class IssueInfoDTO {
    private String patternName;

    private String priority;

    private String kingdom;

    private String fileName;

    private String filePath;

    private Integer startLine;

    private String targetFunction;

    private String description;

    private String snippet;

    private IssueSourceDTO issueSourceDTO;

    public IssueInfoDTO(){
        this.issueSourceDTO = null;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getStartLine() {
        return startLine;
    }

    public void setStartLine(Integer startLine) {
        this.startLine = startLine;
    }

    public String getTargetFunction() {
        return targetFunction;
    }

    public void setTargetFunction(String targetFunction) {
        this.targetFunction = targetFunction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public IssueSourceDTO getIssueSourceDTO() {
        return issueSourceDTO;
    }

    public void setIssueSourceDTO(IssueSourceDTO issueSourceDTO) {
        this.issueSourceDTO = issueSourceDTO;
    }
}
