package org.nju.demo.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.nju.demo.entity.IssueSource;
import org.nju.demo.entity.IssueSourceExample.Criteria;
import org.nju.demo.entity.IssueSourceExample.Criterion;
import org.nju.demo.entity.IssueSourceExample;

public class IssueSourceSqlProvider {

    public String countByExample(IssueSourceExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("issue_source");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(IssueSourceExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("issue_source");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(IssueSource record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("issue_source");
        
        if (record.getIssueSourceId() != null) {
            sql.VALUES("issue_source_id", "#{issueSourceId,jdbcType=VARCHAR}");
        }
        
        if (record.getIssueBasicId() != null) {
            sql.VALUES("issue_basic_id", "#{issueBasicId,jdbcType=VARCHAR}");
        }
        
        if (record.getFileName() != null) {
            sql.VALUES("file_name", "#{fileName,jdbcType=VARCHAR}");
        }
        
        if (record.getFilePath() != null) {
            sql.VALUES("file_path", "#{filePath,jdbcType=VARCHAR}");
        }
        
        if (record.getStartLine() != null) {
            sql.VALUES("start_line", "#{startLine,jdbcType=INTEGER}");
        }
        
        if (record.getTargetFunction() != null) {
            sql.VALUES("target_function", "#{targetFunction,jdbcType=VARCHAR}");
        }
        
        if (record.getSnippet() != null) {
            sql.VALUES("snippet", "#{snippet,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExampleWithBLOBs(IssueSourceExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("issue_source_id");
        } else {
            sql.SELECT("issue_source_id");
        }
        sql.SELECT("issue_basic_id");
        sql.SELECT("file_name");
        sql.SELECT("file_path");
        sql.SELECT("start_line");
        sql.SELECT("target_function");
        sql.SELECT("snippet");
        sql.FROM("issue_source");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String selectByExample(IssueSourceExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("issue_source_id");
        } else {
            sql.SELECT("issue_source_id");
        }
        sql.SELECT("issue_basic_id");
        sql.SELECT("file_name");
        sql.SELECT("file_path");
        sql.SELECT("start_line");
        sql.SELECT("target_function");
        sql.FROM("issue_source");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        IssueSource record = (IssueSource) parameter.get("record");
        IssueSourceExample example = (IssueSourceExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("issue_source");
        
        if (record.getIssueSourceId() != null) {
            sql.SET("issue_source_id = #{record.issueSourceId,jdbcType=VARCHAR}");
        }
        
        if (record.getIssueBasicId() != null) {
            sql.SET("issue_basic_id = #{record.issueBasicId,jdbcType=VARCHAR}");
        }
        
        if (record.getFileName() != null) {
            sql.SET("file_name = #{record.fileName,jdbcType=VARCHAR}");
        }
        
        if (record.getFilePath() != null) {
            sql.SET("file_path = #{record.filePath,jdbcType=VARCHAR}");
        }
        
        if (record.getStartLine() != null) {
            sql.SET("start_line = #{record.startLine,jdbcType=INTEGER}");
        }
        
        if (record.getTargetFunction() != null) {
            sql.SET("target_function = #{record.targetFunction,jdbcType=VARCHAR}");
        }
        
        if (record.getSnippet() != null) {
            sql.SET("snippet = #{record.snippet,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("issue_source");
        
        sql.SET("issue_source_id = #{record.issueSourceId,jdbcType=VARCHAR}");
        sql.SET("issue_basic_id = #{record.issueBasicId,jdbcType=VARCHAR}");
        sql.SET("file_name = #{record.fileName,jdbcType=VARCHAR}");
        sql.SET("file_path = #{record.filePath,jdbcType=VARCHAR}");
        sql.SET("start_line = #{record.startLine,jdbcType=INTEGER}");
        sql.SET("target_function = #{record.targetFunction,jdbcType=VARCHAR}");
        sql.SET("snippet = #{record.snippet,jdbcType=LONGVARCHAR}");
        
        IssueSourceExample example = (IssueSourceExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("issue_source");
        
        sql.SET("issue_source_id = #{record.issueSourceId,jdbcType=VARCHAR}");
        sql.SET("issue_basic_id = #{record.issueBasicId,jdbcType=VARCHAR}");
        sql.SET("file_name = #{record.fileName,jdbcType=VARCHAR}");
        sql.SET("file_path = #{record.filePath,jdbcType=VARCHAR}");
        sql.SET("start_line = #{record.startLine,jdbcType=INTEGER}");
        sql.SET("target_function = #{record.targetFunction,jdbcType=VARCHAR}");
        
        IssueSourceExample example = (IssueSourceExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(IssueSource record) {
        SQL sql = new SQL();
        sql.UPDATE("issue_source");
        
        if (record.getIssueBasicId() != null) {
            sql.SET("issue_basic_id = #{issueBasicId,jdbcType=VARCHAR}");
        }
        
        if (record.getFileName() != null) {
            sql.SET("file_name = #{fileName,jdbcType=VARCHAR}");
        }
        
        if (record.getFilePath() != null) {
            sql.SET("file_path = #{filePath,jdbcType=VARCHAR}");
        }
        
        if (record.getStartLine() != null) {
            sql.SET("start_line = #{startLine,jdbcType=INTEGER}");
        }
        
        if (record.getTargetFunction() != null) {
            sql.SET("target_function = #{targetFunction,jdbcType=VARCHAR}");
        }
        
        if (record.getSnippet() != null) {
            sql.SET("snippet = #{snippet,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("issue_source_id = #{issueSourceId,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, IssueSourceExample example, boolean includeExamplePhrase) {
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