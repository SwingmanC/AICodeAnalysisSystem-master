package org.nju.demo.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.nju.demo.entity.PriorityStatistics;
import org.nju.demo.entity.PriorityStatisticsExample.Criteria;
import org.nju.demo.entity.PriorityStatisticsExample.Criterion;
import org.nju.demo.entity.PriorityStatisticsExample;

public class PriorityStatisticsSqlProvider {

    public String countByExample(PriorityStatisticsExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("priority_statistics");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(PriorityStatisticsExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("priority_statistics");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(PriorityStatistics record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("priority_statistics");
        
        if (record.getLowNum() != null) {
            sql.VALUES("low_num", "#{lowNum,jdbcType=INTEGER}");
        }
        
        if (record.getMediumNum() != null) {
            sql.VALUES("medium_num", "#{mediumNum,jdbcType=INTEGER}");
        }
        
        if (record.getHighNum() != null) {
            sql.VALUES("high_num", "#{highNum,jdbcType=INTEGER}");
        }
        
        if (record.getCriticalNum() != null) {
            sql.VALUES("critical_num", "#{criticalNum,jdbcType=INTEGER}");
        }
        
        if (record.getVersionId() != null) {
            sql.VALUES("version_id", "#{versionId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(PriorityStatisticsExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("low_num");
        sql.SELECT("medium_num");
        sql.SELECT("high_num");
        sql.SELECT("critical_num");
        sql.SELECT("version_id");
        sql.FROM("priority_statistics");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        PriorityStatistics record = (PriorityStatistics) parameter.get("record");
        PriorityStatisticsExample example = (PriorityStatisticsExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("priority_statistics");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getLowNum() != null) {
            sql.SET("low_num = #{record.lowNum,jdbcType=INTEGER}");
        }
        
        if (record.getMediumNum() != null) {
            sql.SET("medium_num = #{record.mediumNum,jdbcType=INTEGER}");
        }
        
        if (record.getHighNum() != null) {
            sql.SET("high_num = #{record.highNum,jdbcType=INTEGER}");
        }
        
        if (record.getCriticalNum() != null) {
            sql.SET("critical_num = #{record.criticalNum,jdbcType=INTEGER}");
        }
        
        if (record.getVersionId() != null) {
            sql.SET("version_id = #{record.versionId,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("priority_statistics");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("low_num = #{record.lowNum,jdbcType=INTEGER}");
        sql.SET("medium_num = #{record.mediumNum,jdbcType=INTEGER}");
        sql.SET("high_num = #{record.highNum,jdbcType=INTEGER}");
        sql.SET("critical_num = #{record.criticalNum,jdbcType=INTEGER}");
        sql.SET("version_id = #{record.versionId,jdbcType=VARCHAR}");
        
        PriorityStatisticsExample example = (PriorityStatisticsExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PriorityStatistics record) {
        SQL sql = new SQL();
        sql.UPDATE("priority_statistics");
        
        if (record.getLowNum() != null) {
            sql.SET("low_num = #{lowNum,jdbcType=INTEGER}");
        }
        
        if (record.getMediumNum() != null) {
            sql.SET("medium_num = #{mediumNum,jdbcType=INTEGER}");
        }
        
        if (record.getHighNum() != null) {
            sql.SET("high_num = #{highNum,jdbcType=INTEGER}");
        }
        
        if (record.getCriticalNum() != null) {
            sql.SET("critical_num = #{criticalNum,jdbcType=INTEGER}");
        }
        
        if (record.getVersionId() != null) {
            sql.SET("version_id = #{versionId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, PriorityStatisticsExample example, boolean includeExamplePhrase) {
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