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
import org.nju.demo.entity.PatternInfo;
import org.nju.demo.entity.PatternInfoExample;
import org.nju.demo.entity.PatternInfoWithBLOBs;

public interface PatternInfoMapper {
    @SelectProvider(type=PatternInfoSqlProvider.class, method="countByExample")
    long countByExample(PatternInfoExample example);

    @DeleteProvider(type=PatternInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(PatternInfoExample example);

    @Delete({
        "delete from pattern_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into pattern_info (pattern_id, pattern_name, ",
        "explanation, recommendation, ",
        "tip)",
        "values (#{patternId,jdbcType=VARCHAR}, #{patternName,jdbcType=VARCHAR}, ",
        "#{explanation,jdbcType=LONGVARCHAR}, #{recommendation,jdbcType=LONGVARCHAR}, ",
        "#{tip,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(PatternInfoWithBLOBs record);

    @InsertProvider(type=PatternInfoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(PatternInfoWithBLOBs record);

    @SelectProvider(type=PatternInfoSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern_name", property="patternName", jdbcType=JdbcType.VARCHAR),
        @Result(column="explanation", property="explanation", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="recommendation", property="recommendation", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="tip", property="tip", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<PatternInfoWithBLOBs> selectByExampleWithBLOBs(PatternInfoExample example);

    @SelectProvider(type=PatternInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern_name", property="patternName", jdbcType=JdbcType.VARCHAR)
    })
    List<PatternInfo> selectByExample(PatternInfoExample example);

    @Select({
        "select",
        "id, pattern_id, pattern_name, explanation, recommendation, tip",
        "from pattern_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern_name", property="patternName", jdbcType=JdbcType.VARCHAR),
        @Result(column="explanation", property="explanation", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="recommendation", property="recommendation", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="tip", property="tip", jdbcType=JdbcType.LONGVARCHAR)
    })
    PatternInfoWithBLOBs selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PatternInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PatternInfoWithBLOBs record, @Param("example") PatternInfoExample example);

    @UpdateProvider(type=PatternInfoSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") PatternInfoWithBLOBs record, @Param("example") PatternInfoExample example);

    @UpdateProvider(type=PatternInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PatternInfo record, @Param("example") PatternInfoExample example);

    @UpdateProvider(type=PatternInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PatternInfoWithBLOBs record);

    @Update({
        "update pattern_info",
        "set pattern_id = #{patternId,jdbcType=VARCHAR},",
          "pattern_name = #{patternName,jdbcType=VARCHAR},",
          "explanation = #{explanation,jdbcType=LONGVARCHAR},",
          "recommendation = #{recommendation,jdbcType=LONGVARCHAR},",
          "tip = #{tip,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(PatternInfoWithBLOBs record);

    @Update({
        "update pattern_info",
        "set pattern_id = #{patternId,jdbcType=VARCHAR},",
          "pattern_name = #{patternName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PatternInfo record);
}