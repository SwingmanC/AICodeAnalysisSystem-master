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
import org.nju.demo.entity.PriorityStatistics;
import org.nju.demo.entity.PriorityStatisticsExample;

public interface PriorityStatisticsMapper {
    @SelectProvider(type=PriorityStatisticsSqlProvider.class, method="countByExample")
    long countByExample(PriorityStatisticsExample example);

    @DeleteProvider(type=PriorityStatisticsSqlProvider.class, method="deleteByExample")
    int deleteByExample(PriorityStatisticsExample example);

    @Delete({
        "delete from priority_statistics",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into priority_statistics (low_num, medium_num, ",
        "high_num, critical_num, ",
        "version_id)",
        "values (#{lowNum,jdbcType=INTEGER}, #{mediumNum,jdbcType=INTEGER}, ",
        "#{highNum,jdbcType=INTEGER}, #{criticalNum,jdbcType=INTEGER}, ",
        "#{versionId,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(PriorityStatistics record);

    @InsertProvider(type=PriorityStatisticsSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(PriorityStatistics record);

    @SelectProvider(type=PriorityStatisticsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="low_num", property="lowNum", jdbcType=JdbcType.INTEGER),
        @Result(column="medium_num", property="mediumNum", jdbcType=JdbcType.INTEGER),
        @Result(column="high_num", property="highNum", jdbcType=JdbcType.INTEGER),
        @Result(column="critical_num", property="criticalNum", jdbcType=JdbcType.INTEGER),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR)
    })
    List<PriorityStatistics> selectByExample(PriorityStatisticsExample example);

    @Select({
        "select",
        "id, low_num, medium_num, high_num, critical_num, version_id",
        "from priority_statistics",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="low_num", property="lowNum", jdbcType=JdbcType.INTEGER),
        @Result(column="medium_num", property="mediumNum", jdbcType=JdbcType.INTEGER),
        @Result(column="high_num", property="highNum", jdbcType=JdbcType.INTEGER),
        @Result(column="critical_num", property="criticalNum", jdbcType=JdbcType.INTEGER),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR)
    })
    PriorityStatistics selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PriorityStatisticsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PriorityStatistics record, @Param("example") PriorityStatisticsExample example);

    @UpdateProvider(type=PriorityStatisticsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PriorityStatistics record, @Param("example") PriorityStatisticsExample example);

    @UpdateProvider(type=PriorityStatisticsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PriorityStatistics record);

    @Update({
        "update priority_statistics",
        "set low_num = #{lowNum,jdbcType=INTEGER},",
          "medium_num = #{mediumNum,jdbcType=INTEGER},",
          "high_num = #{highNum,jdbcType=INTEGER},",
          "critical_num = #{criticalNum,jdbcType=INTEGER},",
          "version_id = #{versionId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PriorityStatistics record);
}