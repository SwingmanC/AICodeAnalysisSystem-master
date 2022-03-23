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
import org.nju.demo.entity.PatternStatistics;
import org.nju.demo.entity.PatternStatisticsExample;
import org.nju.demo.pojo.dto.PatternStatisticsDTO;

public interface PatternStatisticsMapper {
    @SelectProvider(type=PatternStatisticsSqlProvider.class, method="countByExample")
    long countByExample(PatternStatisticsExample example);

    @DeleteProvider(type=PatternStatisticsSqlProvider.class, method="deleteByExample")
    int deleteByExample(PatternStatisticsExample example);

    @Delete({
        "delete from pattern_statistics",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into pattern_statistics (issue_num, v_p_id)",
        "values (#{issueNum,jdbcType=INTEGER}, #{vPId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(PatternStatistics record);

    @InsertProvider(type=PatternStatisticsSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(PatternStatistics record);

    @SelectProvider(type=PatternStatisticsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="issue_num", property="issueNum", jdbcType=JdbcType.INTEGER),
        @Result(column="v_p_id", property="vPId", jdbcType=JdbcType.INTEGER)
    })
    List<PatternStatistics> selectByExample(PatternStatisticsExample example);

    @Select({
        "select",
        "id, issue_num, v_p_id",
        "from pattern_statistics",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="issue_num", property="issueNum", jdbcType=JdbcType.INTEGER),
        @Result(column="v_p_id", property="vPId", jdbcType=JdbcType.INTEGER)
    })
    PatternStatistics selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PatternStatisticsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PatternStatistics record, @Param("example") PatternStatisticsExample example);

    @UpdateProvider(type=PatternStatisticsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PatternStatistics record, @Param("example") PatternStatisticsExample example);

    @UpdateProvider(type=PatternStatisticsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PatternStatistics record);

    @Update({
        "update pattern_statistics",
        "set issue_num = #{issueNum,jdbcType=INTEGER},",
          "v_p_id = #{vPId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PatternStatistics record);

    @Select({
            "select pl.pattern_name,vp.pattern_id,vp.version_id,ps.issue_num",
            "from pattern_lk as pl,version_pattern_rel as vp,pattern_statistics as ps",
            "where vp.version_id = #{versionId,jdbcType=VARCHAR} and vp.pattern_id = pl.pattern_lk_id and vp.id = ps.v_p_id"
    })
    @Results({
            @Result(column="pattern_name", property="patternName", jdbcType=JdbcType.VARCHAR),
            @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
            @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR),
            @Result(column="issue_num", property="issueNum", jdbcType=JdbcType.INTEGER)
    })
    List<PatternStatisticsDTO> selectPatternStatisticsByVersionId(String versionId);

    @Select({
            "select issue_num",
            "from pattern_statistics as ps,version_pattern_rel as vp",
            "where vp.version_id = #{versionId,jdbcType=VARCHAR} and vp.pattern_id = #{patternId,jdbcType=VARCHAR} and vp.id = ps.v_p_id"
    })
    int selectIssueNumByRelation(String versionId,String patternId);
}