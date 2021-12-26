package org.nju.demo.entity;

public class AVersion {
    private String versionId;

    private String versionName;

    private String filePath;

    private String projectId;

    private String lastId;

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", versionId=").append(versionId);
        sb.append(", versionName=").append(versionName);
        sb.append(", filePath=").append(filePath);
        sb.append(", projectId=").append(projectId);
        sb.append(", lastId=").append(lastId);
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
        AVersion other = (AVersion) that;
        return (this.getVersionId() == null ? other.getVersionId() == null : this.getVersionId().equals(other.getVersionId()))
            && (this.getVersionName() == null ? other.getVersionName() == null : this.getVersionName().equals(other.getVersionName()))
            && (this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getLastId() == null ? other.getLastId() == null : this.getLastId().equals(other.getLastId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVersionId() == null) ? 0 : getVersionId().hashCode());
        result = prime * result + ((getVersionName() == null) ? 0 : getVersionName().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getLastId() == null) ? 0 : getLastId().hashCode());
        return result;
    }
}