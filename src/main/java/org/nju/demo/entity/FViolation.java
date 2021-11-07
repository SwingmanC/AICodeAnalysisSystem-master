package org.nju.demo.entity;

public class FViolation {
    private Integer id;

    private Integer versionId;

    private String type;

    private String category;

    private Integer priority;

    private String classname;

    private String sourcePath;

    private String methodName;

    private String signature;

    private Integer startLine;

    private Integer endLine;

    private String state;

    public FViolation(){
        this.state = "未必对";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getStartLine() {
        return startLine;
    }

    public void setStartLine(Integer startLine) {
        this.startLine = startLine;
    }

    public Integer getEndLine() {
        return endLine;
    }

    public void setEndLine(Integer endLine) {
        this.endLine = endLine;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", versionId=").append(versionId);
        sb.append(", type=").append(type);
        sb.append(", category=").append(category);
        sb.append(", priority=").append(priority);
        sb.append(", classname=").append(classname);
        sb.append(", sourcePath=").append(sourcePath);
        sb.append(", methodName=").append(methodName);
        sb.append(", signature=").append(signature);
        sb.append(", startLine=").append(startLine);
        sb.append(", endLine=").append(endLine);
        sb.append(", state=").append(state);
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
        FViolation other = (FViolation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVersionId() == null ? other.getVersionId() == null : this.getVersionId().equals(other.getVersionId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
            && (this.getClassname() == null ? other.getClassname() == null : this.getClassname().equals(other.getClassname()))
            && (this.getSourcePath() == null ? other.getSourcePath() == null : this.getSourcePath().equals(other.getSourcePath()))
            && (this.getMethodName() == null ? other.getMethodName() == null : this.getMethodName().equals(other.getMethodName()))
            && (this.getSignature() == null ? other.getSignature() == null : this.getSignature().equals(other.getSignature()))
            && (this.getStartLine() == null ? other.getStartLine() == null : this.getStartLine().equals(other.getStartLine()))
            && (this.getEndLine() == null ? other.getEndLine() == null : this.getEndLine().equals(other.getEndLine()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVersionId() == null) ? 0 : getVersionId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getClassname() == null) ? 0 : getClassname().hashCode());
        result = prime * result + ((getSourcePath() == null) ? 0 : getSourcePath().hashCode());
        result = prime * result + ((getMethodName() == null) ? 0 : getMethodName().hashCode());
        result = prime * result + ((getSignature() == null) ? 0 : getSignature().hashCode());
        result = prime * result + ((getStartLine() == null) ? 0 : getStartLine().hashCode());
        result = prime * result + ((getEndLine() == null) ? 0 : getEndLine().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }
}