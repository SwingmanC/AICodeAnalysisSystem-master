package org.nju.demo.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.nju.demo.entity.AVersion;
import org.nju.demo.entity.AVersionExample.Criteria;
import org.nju.demo.entity.AVersionExample.Criterion;
import org.nju.demo.entity.AVersionExample;

public class AVersionSqlProvider {

    public String countByExample(AVersionExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("a_version");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(AVersionExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("a_version");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(AVersion record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("a_version");
        
        if (record.getVersionId() != null) {
            sql.VALUES("version_id", "#{versionId,jdbcType=VARCHAR}");
        }
        
        if (record.getVersionName() != null) {
            sql.VALUES("version_name", "#{versionName,jdbcType=VARCHAR}");
        }
        
        if (record.getFilePath() != null) {
            sql.VALUES("file_path", "#{filePath,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getProjectId() != null) {
            sql.VALUES("project_id", "#{projectId,jdbcType=VARCHAR}");
        }
        
        if (record.getLastId() != null) {
            sql.VALUES("last_id", "#{lastId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(AVersionExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("version_id");
        } else {
            sql.SELECT("version_id");
        }
        sql.SELECT("version_name");
        sql.SELECT("file_path");
        sql.SELECT("create_time");
        sql.SELECT("project_id");
        sql.SELECT("last_id");
        sql.FROM("a_version");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        AVersion record = (AVersion) parameter.get("record");
        AVersionExample example = (AVersionExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("a_version");
        
        if (record.getVersionId() != null) {
            sql.SET("version_id = #{record.versionId,jdbcType=VARCHAR}");
        }
        
        if (record.getVersionName() != null) {
            sql.SET("version_name = #{record.versionName,jdbcType=VARCHAR}");
        }
        
        if (record.getFilePath() != null) {
            sql.SET("file_path = #{record.filePath,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getProjectId() != null) {
            sql.SET("project_id = #{record.projectId,jdbcType=VARCHAR}");
        }
        
        if (record.getLastId() != null) {
            sql.SET("last_id = #{record.lastId,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("a_version");
        
        sql.SET("version_id = #{record.versionId,jdbcType=VARCHAR}");
        sql.SET("version_name = #{record.versionName,jdbcType=VARCHAR}");
        sql.SET("file_path = #{record.filePath,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("project_id = #{record.projectId,jdbcType=VARCHAR}");
        sql.SET("last_id = #{record.lastId,jdbcType=VARCHAR}");
        
        AVersionExample example = (AVersionExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AVersion record) {
        SQL sql = new SQL();
        sql.UPDATE("a_version");
        
        if (record.getVersionName() != null) {
            sql.SET("version_name = #{versionName,jdbcType=VARCHAR}");
        }
        
        if (record.getFilePath() != null) {
            sql.SET("file_path = #{filePath,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getProjectId() != null) {
            sql.SET("project_id = #{projectId,jdbcType=VARCHAR}");
        }
        
        if (record.getLastId() != null) {
            sql.SET("last_id = #{lastId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("version_id = #{versionId,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, AVersionExample example, boolean includeExamplePhrase) {
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