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
import org.nju.demo.entity.IssueSource;
import org.nju.demo.entity.IssueSourceExample;

public interface IssueSourceMapper {
    @SelectProvider(type=IssueSourceSqlProvider.class, method="countByExample")
    long countByExample(IssueSourceExample example);

    @DeleteProvider(type=IssueSourceSqlProvider.class, method="deleteByExample")
    int deleteByExample(IssueSourceExample example);

    @Delete({
        "delete from issue_source",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into issue_source (issue_id, file_name, ",
        "file_path, start_line, ",
        "target_function, snippet)",
        "values (#{issueId,jdbcType=VARCHAR}, #{fileName,jdbcType=VARCHAR}, ",
        "#{filePath,jdbcType=VARCHAR}, #{startLine,jdbcType=INTEGER}, ",
        "#{targetFunction,jdbcType=VARCHAR}, #{snippet,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(IssueSource record);

    @InsertProvider(type=IssueSourceSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(IssueSource record);

    @SelectProvider(type=IssueSourceSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="issue_id", property="issueId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="snippet", property="snippet", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<IssueSource> selectByExampleWithBLOBs(IssueSourceExample example);

    @SelectProvider(type=IssueSourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="issue_id", property="issueId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR)
    })
    List<IssueSource> selectByExample(IssueSourceExample example);

    @Select({
        "select",
        "id, issue_id, file_name, file_path, start_line, target_function, snippet",
        "from issue_source",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="issue_id", property="issueId", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="snippet", property="snippet", jdbcType=JdbcType.LONGVARCHAR)
    })
    IssueSource selectByPrimaryKey(Integer id);

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
        "set issue_id = #{issueId,jdbcType=VARCHAR},",
          "file_name = #{fileName,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "start_line = #{startLine,jdbcType=INTEGER},",
          "target_function = #{targetFunction,jdbcType=VARCHAR},",
          "snippet = #{snippet,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(IssueSource record);

    @Update({
        "update issue_source",
        "set issue_id = #{issueId,jdbcType=VARCHAR},",
          "file_name = #{fileName,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "start_line = #{startLine,jdbcType=INTEGER},",
          "target_function = #{targetFunction,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(IssueSource record);
}