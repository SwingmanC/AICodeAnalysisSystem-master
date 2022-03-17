package org.nju.demo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.nju.demo.entity.ARule;
import org.nju.demo.entity.ARuleExample;

public interface ARuleMapper {
    @SelectProvider(type=ARuleSqlProvider.class, method="countByExample")
    long countByExample(ARuleExample example);

    @DeleteProvider(type=ARuleSqlProvider.class, method="deleteByExample")
    int deleteByExample(ARuleExample example);

    @Delete({
        "delete from a_rule",
        "where rule_id = #{ruleId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String ruleId);

    @Insert({
        "insert into a_rule (rule_id, rule_name, ",
        "pattern_name, priority, ",
        "kingdom, file_name, ",
        "function_name, create_time, ",
        "`state`, version_id)",
        "values (#{ruleId,jdbcType=VARCHAR}, #{ruleName,jdbcType=VARCHAR}, ",
        "#{patternName,jdbcType=VARCHAR}, #{priority,jdbcType=VARCHAR}, ",
        "#{kingdom,jdbcType=VARCHAR}, #{fileName,jdbcType=VARCHAR}, ",
        "#{functionName,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{state,jdbcType=INTEGER}, #{versionId,jdbcType=VARCHAR})"
    })
    int insert(ARule record);

    @InsertProvider(type=ARuleSqlProvider.class, method="insertSelective")
    int insertSelective(ARule record);

    @SelectProvider(type=ARuleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="rule_id", property="ruleId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="rule_name", property="ruleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern_name", property="patternName", jdbcType=JdbcType.VARCHAR),
        @Result(column="priority", property="priority", jdbcType=JdbcType.VARCHAR),
        @Result(column="kingdom", property="kingdom", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="function_name", property="functionName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR)
    })
    List<ARule> selectByExample(ARuleExample example);

    @Select({
        "select",
        "rule_id, rule_name, pattern_name, priority, kingdom, file_name, function_name, ",
        "create_time, `state`, version_id",
        "from a_rule",
        "where rule_id = #{ruleId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="rule_id", property="ruleId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="rule_name", property="ruleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern_name", property="patternName", jdbcType=JdbcType.VARCHAR),
        @Result(column="priority", property="priority", jdbcType=JdbcType.VARCHAR),
        @Result(column="kingdom", property="kingdom", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="function_name", property="functionName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR)
    })
    ARule selectByPrimaryKey(String ruleId);

    @UpdateProvider(type=ARuleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ARule record, @Param("example") ARuleExample example);

    @UpdateProvider(type=ARuleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ARule record, @Param("example") ARuleExample example);

    @UpdateProvider(type=ARuleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ARule record);

    @Update({
        "update a_rule",
        "set rule_name = #{ruleName,jdbcType=VARCHAR},",
          "pattern_name = #{patternName,jdbcType=VARCHAR},",
          "priority = #{priority,jdbcType=VARCHAR},",
          "kingdom = #{kingdom,jdbcType=VARCHAR},",
          "file_name = #{fileName,jdbcType=VARCHAR},",
          "function_name = #{functionName,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "`state` = #{state,jdbcType=INTEGER},",
          "version_id = #{versionId,jdbcType=VARCHAR}",
        "where rule_id = #{ruleId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(ARule record);
}