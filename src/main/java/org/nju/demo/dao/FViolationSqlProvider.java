package org.nju.demo.dao;

import org.nju.demo.entity.FViolation;
import org.nju.demo.entity.FViolationExample;
import org.nju.demo.entity.FViolationExample.Criteria;
import org.nju.demo.entity.FViolationExample.Criterion;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class FViolationSqlProvider {

    public String countByExample(FViolationExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("f_violation");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(FViolationExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("f_violation");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(FViolation record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("f_violation");
        
        if (record.getVersionId() != null) {
            sql.VALUES("version_id", "#{versionId,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("`type`", "#{type,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.VALUES("category", "#{category,jdbcType=VARCHAR}");
        }
        
        if (record.getPriority() != null) {
            sql.VALUES("priority", "#{priority,jdbcType=INTEGER}");
        }
        
        if (record.getClassname() != null) {
            sql.VALUES("classname", "#{classname,jdbcType=VARCHAR}");
        }
        
        if (record.getSourcePath() != null) {
            sql.VALUES("source_path", "#{sourcePath,jdbcType=VARCHAR}");
        }
        
        if (record.getMethodName() != null) {
            sql.VALUES("method_name", "#{methodName,jdbcType=VARCHAR}");
        }
        
        if (record.getSignature() != null) {
            sql.VALUES("signature", "#{signature,jdbcType=VARCHAR}");
        }
        
        if (record.getStartLine() != null) {
            sql.VALUES("start_line", "#{startLine,jdbcType=INTEGER}");
        }
        
        if (record.getEndLine() != null) {
            sql.VALUES("end_line", "#{endLine,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("`state`", "#{state,jdbcType=VARCHAR}");
        }
        
        if (record.getCategoryId() != null) {
            sql.VALUES("category_id", "#{categoryId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(FViolationExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("version_id");
        sql.SELECT("`type`");
        sql.SELECT("category");
        sql.SELECT("priority");
        sql.SELECT("classname");
        sql.SELECT("source_path");
        sql.SELECT("method_name");
        sql.SELECT("signature");
        sql.SELECT("start_line");
        sql.SELECT("end_line");
        sql.SELECT("`state`");
        sql.SELECT("category_id");
        sql.FROM("f_violation");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        FViolation record = (FViolation) parameter.get("record");
        FViolationExample example = (FViolationExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("f_violation");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getVersionId() != null) {
            sql.SET("version_id = #{record.versionId,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.SET("`type` = #{record.type,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("category = #{record.category,jdbcType=VARCHAR}");
        }
        
        if (record.getPriority() != null) {
            sql.SET("priority = #{record.priority,jdbcType=INTEGER}");
        }
        
        if (record.getClassname() != null) {
            sql.SET("classname = #{record.classname,jdbcType=VARCHAR}");
        }
        
        if (record.getSourcePath() != null) {
            sql.SET("source_path = #{record.sourcePath,jdbcType=VARCHAR}");
        }
        
        if (record.getMethodName() != null) {
            sql.SET("method_name = #{record.methodName,jdbcType=VARCHAR}");
        }
        
        if (record.getSignature() != null) {
            sql.SET("signature = #{record.signature,jdbcType=VARCHAR}");
        }
        
        if (record.getStartLine() != null) {
            sql.SET("start_line = #{record.startLine,jdbcType=INTEGER}");
        }
        
        if (record.getEndLine() != null) {
            sql.SET("end_line = #{record.endLine,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            sql.SET("`state` = #{record.state,jdbcType=VARCHAR}");
        }
        
        if (record.getCategoryId() != null) {
            sql.SET("category_id = #{record.categoryId,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("f_violation");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("version_id = #{record.versionId,jdbcType=INTEGER}");
        sql.SET("`type` = #{record.type,jdbcType=VARCHAR}");
        sql.SET("category = #{record.category,jdbcType=VARCHAR}");
        sql.SET("priority = #{record.priority,jdbcType=INTEGER}");
        sql.SET("classname = #{record.classname,jdbcType=VARCHAR}");
        sql.SET("source_path = #{record.sourcePath,jdbcType=VARCHAR}");
        sql.SET("method_name = #{record.methodName,jdbcType=VARCHAR}");
        sql.SET("signature = #{record.signature,jdbcType=VARCHAR}");
        sql.SET("start_line = #{record.startLine,jdbcType=INTEGER}");
        sql.SET("end_line = #{record.endLine,jdbcType=INTEGER}");
        sql.SET("`state` = #{record.state,jdbcType=VARCHAR}");
        sql.SET("category_id = #{record.categoryId,jdbcType=INTEGER}");
        
        FViolationExample example = (FViolationExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FViolation record) {
        SQL sql = new SQL();
        sql.UPDATE("f_violation");
        
        if (record.getVersionId() != null) {
            sql.SET("version_id = #{versionId,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.SET("`type` = #{type,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("category = #{category,jdbcType=VARCHAR}");
        }
        
        if (record.getPriority() != null) {
            sql.SET("priority = #{priority,jdbcType=INTEGER}");
        }
        
        if (record.getClassname() != null) {
            sql.SET("classname = #{classname,jdbcType=VARCHAR}");
        }
        
        if (record.getSourcePath() != null) {
            sql.SET("source_path = #{sourcePath,jdbcType=VARCHAR}");
        }
        
        if (record.getMethodName() != null) {
            sql.SET("method_name = #{methodName,jdbcType=VARCHAR}");
        }
        
        if (record.getSignature() != null) {
            sql.SET("signature = #{signature,jdbcType=VARCHAR}");
        }
        
        if (record.getStartLine() != null) {
            sql.SET("start_line = #{startLine,jdbcType=INTEGER}");
        }
        
        if (record.getEndLine() != null) {
            sql.SET("end_line = #{endLine,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            sql.SET("`state` = #{state,jdbcType=VARCHAR}");
        }
        
        if (record.getCategoryId() != null) {
            sql.SET("category_id = #{categoryId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, FViolationExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}