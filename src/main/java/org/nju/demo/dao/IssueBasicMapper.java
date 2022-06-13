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
import org.nju.demo.entity.IssueBasic;
import org.nju.demo.entity.IssueBasicExample;
import org.nju.demo.pojo.dto.IssueDTO;
import org.nju.demo.pojo.vo.IssueFeature;

public interface IssueBasicMapper {
    @SelectProvider(type=IssueBasicSqlProvider.class, method="countByExample")
    long countByExample(IssueBasicExample example);

    @DeleteProvider(type=IssueBasicSqlProvider.class, method="deleteByExample")
    int deleteByExample(IssueBasicExample example);

    @Delete({
        "delete from issue_basic",
        "where issue_id = #{issueId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String issueId);

    @Insert({
        "insert into issue_basic (issue_id, pattern_id, ",
        "priority, kingdom, ",
        "file_name, file_path, ",
        "start_line, target_function, ",
        "description, version_id, ",
        "`state`, flag, snippet)",
        "values (#{issueId,jdbcType=VARCHAR}, #{patternId,jdbcType=VARCHAR}, ",
        "#{priority,jdbcType=VARCHAR}, #{kingdom,jdbcType=VARCHAR}, ",
        "#{fileName,jdbcType=VARCHAR}, #{filePath,jdbcType=VARCHAR}, ",
        "#{startLine,jdbcType=INTEGER}, #{targetFunction,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR}, #{versionId,jdbcType=VARCHAR}, ",
        "#{state,jdbcType=VARCHAR}, #{flag,jdbcType=INTEGER}, #{snippet,jdbcType=LONGVARCHAR})"
    })
    int insert(IssueBasic record);

    @InsertProvider(type=IssueBasicSqlProvider.class, method="insertSelective")
    int insertSelective(IssueBasic record);

    @SelectProvider(type=IssueBasicSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="issue_id", property="issueId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
        @Result(column="priority", property="priority", jdbcType=JdbcType.VARCHAR),
        @Result(column="kingdom", property="kingdom", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
        @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
        @Result(column="snippet", property="snippet", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<IssueBasic> selectByExampleWithBLOBs(IssueBasicExample example);

    @SelectProvider(type=IssueBasicSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="issue_id", property="issueId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
        @Result(column="priority", property="priority", jdbcType=JdbcType.VARCHAR),
        @Result(column="kingdom", property="kingdom", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
        @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER)
    })
    List<IssueBasic> selectByExample(IssueBasicExample example);

    @Select({
        "select",
        "issue_id, pattern_id, priority, kingdom, file_name, file_path, start_line, target_function, ",
        "description, version_id, `state`, flag, snippet",
        "from issue_basic",
        "where issue_id = #{issueId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="issue_id", property="issueId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
        @Result(column="priority", property="priority", jdbcType=JdbcType.VARCHAR),
        @Result(column="kingdom", property="kingdom", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
        @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
        @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
        @Result(column="snippet", property="snippet", jdbcType=JdbcType.LONGVARCHAR)
    })
    IssueBasic selectByPrimaryKey(String issueId);

    @UpdateProvider(type=IssueBasicSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") IssueBasic record, @Param("example") IssueBasicExample example);

    @UpdateProvider(type=IssueBasicSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") IssueBasic record, @Param("example") IssueBasicExample example);

    @UpdateProvider(type=IssueBasicSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") IssueBasic record, @Param("example") IssueBasicExample example);

    @UpdateProvider(type=IssueBasicSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(IssueBasic record);

    @Update({
        "update issue_basic",
        "set pattern_id = #{patternId,jdbcType=VARCHAR},",
          "priority = #{priority,jdbcType=VARCHAR},",
          "kingdom = #{kingdom,jdbcType=VARCHAR},",
          "file_name = #{fileName,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "start_line = #{startLine,jdbcType=INTEGER},",
          "target_function = #{targetFunction,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "version_id = #{versionId,jdbcType=VARCHAR},",
          "`state` = #{state,jdbcType=VARCHAR},",
          "flag = #{flag,jdbcType=INTEGER},",
          "snippet = #{snippet,jdbcType=LONGVARCHAR}",
        "where issue_id = #{issueId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(IssueBasic record);

    @Update({
        "update issue_basic",
        "set pattern_id = #{patternId,jdbcType=VARCHAR},",
          "priority = #{priority,jdbcType=VARCHAR},",
          "kingdom = #{kingdom,jdbcType=VARCHAR},",
          "file_name = #{fileName,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "start_line = #{startLine,jdbcType=INTEGER},",
          "target_function = #{targetFunction,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "version_id = #{versionId,jdbcType=VARCHAR},",
          "`state` = #{state,jdbcType=VARCHAR},",
          "flag = #{flag,jdbcType=INTEGER}",
        "where issue_id = #{issueId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(IssueBasic record);

    @Select({
            "select v.issue_id,v.kingdom,v.file_name,v.target_function,v.start_line,v.state,v.pattern_id,p.pattern_name",
            "from issue_basic as i,pattern_lk as p",
            "where i.version_id = #{versionId,jdbcType=VARCHAR} and i.priority = #{priority,jdbcType=VARCHAR} and i.flag = #{flag,jdbcType=INTEGER} and i.pattern_id = p.pattern_lk_id"
    })
    @Results({
            @Result(column="issue_id", property="issueId", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
            @Result(column="priority", property="priority", jdbcType=JdbcType.VARCHAR),
            @Result(column="kingdom", property="kingdom", jdbcType=JdbcType.VARCHAR),
            @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
            @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
            @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
            @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR),
            @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
    })
    List<IssueDTO> selectIssueByVersionIdAndPriority(String versionId,String priority,int flag);

    @Select({
            "select issue_id,pattern_id,priority,kingdom,file_name,start_line,snippet,target_function,version_id,state",
            "from issue_basic",
            "where state = 'False' or state = 'True'"
    })
    @Results({
            @Result(column="issue_id", property="issueId", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
            @Result(column="priority", property="priority", jdbcType=JdbcType.VARCHAR),
            @Result(column="kingdom", property="kingdom", jdbcType=JdbcType.VARCHAR),
            @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
            @Result(column="start_line", property="startLine", jdbcType=JdbcType.INTEGER),
            @Result(column="target_function", property="targetFunction", jdbcType=JdbcType.VARCHAR),
            @Result(column="version_id", property="versionId", jdbcType=JdbcType.VARCHAR),
            @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
            @Result(column="snippet", property="snippet", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<IssueBasic> selectClassifiedIssueList();
}