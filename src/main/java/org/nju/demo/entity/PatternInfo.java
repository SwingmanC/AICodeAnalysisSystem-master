package org.nju.demo.entity;

public class PatternInfo {
    private Integer id;

    private String patternId;

    private String patternName;

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

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", patternId=").append(patternId);
        sb.append(", patternName=").append(patternName);
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
        PatternInfo other = (PatternInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPatternId() == null ? other.getPatternId() == null : this.getPatternId().equals(other.getPatternId()))
            && (this.getPatternName() == null ? other.getPatternName() == null : this.getPatternName().equals(other.getPatternName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPatternId() == null) ? 0 : getPatternId().hashCode());
        result = prime * result + ((getPatternName() == null) ? 0 : getPatternName().hashCode());
        return result;
    }
}