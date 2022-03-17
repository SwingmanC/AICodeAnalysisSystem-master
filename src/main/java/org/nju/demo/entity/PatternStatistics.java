package org.nju.demo.entity;

public class PatternStatistics {
    private Integer id;

    private Integer issueNum;

    private Integer vPId;

    public PatternStatistics(){
        this.issueNum = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(Integer issueNum) {
        this.issueNum = issueNum;
    }

    public Integer getvPId() {
        return vPId;
    }

    public void setvPId(Integer vPId) {
        this.vPId = vPId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", issueNum=").append(issueNum);
        sb.append(", vPId=").append(vPId);
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
        PatternStatistics other = (PatternStatistics) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIssueNum() == null ? other.getIssueNum() == null : this.getIssueNum().equals(other.getIssueNum()))
            && (this.getvPId() == null ? other.getvPId() == null : this.getvPId().equals(other.getvPId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIssueNum() == null) ? 0 : getIssueNum().hashCode());
        result = prime * result + ((getvPId() == null) ? 0 : getvPId().hashCode());
        return result;
    }
}