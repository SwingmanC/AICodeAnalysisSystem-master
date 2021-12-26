package org.nju.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class PatternLkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PatternLkExample() {
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

        public Criteria andPatternNameIsNull() {
            addCriterion("pattern_name is null");
            return (Criteria) this;
        }

        public Criteria andPatternNameIsNotNull() {
            addCriterion("pattern_name is not null");
            return (Criteria) this;
        }

        public Criteria andPatternNameEqualTo(String value) {
            addCriterion("pattern_name =", value, "patternName");
            return (Criteria) this;
        }

        public Criteria andPatternNameNotEqualTo(String value) {
            addCriterion("pattern_name <>", value, "patternName");
            return (Criteria) this;
        }

        public Criteria andPatternNameGreaterThan(String value) {
            addCriterion("pattern_name >", value, "patternName");
            return (Criteria) this;
        }

        public Criteria andPatternNameGreaterThanOrEqualTo(String value) {
            addCriterion("pattern_name >=", value, "patternName");
            return (Criteria) this;
        }

        public Criteria andPatternNameLessThan(String value) {
            addCriterion("pattern_name <", value, "patternName");
            return (Criteria) this;
        }

        public Criteria andPatternNameLessThanOrEqualTo(String value) {
            addCriterion("pattern_name <=", value, "patternName");
            return (Criteria) this;
        }

        public Criteria andPatternNameLike(String value) {
            addCriterion("pattern_name like", value, "patternName");
            return (Criteria) this;
        }

        public Criteria andPatternNameNotLike(String value) {
            addCriterion("pattern_name not like", value, "patternName");
            return (Criteria) this;
        }

        public Criteria andPatternNameIn(List<String> values) {
            addCriterion("pattern_name in", values, "patternName");
            return (Criteria) this;
        }

        public Criteria andPatternNameNotIn(List<String> values) {
            addCriterion("pattern_name not in", values, "patternName");
            return (Criteria) this;
        }

        public Criteria andPatternNameBetween(String value1, String value2) {
            addCriterion("pattern_name between", value1, value2, "patternName");
            return (Criteria) this;
        }

        public Criteria andPatternNameNotBetween(String value1, String value2) {
            addCriterion("pattern_name not between", value1, value2, "patternName");
            return (Criteria) this;
        }

        public Criteria andTNumIsNull() {
            addCriterion("t_num is null");
            return (Criteria) this;
        }

        public Criteria andTNumIsNotNull() {
            addCriterion("t_num is not null");
            return (Criteria) this;
        }

        public Criteria andTNumEqualTo(Integer value) {
            addCriterion("t_num =", value, "tNum");
            return (Criteria) this;
        }

        public Criteria andTNumNotEqualTo(Integer value) {
            addCriterion("t_num <>", value, "tNum");
            return (Criteria) this;
        }

        public Criteria andTNumGreaterThan(Integer value) {
            addCriterion("t_num >", value, "tNum");
            return (Criteria) this;
        }

        public Criteria andTNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("t_num >=", value, "tNum");
            return (Criteria) this;
        }

        public Criteria andTNumLessThan(Integer value) {
            addCriterion("t_num <", value, "tNum");
            return (Criteria) this;
        }

        public Criteria andTNumLessThanOrEqualTo(Integer value) {
            addCriterion("t_num <=", value, "tNum");
            return (Criteria) this;
        }

        public Criteria andTNumIn(List<Integer> values) {
            addCriterion("t_num in", values, "tNum");
            return (Criteria) this;
        }

        public Criteria andTNumNotIn(List<Integer> values) {
            addCriterion("t_num not in", values, "tNum");
            return (Criteria) this;
        }

        public Criteria andTNumBetween(Integer value1, Integer value2) {
            addCriterion("t_num between", value1, value2, "tNum");
            return (Criteria) this;
        }

        public Criteria andTNumNotBetween(Integer value1, Integer value2) {
            addCriterion("t_num not between", value1, value2, "tNum");
            return (Criteria) this;
        }

        public Criteria andFNumIsNull() {
            addCriterion("f_num is null");
            return (Criteria) this;
        }

        public Criteria andFNumIsNotNull() {
            addCriterion("f_num is not null");
            return (Criteria) this;
        }

        public Criteria andFNumEqualTo(Integer value) {
            addCriterion("f_num =", value, "fNum");
            return (Criteria) this;
        }

        public Criteria andFNumNotEqualTo(Integer value) {
            addCriterion("f_num <>", value, "fNum");
            return (Criteria) this;
        }

        public Criteria andFNumGreaterThan(Integer value) {
            addCriterion("f_num >", value, "fNum");
            return (Criteria) this;
        }

        public Criteria andFNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_num >=", value, "fNum");
            return (Criteria) this;
        }

        public Criteria andFNumLessThan(Integer value) {
            addCriterion("f_num <", value, "fNum");
            return (Criteria) this;
        }

        public Criteria andFNumLessThanOrEqualTo(Integer value) {
            addCriterion("f_num <=", value, "fNum");
            return (Criteria) this;
        }

        public Criteria andFNumIn(List<Integer> values) {
            addCriterion("f_num in", values, "fNum");
            return (Criteria) this;
        }

        public Criteria andFNumNotIn(List<Integer> values) {
            addCriterion("f_num not in", values, "fNum");
            return (Criteria) this;
        }

        public Criteria andFNumBetween(Integer value1, Integer value2) {
            addCriterion("f_num between", value1, value2, "fNum");
            return (Criteria) this;
        }

        public Criteria andFNumNotBetween(Integer value1, Integer value2) {
            addCriterion("f_num not between", value1, value2, "fNum");
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