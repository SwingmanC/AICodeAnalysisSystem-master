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
import org.nju.demo.entity.PatternInfo;
import org.nju.demo.entity.PatternInfoExample;
import org.nju.demo.entity.PatternInfoWithBLOBs;
import org.nju.demo.pojo.vo.PatternItem;

public interface PatternInfoMapper {
    @SelectProvider(type=PatternInfoSqlProvider.class, method="countByExample")
    long countByExample(PatternInfoExample example);

    @DeleteProvider(type=PatternInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(PatternInfoExample example);

    @Delete({
        "delete from pattern_info",
        "where pattern_info_id = #{patternInfoId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String patternInfoId);

    @Insert({
        "insert into pattern_info (pattern_info_id, pattern_lk_id, ",
        "explanation, recommendation, ",
        "tip)",
        "values (#{patternInfoId,jdbcType=VARCHAR}, #{patternLkId,jdbcType=VARCHAR}, ",
        "#{explanation,jdbcType=LONGVARCHAR}, #{recommendation,jdbcType=LONGVARCHAR}, ",
        "#{tip,jdbcType=LONGVARCHAR})"
    })
    int insert(PatternInfoWithBLOBs record);

    @InsertProvider(type=PatternInfoSqlProvider.class, method="insertSelective")
    int insertSelective(PatternInfoWithBLOBs record);

    @SelectProvider(type=PatternInfoSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="pattern_info_id", property="patternInfoId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="pattern_lk_id", property="patternLkId", jdbcType=JdbcType.VARCHAR),
        @Result(column="explanation", property="explanation", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="recommendation", property="recommendation", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="tip", property="tip", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<PatternInfoWithBLOBs> selectByExampleWithBLOBs(PatternInfoExample example);

    @SelectProvider(type=PatternInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="pattern_info_id", property="patternInfoId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="pattern_lk_id", property="patternLkId", jdbcType=JdbcType.VARCHAR)
    })
    List<PatternInfo> selectByExample(PatternInfoExample example);

    @Select({
        "select",
        "pattern_info_id, pattern_lk_id, explanation, recommendation, tip",
        "from pattern_info",
        "where pattern_info_id = #{patternInfoId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="pattern_info_id", property="patternInfoId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="pattern_lk_id", property="patternLkId", jdbcType=JdbcType.VARCHAR),
        @Result(column="explanation", property="explanation", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="recommendation", property="recommendation", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="tip", property="tip", jdbcType=JdbcType.LONGVARCHAR)
    })
    PatternInfoWithBLOBs selectByPrimaryKey(String patternInfoId);

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
        "set pattern_lk_id = #{patternLkId,jdbcType=VARCHAR},",
          "explanation = #{explanation,jdbcType=LONGVARCHAR},",
          "recommendation = #{recommendation,jdbcType=LONGVARCHAR},",
          "tip = #{tip,jdbcType=LONGVARCHAR}",
        "where pattern_info_id = #{patternInfoId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(PatternInfoWithBLOBs record);

    @Update({
        "update pattern_info",
        "set pattern_lk_id = #{patternLkId,jdbcType=VARCHAR}",
        "where pattern_info_id = #{patternInfoId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(PatternInfo record);
}