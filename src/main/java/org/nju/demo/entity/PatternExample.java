package org.nju.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class PatternExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PatternExample() {
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

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andLikelihoodIsNull() {
            addCriterion("likelihood is null");
            return (Criteria) this;
        }

        public Criteria andLikelihoodIsNotNull() {
            addCriterion("likelihood is not null");
            return (Criteria) this;
        }

        public Criteria andLikelihoodEqualTo(Double value) {
            addCriterion("likelihood =", value, "likelihood");
            return (Criteria) this;
        }

        public Criteria andLikelihoodNotEqualTo(Double value) {
            addCriterion("likelihood <>", value, "likelihood");
            return (Criteria) this;
        }

        public Criteria andLikelihoodGreaterThan(Double value) {
            addCriterion("likelihood >", value, "likelihood");
            return (Criteria) this;
        }

        public Criteria andLikelihoodGreaterThanOrEqualTo(Double value) {
            addCriterion("likelihood >=", value, "likelihood");
            return (Criteria) this;
        }

        public Criteria andLikelihoodLessThan(Double value) {
            addCriterion("likelihood <", value, "likelihood");
            return (Criteria) this;
        }

        public Criteria andLikelihoodLessThanOrEqualTo(Double value) {
            addCriterion("likelihood <=", value, "likelihood");
            return (Criteria) this;
        }

        public Criteria andLikelihoodIn(List<Double> values) {
            addCriterion("likelihood in", values, "likelihood");
            return (Criteria) this;
        }

        public Criteria andLikelihoodNotIn(List<Double> values) {
            addCriterion("likelihood not in", values, "likelihood");
            return (Criteria) this;
        }

        public Criteria andLikelihoodBetween(Double value1, Double value2) {
            addCriterion("likelihood between", value1, value2, "likelihood");
            return (Criteria) this;
        }

        public Criteria andLikelihoodNotBetween(Double value1, Double value2) {
            addCriterion("likelihood not between", value1, value2, "likelihood");
            return (Criteria) this;
        }

        public Criteria andVarianceIsNull() {
            addCriterion("variance is null");
            return (Criteria) this;
        }

        public Criteria andVarianceIsNotNull() {
            addCriterion("variance is not null");
            return (Criteria) this;
        }

        public Criteria andVarianceEqualTo(Double value) {
            addCriterion("variance =", value, "variance");
            return (Criteria) this;
        }

        public Criteria andVarianceNotEqualTo(Double value) {
            addCriterion("variance <>", value, "variance");
            return (Criteria) this;
        }

        public Criteria andVarianceGreaterThan(Double value) {
            addCriterion("variance >", value, "variance");
            return (Criteria) this;
        }

        public Criteria andVarianceGreaterThanOrEqualTo(Double value) {
            addCriterion("variance >=", value, "variance");
            return (Criteria) this;
        }

        public Criteria andVarianceLessThan(Double value) {
            addCriterion("variance <", value, "variance");
            return (Criteria) this;
        }

        public Criteria andVarianceLessThanOrEqualTo(Double value) {
            addCriterion("variance <=", value, "variance");
            return (Criteria) this;
        }

        public Criteria andVarianceIn(List<Double> values) {
            addCriterion("variance in", values, "variance");
            return (Criteria) this;
        }

        public Criteria andVarianceNotIn(List<Double> values) {
            addCriterion("variance not in", values, "variance");
            return (Criteria) this;
        }

        public Criteria andVarianceBetween(Double value1, Double value2) {
            addCriterion("variance between", value1, value2, "variance");
            return (Criteria) this;
        }

        public Criteria andVarianceNotBetween(Double value1, Double value2) {
            addCriterion("variance not between", value1, value2, "variance");
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