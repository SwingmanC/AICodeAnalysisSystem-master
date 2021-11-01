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
import org.nju.demo.entity.AVersion;
import org.nju.demo.entity.AVersionExample;

public interface AVersionMapper {
    @SelectProvider(type=AVersionSqlProvider.class, method="countByExample")
    long countByExample(AVersionExample example);

    @DeleteProvider(type=AVersionSqlProvider.class, method="deleteByExample")
    int deleteByExample(AVersionExample example);

    @Delete({
        "delete from a_version",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into a_version (version, file_path, ",
        "project_id, last_id)",
        "values (#{version,jdbcType=VARCHAR}, #{filePath,jdbcType=VARCHAR}, ",
        "#{projectId,jdbcType=INTEGER}, #{lastId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(AVersion record);

    @InsertProvider(type=AVersionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(AVersion record);

    @SelectProvider(type=AVersionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="version", property="version", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.INTEGER),
        @Result(column="last_id", property="lastId", jdbcType=JdbcType.INTEGER)
    })
    List<AVersion> selectByExample(AVersionExample example);

    @Select({
        "select",
        "id, version, file_path, project_id, last_id",
        "from a_version",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="version", property="version", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.INTEGER),
        @Result(column="last_id", property="lastId", jdbcType=JdbcType.INTEGER)
    })
    AVersion selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AVersionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AVersion record, @Param("example") AVersionExample example);

    @UpdateProvider(type=AVersionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AVersion record, @Param("example") AVersionExample example);

    @UpdateProvider(type=AVersionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AVersion record);

    @Update({
        "update a_version",
        "set version = #{version,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "project_id = #{projectId,jdbcType=INTEGER},",
          "last_id = #{lastId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AVersion record);
}