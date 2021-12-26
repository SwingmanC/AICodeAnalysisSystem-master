package org.nju.demo.entity;

public class PatternLk {
    private String patternLkId;

    private String patternName;

    private Integer tNum;

    private Integer fNum;

    public PatternLk(){
        this.tNum = 0;
        this.fNum = 0;
    }

    public String getPatternLkId() {
        return patternLkId;
    }

    public void setPatternLkId(String patternLkId) {
        this.patternLkId = patternLkId;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public Integer gettNum() {
        return tNum;
    }

    public void settNum(Integer tNum) {
        this.tNum = tNum;
    }

    public Integer getfNum() {
        return fNum;
    }

    public void setfNum(Integer fNum) {
        this.fNum = fNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", patternLkId=").append(patternLkId);
        sb.append(", patternName=").append(patternName);
        sb.append(", tNum=").append(tNum);
        sb.append(", fNum=").append(fNum);
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
        PatternLk other = (PatternLk) that;
        return (this.getPatternLkId() == null ? other.getPatternLkId() == null : this.getPatternLkId().equals(other.getPatternLkId()))
            && (this.getPatternName() == null ? other.getPatternName() == null : this.getPatternName().equals(other.getPatternName()))
            && (this.gettNum() == null ? other.gettNum() == null : this.gettNum().equals(other.gettNum()))
            && (this.getfNum() == null ? other.getfNum() == null : this.getfNum().equals(other.getfNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPatternLkId() == null) ? 0 : getPatternLkId().hashCode());
        result = prime * result + ((getPatternName() == null) ? 0 : getPatternName().hashCode());
        result = prime * result + ((gettNum() == null) ? 0 : gettNum().hashCode());
        result = prime * result + ((getfNum() == null) ? 0 : getfNum().hashCode());
        return result;
    }
}