package org.nju.demo.entity;

public class PatternInfoWithBLOBs extends PatternInfo {
    private String explanation;

    private String recommendation;

    private String tip;

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", explanation=").append(explanation);
        sb.append(", recommendation=").append(recommendation);
        sb.append(", tip=").append(tip);
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
        PatternInfoWithBLOBs other = (PatternInfoWithBLOBs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPatternId() == null ? other.getPatternId() == null : this.getPatternId().equals(other.getPatternId()))
            && (this.getPatternName() == null ? other.getPatternName() == null : this.getPatternName().equals(other.getPatternName()))
            && (this.getExplanation() == null ? other.getExplanation() == null : this.getExplanation().equals(other.getExplanation()))
            && (this.getRecommendation() == null ? other.getRecommendation() == null : this.getRecommendation().equals(other.getRecommendation()))
            && (this.getTip() == null ? other.getTip() == null : this.getTip().equals(other.getTip()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPatternId() == null) ? 0 : getPatternId().hashCode());
        result = prime * result + ((getPatternName() == null) ? 0 : getPatternName().hashCode());
        result = prime * result + ((getExplanation() == null) ? 0 : getExplanation().hashCode());
        result = prime * result + ((getRecommendation() == null) ? 0 : getRecommendation().hashCode());
        result = prime * result + ((getTip() == null) ? 0 : getTip().hashCode());
        return result;
    }
}