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
import org.nju.demo.entity.FIssue;
import org.nju.demo.entity.FIssueExample;
import org.nju.demo.entity.FIssueWithBLOBs;

public interface FIssueMapper {
    @SelectProvider(type=FIssueSqlProvider.class, method="countByExample")
    long countByExample(FIssueExample example);

    @DeleteProvider(type=FIssueSqlProvider.class, method="deleteByExample")
    int deleteByExample(FIssueExample example);

    @Delete({
        "delete from f_issue",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into f_issue (i_id, category, ",
        "priority, kingdom, ",
        "description, file_name, ",
        "file_path, start_line, ",
        "target_function, report_id, ",
        "snippet, recommendation)",
        "values (#{iId,jdbcType=VARCHAR}, #{category,jdbcType=VARCHAR}, ",
        "#{priority,jdbcType=VARCHAR}, #{kingdom,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR}, #{fileName,jdbcType=VARCHAR}, ",
        "#{filePath,jdbcType=VARCHAR}, #{startLine,jdbcType=INTEGER}, ",
        "#{targetFunction,jdbcType=VARCHAR}, #{reportId,jdbcType=INTEGER}, ",
        "#{snippet,jdbcType=LONGVARCHAR}, #{recommendation,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(FIssueWithBLOBs record);

    @InsertProvider(type=FIssueSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(FIssueWithBLOBs record);

    @SelectProvider(type=FIssueSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="i_id", property="iId", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="priority", property="priority", jdbcType=JdbcType.VARCHAR),
        @Result(column="kingdom", property="kingdom", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="report_id", property="reportId", jdbcType=JdbcType.INTEGER),
        @Result(column="snippet", property="snippet", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="recommendation", property="recommendation", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<FIssueWithBLOBs> selectByExampleWithBLOBs(FIssueExample example);

    @SelectProvider(type=FIssueSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="i_id", property="iId", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="priority", property="priority", jdbcType=JdbcType.VARCHAR),
        @Result(column="kingdom", property="kingdom", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="report_id", property="reportId", jdbcType=JdbcType.INTEGER)
    })
    List<FIssue> selectByExample(FIssueExample example);

    @Select({
        "select",
        "id, i_id, category, priority, kingdom, description, file_name, file_path, start_line, ",
        "target_function, report_id, snippet, recommendation",
        "from f_issue",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="i_id", property="iId", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="priority", property="priority", jdbcType=JdbcType.VARCHAR),
        @Result(column="kingdom", property="kingdom", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="report_id", property="reportId", jdbcType=JdbcType.INTEGER),
        @Result(column="snippet", property="snippet", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="recommendation", property="recommendation", jdbcType=JdbcType.LONGVARCHAR)
    })
    FIssueWithBLOBs selectByPrimaryKey(Integer id);

    @UpdateProvider(type=FIssueSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FIssueWithBLOBs record, @Param("example") FIssueExample example);

    @UpdateProvider(type=FIssueSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") FIssueWithBLOBs record, @Param("example") FIssueExample example);

    @UpdateProvider(type=FIssueSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FIssue record, @Param("example") FIssueExample example);

    @UpdateProvider(type=FIssueSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FIssueWithBLOBs record);

    @Update({
        "update f_issue",
        "set i_id = #{iId,jdbcType=VARCHAR},",
          "category = #{category,jdbcType=VARCHAR},",
          "priority = #{priority,jdbcType=VARCHAR},",
          "kingdom = #{kingdom,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "file_name = #{fileName,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "start_line = #{startLine,jdbcType=INTEGER},",
          "target_function = #{targetFunction,jdbcType=VARCHAR},",
          "report_id = #{reportId,jdbcType=INTEGER},",
          "snippet = #{snippet,jdbcType=LONGVARCHAR},",
          "recommendation = #{recommendation,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(FIssueWithBLOBs record);

    @Update({
        "update f_issue",
        "set i_id = #{iId,jdbcType=VARCHAR},",
          "category = #{category,jdbcType=VARCHAR},",
          "priority = #{priority,jdbcType=VARCHAR},",
          "kingdom = #{kingdom,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "file_name = #{fileName,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "start_line = #{startLine,jdbcType=INTEGER},",
          "target_function = #{targetFunction,jdbcType=VARCHAR},",
          "report_id = #{reportId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FIssue record);
}