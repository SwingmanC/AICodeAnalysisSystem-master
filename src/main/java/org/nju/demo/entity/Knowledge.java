package org.nju.demo.entity;

import java.util.Date;

public class Knowledge {
    private String knowledgeId;

    private String knowledgeName;

    private Date createTime;

    private String patternId;

    private String content;

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPatternId() {
        return patternId;
    }

    public void setPatternId(String patternId) {
        this.patternId = patternId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", knowledgeId=").append(knowledgeId);
        sb.append(", knowledgeName=").append(knowledgeName);
        sb.append(", createTime=").append(createTime);
        sb.append(", patternId=").append(patternId);
        sb.append(", content=").append(content);
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
        Knowledge other = (Knowledge) that;
        return (this.getKnowledgeId() == null ? other.getKnowledgeId() == null : this.getKnowledgeId().equals(other.getKnowledgeId()))
            && (this.getKnowledgeName() == null ? other.getKnowledgeName() == null : this.getKnowledgeName().equals(other.getKnowledgeName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getPatternId() == null ? other.getPatternId() == null : this.getPatternId().equals(other.getPatternId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getKnowledgeId() == null) ? 0 : getKnowledgeId().hashCode());
        result = prime * result + ((getKnowledgeName() == null) ? 0 : getKnowledgeName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getPatternId() == null) ? 0 : getPatternId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }
}