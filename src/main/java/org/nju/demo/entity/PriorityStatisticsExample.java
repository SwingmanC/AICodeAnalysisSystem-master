package org.nju.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class PriorityStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PriorityStatisticsExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLowNumIsNull() {
            addCriterion("low_num is null");
            return (Criteria) this;
        }

        public Criteria andLowNumIsNotNull() {
            addCriterion("low_num is not null");
            return (Criteria) this;
        }

        public Criteria andLowNumEqualTo(Integer value) {
            addCriterion("low_num =", value, "lowNum");
            return (Criteria) this;
        }

        public Criteria andLowNumNotEqualTo(Integer value) {
            addCriterion("low_num <>", value, "lowNum");
            return (Criteria) this;
        }

        public Criteria andLowNumGreaterThan(Integer value) {
            addCriterion("low_num >", value, "lowNum");
            return (Criteria) this;
        }

        public Criteria andLowNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("low_num >=", value, "lowNum");
            return (Criteria) this;
        }

        public Criteria andLowNumLessThan(Integer value) {
            addCriterion("low_num <", value, "lowNum");
            return (Criteria) this;
        }

        public Criteria andLowNumLessThanOrEqualTo(Integer value) {
            addCriterion("low_num <=", value, "lowNum");
            return (Criteria) this;
        }

        public Criteria andLowNumIn(List<Integer> values) {
            addCriterion("low_num in", values, "lowNum");
            return (Criteria) this;
        }

        public Criteria andLowNumNotIn(List<Integer> values) {
            addCriterion("low_num not in", values, "lowNum");
            return (Criteria) this;
        }

        public Criteria andLowNumBetween(Integer value1, Integer value2) {
            addCriterion("low_num between", value1, value2, "lowNum");
            return (Criteria) this;
        }

        public Criteria andLowNumNotBetween(Integer value1, Integer value2) {
            addCriterion("low_num not between", value1, value2, "lowNum");
            return (Criteria) this;
        }

        public Criteria andMediumNumIsNull() {
            addCriterion("medium_num is null");
            return (Criteria) this;
        }

        public Criteria andMediumNumIsNotNull() {
            addCriterion("medium_num is not null");
            return (Criteria) this;
        }

        public Criteria andMediumNumEqualTo(Integer value) {
            addCriterion("medium_num =", value, "mediumNum");
            return (Criteria) this;
        }

        public Criteria andMediumNumNotEqualTo(Integer value) {
            addCriterion("medium_num <>", value, "mediumNum");
            return (Criteria) this;
        }

        public Criteria andMediumNumGreaterThan(Integer value) {
            addCriterion("medium_num >", value, "mediumNum");
            return (Criteria) this;
        }

        public Criteria andMediumNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("medium_num >=", value, "mediumNum");
            return (Criteria) this;
        }

        public Criteria andMediumNumLessThan(Integer value) {
            addCriterion("medium_num <", value, "mediumNum");
            return (Criteria) this;
        }

        public Criteria andMediumNumLessThanOrEqualTo(Integer value) {
            addCriterion("medium_num <=", value, "mediumNum");
            return (Criteria) this;
        }

        public Criteria andMediumNumIn(List<Integer> values) {
            addCriterion("medium_num in", values, "mediumNum");
            return (Criteria) this;
        }

        public Criteria andMediumNumNotIn(List<Integer> values) {
            addCriterion("medium_num not in", values, "mediumNum");
            return (Criteria) this;
        }

        public Criteria andMediumNumBetween(Integer value1, Integer value2) {
            addCriterion("medium_num between", value1, value2, "mediumNum");
            return (Criteria) this;
        }

        public Criteria andMediumNumNotBetween(Integer value1, Integer value2) {
            addCriterion("medium_num not between", value1, value2, "mediumNum");
            return (Criteria) this;
        }

        public Criteria andHighNumIsNull() {
            addCriterion("high_num is null");
            return (Criteria) this;
        }

        public Criteria andHighNumIsNotNull() {
            addCriterion("high_num is not null");
            return (Criteria) this;
        }

        public Criteria andHighNumEqualTo(Integer value) {
            addCriterion("high_num =", value, "highNum");
            return (Criteria) this;
        }

        public Criteria andHighNumNotEqualTo(Integer value) {
            addCriterion("high_num <>", value, "highNum");
            return (Criteria) this;
        }

        public Criteria andHighNumGreaterThan(Integer value) {
            addCriterion("high_num >", value, "highNum");
            return (Criteria) this;
        }

        public Criteria andHighNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("high_num >=", value, "highNum");
            return (Criteria) this;
        }

        public Criteria andHighNumLessThan(Integer value) {
            addCriterion("high_num <", value, "highNum");
            return (Criteria) this;
        }

        public Criteria andHighNumLessThanOrEqualTo(Integer value) {
            addCriterion("high_num <=", value, "highNum");
            return (Criteria) this;
        }

        public Criteria andHighNumIn(List<Integer> values) {
            addCriterion("high_num in", values, "highNum");
            return (Criteria) this;
        }

        public Criteria andHighNumNotIn(List<Integer> values) {
            addCriterion("high_num not in", values, "highNum");
            return (Criteria) this;
        }

        public Criteria andHighNumBetween(Integer value1, Integer value2) {
            addCriterion("high_num between", value1, value2, "highNum");
            return (Criteria) this;
        }

        public Criteria andHighNumNotBetween(Integer value1, Integer value2) {
            addCriterion("high_num not between", value1, value2, "highNum");
            return (Criteria) this;
        }

        public Criteria andCriticalNumIsNull() {
            addCriterion("critical_num is null");
            return (Criteria) this;
        }

        public Criteria andCriticalNumIsNotNull() {
            addCriterion("critical_num is not null");
            return (Criteria) this;
        }

        public Criteria andCriticalNumEqualTo(Integer value) {
            addCriterion("critical_num =", value, "criticalNum");
            return (Criteria) this;
        }

        public Criteria andCriticalNumNotEqualTo(Integer value) {
            addCriterion("critical_num <>", value, "criticalNum");
            return (Criteria) this;
        }

        public Criteria andCriticalNumGreaterThan(Integer value) {
            addCriterion("critical_num >", value, "criticalNum");
            return (Criteria) this;
        }

        public Criteria andCriticalNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("critical_num >=", value, "criticalNum");
            return (Criteria) this;
        }

        public Criteria andCriticalNumLessThan(Integer value) {
            addCriterion("critical_num <", value, "criticalNum");
            return (Criteria) this;
        }

        public Criteria andCriticalNumLessThanOrEqualTo(Integer value) {
            addCriterion("critical_num <=", value, "criticalNum");
            return (Criteria) this;
        }

        public Criteria andCriticalNumIn(List<Integer> values) {
            addCriterion("critical_num in", values, "criticalNum");
            return (Criteria) this;
        }

        public Criteria andCriticalNumNotIn(List<Integer> values) {
            addCriterion("critical_num not in", values, "criticalNum");
            return (Criteria) this;
        }

        public Criteria andCriticalNumBetween(Integer value1, Integer value2) {
            addCriterion("critical_num between", value1, value2, "criticalNum");
            return (Criteria) this;
        }

        public Criteria andCriticalNumNotBetween(Integer value1, Integer value2) {
            addCriterion("critical_num not between", value1, value2, "criticalNum");
            return (Criteria) this;
        }

        public Criteria andVersionIdIsNull() {
            addCriterion("version_id is null");
            return (Criteria) this;
        }

        public Criteria andVersionIdIsNotNull() {
            addCriterion("version_id is not null");
            return (Criteria) this;
        }

        public Criteria andVersionIdEqualTo(String value) {
            addCriterion("version_id =", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotEqualTo(String value) {
            addCriterion("version_id <>", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThan(String value) {
            addCriterion("version_id >", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThanOrEqualTo(String value) {
            addCriterion("version_id >=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThan(String value) {
            addCriterion("version_id <", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThanOrEqualTo(String value) {
            addCriterion("version_id <=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLike(String value) {
            addCriterion("version_id like", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotLike(String value) {
            addCriterion("version_id not like", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdIn(List<String> values) {
            addCriterion("version_id in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotIn(List<String> values) {
            addCriterion("version_id not in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdBetween(String value1, String value2) {
            addCriterion("version_id between", value1, value2, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotBetween(String value1, String value2) {
            addCriterion("version_id not between", value1, value2, "versionId");
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