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
import org.nju.demo.entity.IssueSource;
import org.nju.demo.entity.IssueSourceExample;

public interface IssueSourceMapper {
    @SelectProvider(type=IssueSourceSqlProvider.class, method="countByExample")
    long countByExample(IssueSourceExample example);

    @DeleteProvider(type=IssueSourceSqlProvider.class, method="deleteByExample")
    int deleteByExample(IssueSourceExample example);

    @Delete({
        "delete from issue_source",
        "where issue_source_id = #{issueSourceId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String issueSourceId);

    @Insert({
        "insert into issue_source (issue_source_id, issue_basic_id, ",
        "file_name, file_path, ",
        "start_line, target_function, ",
        "snippet)",
        "values (#{issueSourceId,jdbcType=VARCHAR}, #{issueBasicId,jdbcType=VARCHAR}, ",
        "#{fileName,jdbcType=VARCHAR}, #{filePath,jdbcType=VARCHAR}, ",
        "#{startLine,jdbcType=INTEGER}, #{targetFunction,jdbcType=VARCHAR}, ",
        "#{snippet,jdbcType=LONGVARCHAR})"
    })
    int insert(IssueSource record);

    @InsertProvider(type=IssueSourceSqlProvider.class, method="insertSelective")
    int insertSelective(IssueSource record);

    @SelectProvider(type=IssueSourceSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="issue_source_id", property="issueSourceId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="issue_basic_id", property="issueBasicId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="snippet", property="snippet", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<IssueSource> selectByExampleWithBLOBs(IssueSourceExample example);

    @SelectProvider(type=IssueSourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="issue_source_id", property="issueSourceId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="issue_basic_id", property="issueBasicId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR)
    })
    List<IssueSource> selectByExample(IssueSourceExample example);

    @Select({
        "select",
        "issue_source_id, issue_basic_id, file_name, file_path, start_line, target_function, ",
        "snippet",
        "from issue_source",
        "where issue_source_id = #{issueSourceId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="issue_source_id", property="issueSourceId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="issue_basic_id", property="issueBasicId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="snippet", property="snippet", jdbcType=JdbcType.LONGVARCHAR)
    })
    IssueSource selectByPrimaryKey(String issueSourceId);

    @UpdateProvider(type=IssueSourceSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") IssueSource record, @Param("example") IssueSourceExample example);

    @UpdateProvider(type=IssueSourceSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") IssueSource record, @Param("example") IssueSourceExample example);

    @UpdateProvider(type=IssueSourceSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") IssueSource record, @Param("example") IssueSourceExample example);

    @UpdateProvider(type=IssueSourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(IssueSource record);

    @Update({
        "update issue_source",
        "set issue_basic_id = #{issueBasicId,jdbcType=VARCHAR},",
          "file_name = #{fileName,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "start_line = #{startLine,jdbcType=INTEGER},",
          "target_function = #{targetFunction,jdbcType=VARCHAR},",
          "snippet = #{snippet,jdbcType=LONGVARCHAR}",
        "where issue_source_id = #{issueSourceId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(IssueSource record);

    @Update({
        "update issue_source",
        "set issue_basic_id = #{issueBasicId,jdbcType=VARCHAR},",
          "file_name = #{fileName,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "start_line = #{startLine,jdbcType=INTEGER},",
          "target_function = #{targetFunction,jdbcType=VARCHAR}",
        "where issue_source_id = #{issueSourceId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(IssueSource record);
}