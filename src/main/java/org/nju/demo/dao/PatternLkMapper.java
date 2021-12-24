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
import org.nju.demo.entity.PatternLk;
import org.nju.demo.entity.PatternLkExample;

public interface PatternLkMapper {
    @SelectProvider(type=PatternLkSqlProvider.class, method="countByExample")
    long countByExample(PatternLkExample example);

    @DeleteProvider(type=PatternLkSqlProvider.class, method="deleteByExample")
    int deleteByExample(PatternLkExample example);

    @Delete({
        "delete from pattern_lk",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into pattern_lk (pattern_id, t_num, ",
        "f_num)",
        "values (#{patternId,jdbcType=VARCHAR}, #{tNum,jdbcType=INTEGER}, ",
        "#{fNum,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(PatternLk record);

    @InsertProvider(type=PatternLkSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(PatternLk record);

    @SelectProvider(type=PatternLkSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
        @Result(column="t_num", property="tNum", jdbcType=JdbcType.INTEGER),
        @Result(column="f_num", property="fNum", jdbcType=JdbcType.INTEGER)
    })
    List<PatternLk> selectByExample(PatternLkExample example);

    @Select({
        "select",
        "id, pattern_id, t_num, f_num",
        "from pattern_lk",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
        @Result(column="t_num", property="tNum", jdbcType=JdbcType.INTEGER),
        @Result(column="f_num", property="fNum", jdbcType=JdbcType.INTEGER)
    })
    PatternLk selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PatternLkSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PatternLk record, @Param("example") PatternLkExample example);

    @UpdateProvider(type=PatternLkSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PatternLk record, @Param("example") PatternLkExample example);

    @UpdateProvider(type=PatternLkSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PatternLk record);

    @Update({
        "update pattern_lk",
        "set pattern_id = #{patternId,jdbcType=VARCHAR},",
          "t_num = #{tNum,jdbcType=INTEGER},",
          "f_num = #{fNum,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PatternLk record);
}