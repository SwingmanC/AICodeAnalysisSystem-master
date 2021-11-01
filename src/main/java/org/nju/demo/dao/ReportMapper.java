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
import org.nju.demo.entity.Report;
import org.nju.demo.entity.ReportExample;

public interface ReportMapper {
    @SelectProvider(type=ReportSqlProvider.class, method="countByExample")
    long countByExample(ReportExample example);

    @DeleteProvider(type=ReportSqlProvider.class, method="deleteByExample")
    int deleteByExample(ReportExample example);

    @Delete({
        "delete from report",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into report (report_name, file_path, ",
        "upload_time, user_id)",
        "values (#{reportName,jdbcType=VARCHAR}, #{filePath,jdbcType=VARCHAR}, ",
        "#{uploadTime,jdbcType=TIMESTAMP}, #{userId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Report record);

    @InsertProvider(type=ReportSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Report record);

    @SelectProvider(type=ReportSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="report_name", property="reportName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="upload_time", property="uploadTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<Report> selectByExample(ReportExample example);

    @Select({
        "select",
        "id, report_name, file_path, upload_time, user_id",
        "from report",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="report_name", property="reportName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="upload_time", property="uploadTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    Report selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ReportSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Report record, @Param("example") ReportExample example);

    @UpdateProvider(type=ReportSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Report record, @Param("example") ReportExample example);

    @UpdateProvider(type=ReportSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Report record);

    @Update({
        "update report",
        "set report_name = #{reportName,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "upload_time = #{uploadTime,jdbcType=TIMESTAMP},",
          "user_id = #{userId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Report record);
}