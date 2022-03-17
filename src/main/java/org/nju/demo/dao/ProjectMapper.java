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
import org.nju.demo.entity.Project;
import org.nju.demo.entity.ProjectExample;

public interface ProjectMapper {
    @SelectProvider(type=ProjectSqlProvider.class, method="countByExample")
    long countByExample(ProjectExample example);

    @DeleteProvider(type=ProjectSqlProvider.class, method="deleteByExample")
    int deleteByExample(ProjectExample example);

    @Delete({
        "delete from project",
        "where project_id = #{projectId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String projectId);

    @Insert({
        "insert into project (project_id, project_name, ",
        "description, create_time, ",
        "user_id)",
        "values (#{projectId,jdbcType=VARCHAR}, #{projectName,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{userId,jdbcType=INTEGER})"
    })
    int insert(Project record);

    @InsertProvider(type=ProjectSqlProvider.class, method="insertSelective")
    int insertSelective(Project record);

    @SelectProvider(type=ProjectSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="project_name", property="projectName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<Project> selectByExample(ProjectExample example);

    @Select({
        "select",
        "project_id, project_name, description, create_time, user_id",
        "from project",
        "where project_id = #{projectId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="project_name", property="projectName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    Project selectByPrimaryKey(String projectId);

    @UpdateProvider(type=ProjectSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    @UpdateProvider(type=ProjectSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    @UpdateProvider(type=ProjectSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Project record);

    @Update({
        "update project",
        "set project_name = #{projectName,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "user_id = #{userId,jdbcType=INTEGER}",
        "where project_id = #{projectId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Project record);
}