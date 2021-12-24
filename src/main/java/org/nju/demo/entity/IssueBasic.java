package org.nju.demo.entity;

public class IssueBasic {
    private Integer id;

    private String patternId;

    private String issueId;

    private String priority;

    private String kingdom;

    private String fileName;

    private String filePath;

    private Integer startLine;

    private String targetFunction;

    private String description;

    private Integer versionId;

    private String state;

    private String snippet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatternId() {
        return patternId;
    }

    public void setPatternId(String patternId) {
        this.patternId = patternId;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
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

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", patternId=").append(patternId);
        sb.append(", issueId=").append(issueId);
        sb.append(", priority=").append(priority);
        sb.append(", kingdom=").append(kingdom);
        sb.append(", fileName=").append(fileName);
        sb.append(", filePath=").append(filePath);
        sb.append(", startLine=").append(startLine);
        sb.append(", targetFunction=").append(targetFunction);
        sb.append(", description=").append(description);
        sb.append(", versionId=").append(versionId);
        sb.append(", state=").append(state);
        sb.append(", snippet=").append(snippet);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        IssueBasic other = (IssueBasic) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPatternId() == null ? other.getPatternId() == null : this.getPatternId().equals(other.getPatternId()))
            && (this.getIssueId() == null ? other.getIssueId() == null : this.getIssueId().equals(other.getIssueId()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
            && (this.getKingdom() == null ? other.getKingdom() == null : this.getKingdom().equals(other.getKingdom()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath()))
            && (this.getStartLine() == null ? other.getStartLine() == null : this.getStartLine().equals(other.getStartLine()))
            && (this.getTargetFunction() == null ? other.getTargetFunction() == null : this.getTargetFunction().equals(other.getTargetFunction()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getVersionId() == null ? other.getVersionId() == null : this.getVersionId().equals(other.getVersionId()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getSnippet() == null ? other.getSnippet() == null : this.getSnippet().equals(other.getSnippet()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPatternId() == null) ? 0 : getPatternId().hashCode());
        result = prime * result + ((getIssueId() == null) ? 0 : getIssueId().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getKingdom() == null) ? 0 : getKingdom().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        result = prime * result + ((getStartLine() == null) ? 0 : getStartLine().hashCode());
        result = prime * result + ((getTargetFunction() == null) ? 0 : getTargetFunction().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getVersionId() == null) ? 0 : getVersionId().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getSnippet() == null) ? 0 : getSnippet().hashCode());
        return result;
    }
}