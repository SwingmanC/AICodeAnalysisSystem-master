package org.nju.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class PatternInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PatternInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPatternInfoIdIsNull() {
            addCriterion("pattern_info_id is null");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdIsNotNull() {
            addCriterion("pattern_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdEqualTo(String value) {
            addCriterion("pattern_info_id =", value, "patternInfoId");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdNotEqualTo(String value) {
            addCriterion("pattern_info_id <>", value, "patternInfoId");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdGreaterThan(String value) {
            addCriterion("pattern_info_id >", value, "patternInfoId");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("pattern_info_id >=", value, "patternInfoId");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdLessThan(String value) {
            addCriterion("pattern_info_id <", value, "patternInfoId");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdLessThanOrEqualTo(String value) {
            addCriterion("pattern_info_id <=", value, "patternInfoId");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdLike(String value) {
            addCriterion("pattern_info_id like", value, "patternInfoId");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdNotLike(String value) {
            addCriterion("pattern_info_id not like", value, "patternInfoId");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdIn(List<String> values) {
            addCriterion("pattern_info_id in", values, "patternInfoId");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdNotIn(List<String> values) {
            addCriterion("pattern_info_id not in", values, "patternInfoId");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdBetween(String value1, String value2) {
            addCriterion("pattern_info_id between", value1, value2, "patternInfoId");
            return (Criteria) this;
        }

        public Criteria andPatternInfoIdNotBetween(String value1, String value2) {
            addCriterion("pattern_info_id not between", value1, value2, "patternInfoId");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdIsNull() {
            addCriterion("pattern_lk_id is null");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdIsNotNull() {
            addCriterion("pattern_lk_id is not null");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdEqualTo(String value) {
            addCriterion("pattern_lk_id =", value, "patternLkId");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdNotEqualTo(String value) {
            addCriterion("pattern_lk_id <>", value, "patternLkId");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdGreaterThan(String value) {
            addCriterion("pattern_lk_id >", value, "patternLkId");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdGreaterThanOrEqualTo(String value) {
            addCriterion("pattern_lk_id >=", value, "patternLkId");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdLessThan(String value) {
            addCriterion("pattern_lk_id <", value, "patternLkId");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdLessThanOrEqualTo(String value) {
            addCriterion("pattern_lk_id <=", value, "patternLkId");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdLike(String value) {
            addCriterion("pattern_lk_id like", value, "patternLkId");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdNotLike(String value) {
            addCriterion("pattern_lk_id not like", value, "patternLkId");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdIn(List<String> values) {
            addCriterion("pattern_lk_id in", values, "patternLkId");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdNotIn(List<String> values) {
            addCriterion("pattern_lk_id not in", values, "patternLkId");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdBetween(String value1, String value2) {
            addCriterion("pattern_lk_id between", value1, value2, "patternLkId");
            return (Criteria) this;
        }

        public Criteria andPatternLkIdNotBetween(String value1, String value2) {
            addCriterion("pattern_lk_id not between", value1, value2, "patternLkId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}