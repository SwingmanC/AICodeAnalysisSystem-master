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
import org.nju.demo.entity.VersionPatternRel;
import org.nju.demo.entity.VersionPatternRelExample;

public interface VersionPatternRelMapper {
    @SelectProvider(type=VersionPatternRelSqlProvider.class, method="countByExample")
    long countByExample(VersionPatternRelExample example);

    @DeleteProvider(type=VersionPatternRelSqlProvider.class, method="deleteByExample")
    int deleteByExample(VersionPatternRelExample example);

    @Delete({
        "delete from version_pattern_rel",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into version_pattern_rel (version_id, pattern_id)",
        "values (#{versionId,jdbcType=VARCHAR}, #{patternId,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(VersionPatternRel record);

    @InsertProvider(type=VersionPatternRelSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(VersionPatternRel record);

    @SelectProvider(type=VersionPatternRelSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR)
    })
    List<VersionPatternRel> selectByExample(VersionPatternRelExample example);

    @Select({
        "select",
        "id, version_id, pattern_id",
        "from version_pattern_rel",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR)
    })
    VersionPatternRel selectByPrimaryKey(Integer id);

    @UpdateProvider(type=VersionPatternRelSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") VersionPatternRel record, @Param("example") VersionPatternRelExample example);

    @UpdateProvider(type=VersionPatternRelSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") VersionPatternRel record, @Param("example") VersionPatternRelExample example);

    @UpdateProvider(type=VersionPatternRelSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VersionPatternRel record);

    @Update({
        "update version_pattern_rel",
        "set version_id = #{versionId,jdbcType=VARCHAR},",
          "pattern_id = #{patternId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VersionPatternRel record);

    @Select({"select id,version_id,pattern_id from version_pattern_rel order by id desc limit 1"})
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR),
            @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR)
    })
    VersionPatternRel selectLastRecord();
}