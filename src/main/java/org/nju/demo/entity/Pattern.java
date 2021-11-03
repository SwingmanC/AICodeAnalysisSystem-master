package org.nju.demo.entity;

public class Pattern {
    private Integer id;

    private String patternName;

    private Integer categoryId;

    private Double likelihood;

    private Double variance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Double getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(Double likelihood) {
        this.likelihood = likelihood;
    }

    public Double getVariance() {
        return variance;
    }

    public void setVariance(Double variance) {
        this.variance = variance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", patternName=").append(patternName);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", likelihood=").append(likelihood);
        sb.append(", variance=").append(variance);
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
        Pattern other = (Pattern) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPatternName() == null ? other.getPatternName() == null : this.getPatternName().equals(other.getPatternName()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getLikelihood() == null ? other.getLikelihood() == null : this.getLikelihood().equals(other.getLikelihood()))
            && (this.getVariance() == null ? other.getVariance() == null : this.getVariance().equals(other.getVariance()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPatternName() == null) ? 0 : getPatternName().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getLikelihood() == null) ? 0 : getLikelihood().hashCode());
        result = prime * result + ((getVariance() == null) ? 0 : getVariance().hashCode());
        return result;
    }
}