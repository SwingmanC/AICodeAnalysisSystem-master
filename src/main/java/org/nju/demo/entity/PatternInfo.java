package org.nju.demo.entity;

public class PatternInfo {
    private String patternInfoId;

    private String patternLkId;

    public String getPatternInfoId() {
        return patternInfoId;
    }

    public void setPatternInfoId(String patternInfoId) {
        this.patternInfoId = patternInfoId;
    }

    public String getPatternLkId() {
        return patternLkId;
    }

    public void setPatternLkId(String patternLkId) {
        this.patternLkId = patternLkId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", patternInfoId=").append(patternInfoId);
        sb.append(", patternLkId=").append(patternLkId);
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
        return (this.getPatternInfoId() == null ? other.getPatternInfoId() == null : this.getPatternInfoId().equals(other.getPatternInfoId()))
            && (this.getPatternLkId() == null ? other.getPatternLkId() == null : this.getPatternLkId().equals(other.getPatternLkId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPatternInfoId() == null) ? 0 : getPatternInfoId().hashCode());
        result = prime * result + ((getPatternLkId() == null) ? 0 : getPatternLkId().hashCode());
        return result;
    }
}