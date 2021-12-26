package org.nju.demo.entity;

public class IssueSource {
    private String issueSourceId;

    private String issueBasicId;

    private String fileName;

    private String filePath;

    private Integer startLine;

    private String targetFunction;

    private String snippet;

    public String getIssueSourceId() {
        return issueSourceId;
    }

    public void setIssueSourceId(String issueSourceId) {
        this.issueSourceId = issueSourceId;
    }

    public String getIssueBasicId() {
        return issueBasicId;
    }

    public void setIssueBasicId(String issueBasicId) {
        this.issueBasicId = issueBasicId;
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
        sb.append(", issueSourceId=").append(issueSourceId);
        sb.append(", issueBasicId=").append(issueBasicId);
        sb.append(", fileName=").append(fileName);
        sb.append(", filePath=").append(filePath);
        sb.append(", startLine=").append(startLine);
        sb.append(", targetFunction=").append(targetFunction);
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
        IssueSource other = (IssueSource) that;
        return (this.getIssueSourceId() == null ? other.getIssueSourceId() == null : this.getIssueSourceId().equals(other.getIssueSourceId()))
            && (this.getIssueBasicId() == null ? other.getIssueBasicId() == null : this.getIssueBasicId().equals(other.getIssueBasicId()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath()))
            && (this.getStartLine() == null ? other.getStartLine() == null : this.getStartLine().equals(other.getStartLine()))
            && (this.getTargetFunction() == null ? other.getTargetFunction() == null : this.getTargetFunction().equals(other.getTargetFunction()))
            && (this.getSnippet() == null ? other.getSnippet() == null : this.getSnippet().equals(other.getSnippet()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIssueSourceId() == null) ? 0 : getIssueSourceId().hashCode());
        result = prime * result + ((getIssueBasicId() == null) ? 0 : getIssueBasicId().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        result = prime * result + ((getStartLine() == null) ? 0 : getStartLine().hashCode());
        result = prime * result + ((getTargetFunction() == null) ? 0 : getTargetFunction().hashCode());
        result = prime * result + ((getSnippet() == null) ? 0 : getSnippet().hashCode());
        return result;
    }
}