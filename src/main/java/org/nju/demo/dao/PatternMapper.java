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
import org.nju.demo.entity.Pattern;
import org.nju.demo.entity.PatternExample;

public interface PatternMapper {
    @SelectProvider(type=PatternSqlProvider.class, method="countByExample")
    long countByExample(PatternExample example);

    @DeleteProvider(type=PatternSqlProvider.class, method="deleteByExample")
    int deleteByExample(PatternExample example);

    @Delete({
        "delete from pattern",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into pattern (pattern_name, category_id, ",
        "likelihood, variance)",
        "values (#{patternName,jdbcType=VARCHAR}, #{categoryId,jdbcType=INTEGER}, ",
        "#{likelihood,jdbcType=DOUBLE}, #{variance,jdbcType=DOUBLE})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Pattern record);

    @InsertProvider(type=PatternSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Pattern record);

    @SelectProvider(type=PatternSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="pattern_name", property="patternName", jdbcType=JdbcType.VARCHAR),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="likelihood", property="likelihood", jdbcType=JdbcType.DOUBLE),
        @Result(column="variance", property="variance", jdbcType=JdbcType.DOUBLE)
    })
    List<Pattern> selectByExample(PatternExample example);

    @Select({
        "select",
        "id, pattern_name, category_id, likelihood, variance",
        "from pattern",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="pattern_name", property="patternName", jdbcType=JdbcType.VARCHAR),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="likelihood", property="likelihood", jdbcType=JdbcType.DOUBLE),
        @Result(column="variance", property="variance", jdbcType=JdbcType.DOUBLE)
    })
    Pattern selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PatternSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Pattern record, @Param("example") PatternExample example);

    @UpdateProvider(type=PatternSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Pattern record, @Param("example") PatternExample example);

    @UpdateProvider(type=PatternSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Pattern record);

    @Update({
        "update pattern",
        "set pattern_name = #{patternName,jdbcType=VARCHAR},",
          "category_id = #{categoryId,jdbcType=INTEGER},",
          "likelihood = #{likelihood,jdbcType=DOUBLE},",
          "variance = #{variance,jdbcType=DOUBLE}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Pattern record);
}