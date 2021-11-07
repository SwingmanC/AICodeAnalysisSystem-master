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
import org.nju.demo.entity.FViolation;
import org.nju.demo.entity.FViolationExample;

public interface FViolationMapper {
    @SelectProvider(type=FViolationSqlProvider.class, method="countByExample")
    long countByExample(FViolationExample example);

    @DeleteProvider(type=FViolationSqlProvider.class, method="deleteByExample")
    int deleteByExample(FViolationExample example);

    @Delete({
        "delete from f_violation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into f_violation (version_id, `type`, ",
        "category, priority, ",
        "classname, source_path, ",
        "method_name, signature, ",
        "start_line, end_line, ",
        "`state`)",
        "values (#{versionId,jdbcType=INTEGER}, #{type,jdbcType=VARCHAR}, ",
        "#{category,jdbcType=VARCHAR}, #{priority,jdbcType=INTEGER}, ",
        "#{classname,jdbcType=VARCHAR}, #{sourcePath,jdbcType=VARCHAR}, ",
        "#{methodName,jdbcType=VARCHAR}, #{signature,jdbcType=VARCHAR}, ",
        "#{startLine,jdbcType=INTEGER}, #{endLine,jdbcType=INTEGER}, ",
        "#{state,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(FViolation record);

    @InsertProvider(type=FViolationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(FViolation record);

    @SelectProvider(type=FViolationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="priority", property="priority", jdbcType=JdbcType.INTEGER),
        @Result(column="classname", property="classname", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_path", property="sourcePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="method_name", property="methodName", jdbcType=JdbcType.VARCHAR),
        @Result(column="signature", property="signature", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="end_line", property="endLine", jdbcType=JdbcType.INTEGER),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR)
    })
    List<FViolation> selectByExample(FViolationExample example);

    @Select({
        "select",
        "id, version_id, `type`, category, priority, classname, source_path, method_name, ",
        "signature, start_line, end_line, `state`",
        "from f_violation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="priority", property="priority", jdbcType=JdbcType.INTEGER),
        @Result(column="classname", property="classname", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_path", property="sourcePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="method_name", property="methodName", jdbcType=JdbcType.VARCHAR),
        @Result(column="signature", property="signature", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="end_line", property="endLine", jdbcType=JdbcType.INTEGER),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR)
    })
    FViolation selectByPrimaryKey(Integer id);

    @UpdateProvider(type=FViolationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FViolation record, @Param("example") FViolationExample example);

    @UpdateProvider(type=FViolationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FViolation record, @Param("example") FViolationExample example);

    @UpdateProvider(type=FViolationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FViolation record);

    @Update({
        "update f_violation",
        "set version_id = #{versionId,jdbcType=INTEGER},",
          "`type` = #{type,jdbcType=VARCHAR},",
          "category = #{category,jdbcType=VARCHAR},",
          "priority = #{priority,jdbcType=INTEGER},",
          "classname = #{classname,jdbcType=VARCHAR},",
          "source_path = #{sourcePath,jdbcType=VARCHAR},",
          "method_name = #{methodName,jdbcType=VARCHAR},",
          "signature = #{signature,jdbcType=VARCHAR},",
          "start_line = #{startLine,jdbcType=INTEGER},",
          "end_line = #{endLine,jdbcType=INTEGER},",
          "`state` = #{state,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FViolation record);
}