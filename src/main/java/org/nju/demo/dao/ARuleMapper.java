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
import org.apache.ibatis.annotations.SelectKey;
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
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into a_rule (rule_name, pattern, ",
        "priority, category, ",
        "file_name, function_name, ",
        "`state`, user_id)",
        "values (#{ruleName,jdbcType=VARCHAR}, #{pattern,jdbcType=VARCHAR}, ",
        "#{priority,jdbcType=VARCHAR}, #{category,jdbcType=VARCHAR}, ",
        "#{fileName,jdbcType=VARCHAR}, #{functionName,jdbcType=VARCHAR}, ",
        "#{state,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ARule record);

    @InsertProvider(type=ARuleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ARule record);

    @SelectProvider(type=ARuleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rule_name", property="ruleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern", property="pattern", jdbcType=JdbcType.VARCHAR),
        @Result(column="priority", property="priority", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="function_name", property="functionName", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<ARule> selectByExample(ARuleExample example);

    @Select({
        "select",
        "id, rule_name, pattern, priority, category, file_name, function_name, `state`, ",
        "user_id",
        "from a_rule",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rule_name", property="ruleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern", property="pattern", jdbcType=JdbcType.VARCHAR),
        @Result(column="priority", property="priority", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="function_name", property="functionName", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    ARule selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ARuleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ARule record, @Param("example") ARuleExample example);

    @UpdateProvider(type=ARuleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ARule record, @Param("example") ARuleExample example);

    @UpdateProvider(type=ARuleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ARule record);

    @Update({
        "update a_rule",
        "set rule_name = #{ruleName,jdbcType=VARCHAR},",
          "pattern = #{pattern,jdbcType=VARCHAR},",
          "priority = #{priority,jdbcType=VARCHAR},",
          "category = #{category,jdbcType=VARCHAR},",
          "file_name = #{fileName,jdbcType=VARCHAR},",
          "function_name = #{functionName,jdbcType=VARCHAR},",
          "`state` = #{state,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ARule record);
}