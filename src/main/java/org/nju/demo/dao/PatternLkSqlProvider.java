package org.nju.demo.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.nju.demo.entity.PatternLk;
import org.nju.demo.entity.PatternLkExample.Criteria;
import org.nju.demo.entity.PatternLkExample.Criterion;
import org.nju.demo.entity.PatternLkExample;

public class PatternLkSqlProvider {

    public String countByExample(PatternLkExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("pattern_lk");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(PatternLkExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("pattern_lk");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(PatternLk record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("pattern_lk");
        
        if (record.getPatternLkId() != null) {
            sql.VALUES("pattern_lk_id", "#{patternLkId,jdbcType=VARCHAR}");
        }
        
        if (record.getPatternName() != null) {
            sql.VALUES("pattern_name", "#{patternName,jdbcType=VARCHAR}");
        }
        
        if (record.gettNum() != null) {
            sql.VALUES("t_num", "#{tNum,jdbcType=INTEGER}");
        }
        
        if (record.getfNum() != null) {
            sql.VALUES("f_num", "#{fNum,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(PatternLkExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("pattern_lk_id");
        } else {
            sql.SELECT("pattern_lk_id");
        }
        sql.SELECT("pattern_name");
        sql.SELECT("t_num");
        sql.SELECT("f_num");
        sql.FROM("pattern_lk");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        PatternLk record = (PatternLk) parameter.get("record");
        PatternLkExample example = (PatternLkExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("pattern_lk");
        
        if (record.getPatternLkId() != null) {
            sql.SET("pattern_lk_id = #{record.patternLkId,jdbcType=VARCHAR}");
        }
        
        if (record.getPatternName() != null) {
            sql.SET("pattern_name = #{record.patternName,jdbcType=VARCHAR}");
        }
        
        if (record.gettNum() != null) {
            sql.SET("t_num = #{record.tNum,jdbcType=INTEGER}");
        }
        
        if (record.getfNum() != null) {
            sql.SET("f_num = #{record.fNum,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("pattern_lk");
        
        sql.SET("pattern_lk_id = #{record.patternLkId,jdbcType=VARCHAR}");
        sql.SET("pattern_name = #{record.patternName,jdbcType=VARCHAR}");
        sql.SET("t_num = #{record.tNum,jdbcType=INTEGER}");
        sql.SET("f_num = #{record.fNum,jdbcType=INTEGER}");
        
        PatternLkExample example = (PatternLkExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PatternLk record) {
        SQL sql = new SQL();
        sql.UPDATE("pattern_lk");
        
        if (record.getPatternName() != null) {
            sql.SET("pattern_name = #{patternName,jdbcType=VARCHAR}");
        }
        
        if (record.gettNum() != null) {
            sql.SET("t_num = #{tNum,jdbcType=INTEGER}");
        }
        
        if (record.getfNum() != null) {
            sql.SET("f_num = #{fNum,jdbcType=INTEGER}");
        }
        
        sql.WHERE("pattern_lk_id = #{patternLkId,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, PatternLkExample example, boolean includeExamplePhrase) {
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