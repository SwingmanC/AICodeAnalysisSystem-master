package org.nju.demo.entity;

public class PriorityStatistics {
    private Integer id;

    private Integer lowNum;

    private Integer mediumNum;

    private Integer highNum;

    private Integer criticalNum;

    private String versionId;

    public PriorityStatistics(){
        this.lowNum = 0;
        this.mediumNum = 0;
        this.highNum = 0;
        this.criticalNum = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLowNum() {
        return lowNum;
    }

    public void setLowNum(Integer lowNum) {
        this.lowNum = lowNum;
    }

    public Integer getMediumNum() {
        return mediumNum;
    }

    public void setMediumNum(Integer mediumNum) {
        this.mediumNum = mediumNum;
    }

    public Integer getHighNum() {
        return highNum;
    }

    public void setHighNum(Integer highNum) {
        this.highNum = highNum;
    }

    public Integer getCriticalNum() {
        return criticalNum;
    }

    public void setCriticalNum(Integer criticalNum) {
        this.criticalNum = criticalNum;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", lowNum=").append(lowNum);
        sb.append(", mediumNum=").append(mediumNum);
        sb.append(", highNum=").append(highNum);
        sb.append(", criticalNum=").append(criticalNum);
        sb.append(", versionId=").append(versionId);
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
        PriorityStatistics other = (PriorityStatistics) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLowNum() == null ? other.getLowNum() == null : this.getLowNum().equals(other.getLowNum()))
            && (this.getMediumNum() == null ? other.getMediumNum() == null : this.getMediumNum().equals(other.getMediumNum()))
            && (this.getHighNum() == null ? other.getHighNum() == null : this.getHighNum().equals(other.getHighNum()))
            && (this.getCriticalNum() == null ? other.getCriticalNum() == null : this.getCriticalNum().equals(other.getCriticalNum()))
            && (this.getVersionId() == null ? other.getVersionId() == null : this.getVersionId().equals(other.getVersionId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLowNum() == null) ? 0 : getLowNum().hashCode());
        result = prime * result + ((getMediumNum() == null) ? 0 : getMediumNum().hashCode());
        result = prime * result + ((getHighNum() == null) ? 0 : getHighNum().hashCode());
        result = prime * result + ((getCriticalNum() == null) ? 0 : getCriticalNum().hashCode());
        result = prime * result + ((getVersionId() == null) ? 0 : getVersionId().hashCode());
        return result;
    }
}