package org.nju.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class IssueSourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IssueSourceExample() {
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

        public Criteria andIssueSourceIdIsNull() {
            addCriterion("issue_source_id is null");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdIsNotNull() {
            addCriterion("issue_source_id is not null");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdEqualTo(String value) {
            addCriterion("issue_source_id =", value, "issueSourceId");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdNotEqualTo(String value) {
            addCriterion("issue_source_id <>", value, "issueSourceId");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdGreaterThan(String value) {
            addCriterion("issue_source_id >", value, "issueSourceId");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("issue_source_id >=", value, "issueSourceId");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdLessThan(String value) {
            addCriterion("issue_source_id <", value, "issueSourceId");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdLessThanOrEqualTo(String value) {
            addCriterion("issue_source_id <=", value, "issueSourceId");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdLike(String value) {
            addCriterion("issue_source_id like", value, "issueSourceId");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdNotLike(String value) {
            addCriterion("issue_source_id not like", value, "issueSourceId");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdIn(List<String> values) {
            addCriterion("issue_source_id in", values, "issueSourceId");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdNotIn(List<String> values) {
            addCriterion("issue_source_id not in", values, "issueSourceId");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdBetween(String value1, String value2) {
            addCriterion("issue_source_id between", value1, value2, "issueSourceId");
            return (Criteria) this;
        }

        public Criteria andIssueSourceIdNotBetween(String value1, String value2) {
            addCriterion("issue_source_id not between", value1, value2, "issueSourceId");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdIsNull() {
            addCriterion("issue_basic_id is null");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdIsNotNull() {
            addCriterion("issue_basic_id is not null");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdEqualTo(String value) {
            addCriterion("issue_basic_id =", value, "issueBasicId");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdNotEqualTo(String value) {
            addCriterion("issue_basic_id <>", value, "issueBasicId");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdGreaterThan(String value) {
            addCriterion("issue_basic_id >", value, "issueBasicId");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdGreaterThanOrEqualTo(String value) {
            addCriterion("issue_basic_id >=", value, "issueBasicId");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdLessThan(String value) {
            addCriterion("issue_basic_id <", value, "issueBasicId");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdLessThanOrEqualTo(String value) {
            addCriterion("issue_basic_id <=", value, "issueBasicId");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdLike(String value) {
            addCriterion("issue_basic_id like", value, "issueBasicId");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdNotLike(String value) {
            addCriterion("issue_basic_id not like", value, "issueBasicId");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdIn(List<String> values) {
            addCriterion("issue_basic_id in", values, "issueBasicId");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdNotIn(List<String> values) {
            addCriterion("issue_basic_id not in", values, "issueBasicId");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdBetween(String value1, String value2) {
            addCriterion("issue_basic_id between", value1, value2, "issueBasicId");
            return (Criteria) this;
        }

        public Criteria andIssueBasicIdNotBetween(String value1, String value2) {
            addCriterion("issue_basic_id not between", value1, value2, "issueBasicId");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andStartLineIsNull() {
            addCriterion("start_line is null");
            return (Criteria) this;
        }

        public Criteria andStartLineIsNotNull() {
            addCriterion("start_line is not null");
            return (Criteria) this;
        }

        public Criteria andStartLineEqualTo(Integer value) {
            addCriterion("start_line =", value, "startLine");
            return (Criteria) this;
        }

        public Criteria andStartLineNotEqualTo(Integer value) {
            addCriterion("start_line <>", value, "startLine");
            return (Criteria) this;
        }

        public Criteria andStartLineGreaterThan(Integer value) {
            addCriterion("start_line >", value, "startLine");
            return (Criteria) this;
        }

        public Criteria andStartLineGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_line >=", value, "startLine");
            return (Criteria) this;
        }

        public Criteria andStartLineLessThan(Integer value) {
            addCriterion("start_line <", value, "startLine");
            return (Criteria) this;
        }

        public Criteria andStartLineLessThanOrEqualTo(Integer value) {
            addCriterion("start_line <=", value, "startLine");
            return (Criteria) this;
        }

        public Criteria andStartLineIn(List<Integer> values) {
            addCriterion("start_line in", values, "startLine");
            return (Criteria) this;
        }

        public Criteria andStartLineNotIn(List<Integer> values) {
            addCriterion("start_line not in", values, "startLine");
            return (Criteria) this;
        }

        public Criteria andStartLineBetween(Integer value1, Integer value2) {
            addCriterion("start_line between", value1, value2, "startLine");
            return (Criteria) this;
        }

        public Criteria andStartLineNotBetween(Integer value1, Integer value2) {
            addCriterion("start_line not between", value1, value2, "startLine");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionIsNull() {
            addCriterion("target_function is null");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionIsNotNull() {
            addCriterion("target_function is not null");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionEqualTo(String value) {
            addCriterion("target_function =", value, "targetFunction");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionNotEqualTo(String value) {
            addCriterion("target_function <>", value, "targetFunction");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionGreaterThan(String value) {
            addCriterion("target_function >", value, "targetFunction");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionGreaterThanOrEqualTo(String value) {
            addCriterion("target_function >=", value, "targetFunction");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionLessThan(String value) {
            addCriterion("target_function <", value, "targetFunction");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionLessThanOrEqualTo(String value) {
            addCriterion("target_function <=", value, "targetFunction");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionLike(String value) {
            addCriterion("target_function like", value, "targetFunction");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionNotLike(String value) {
            addCriterion("target_function not like", value, "targetFunction");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionIn(List<String> values) {
            addCriterion("target_function in", values, "targetFunction");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionNotIn(List<String> values) {
            addCriterion("target_function not in", values, "targetFunction");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionBetween(String value1, String value2) {
            addCriterion("target_function between", value1, value2, "targetFunction");
            return (Criteria) this;
        }

        public Criteria andTargetFunctionNotBetween(String value1, String value2) {
            addCriterion("target_function not between", value1, value2, "targetFunction");
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