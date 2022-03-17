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
import org.nju.demo.entity.PatternLk;
import org.nju.demo.entity.PatternLkExample;
import org.nju.demo.pojo.vo.PatternItem;

public interface PatternLkMapper {
    @SelectProvider(type=PatternLkSqlProvider.class, method="countByExample")
    long countByExample(PatternLkExample example);

    @DeleteProvider(type=PatternLkSqlProvider.class, method="deleteByExample")
    int deleteByExample(PatternLkExample example);

    @Delete({
        "delete from pattern_lk",
        "where pattern_lk_id = #{patternLkId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String patternLkId);

    @Insert({
        "insert into pattern_lk (pattern_lk_id, pattern_name, ",
        "t_num, f_num)",
        "values (#{patternLkId,jdbcType=VARCHAR}, #{patternName,jdbcType=VARCHAR}, ",
        "#{tNum,jdbcType=INTEGER}, #{fNum,jdbcType=INTEGER})"
    })
    int insert(PatternLk record);

    @InsertProvider(type=PatternLkSqlProvider.class, method="insertSelective")
    int insertSelective(PatternLk record);

    @SelectProvider(type=PatternLkSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="pattern_lk_id", property="patternLkId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="pattern_name", property="patternName", jdbcType=JdbcType.VARCHAR),
        @Result(column="t_num", property="tNum", jdbcType=JdbcType.INTEGER),
        @Result(column="f_num", property="fNum", jdbcType=JdbcType.INTEGER)
    })
    List<PatternLk> selectByExample(PatternLkExample example);

    @Select({
        "select",
        "pattern_lk_id, pattern_name, t_num, f_num",
        "from pattern_lk",
        "where pattern_lk_id = #{patternLkId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="pattern_lk_id", property="patternLkId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="pattern_name", property="patternName", jdbcType=JdbcType.VARCHAR),
        @Result(column="t_num", property="tNum", jdbcType=JdbcType.INTEGER),
        @Result(column="f_num", property="fNum", jdbcType=JdbcType.INTEGER)
    })
    PatternLk selectByPrimaryKey(String patternLkId);

    @UpdateProvider(type=PatternLkSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PatternLk record, @Param("example") PatternLkExample example);

    @UpdateProvider(type=PatternLkSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PatternLk record, @Param("example") PatternLkExample example);

    @UpdateProvider(type=PatternLkSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PatternLk record);

    @Update({
        "update pattern_lk",
        "set pattern_name = #{patternName,jdbcType=VARCHAR},",
          "t_num = #{tNum,jdbcType=INTEGER},",
          "f_num = #{fNum,jdbcType=INTEGER}",
        "where pattern_lk_id = #{patternLkId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(PatternLk record);

    @Select({
            "select pl.pattern_lk_id,pl.pattern_name",
            "from pattern_lk as pl,version_pattern_rel as vp",
            "where vp.version_id = #{versionId,jdbcType=VARCHAR} and pl.pattern_lk_id = vp.pattern_id"
    })
    @Results({
            @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
            @Result(column="pattern_name", property="patternName", jdbcType=JdbcType.VARCHAR),
    })
    List<PatternItem> selectPatternItemListByVersionId(String versionId);
}