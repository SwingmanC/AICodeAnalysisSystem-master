package org.nju.demo.entity;

public class PatternLk {
    private Integer id;

    private String patternId;

    private Integer tNum;

    private Integer fNum;

    public PatternLk(){
        tNum = 0;
        fNum = 0;
    }

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
        sb.append(", id=").append(id);
        sb.append(", patternId=").append(patternId);
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
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPatternId() == null ? other.getPatternId() == null : this.getPatternId().equals(other.getPatternId()))
            && (this.gettNum() == null ? other.gettNum() == null : this.gettNum().equals(other.gettNum()))
            && (this.getfNum() == null ? other.getfNum() == null : this.getfNum().equals(other.getfNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPatternId() == null) ? 0 : getPatternId().hashCode());
        result = prime * result + ((gettNum() == null) ? 0 : gettNum().hashCode());
        result = prime * result + ((getfNum() == null) ? 0 : getfNum().hashCode());
        return result;
    }
}