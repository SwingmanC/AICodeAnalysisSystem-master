package org.nju.demo.dao;

import org.nju.demo.entity.Feature;
import org.nju.demo.entity.FeatureExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface FeatureMapper {
    @SelectProvider(type=FeatureSqlProvider.class, method="countByExample")
    long countByExample(FeatureExample example);

    @DeleteProvider(type=FeatureSqlProvider.class, method="deleteByExample")
    int deleteByExample(FeatureExample example);

    @Delete({
        "delete from feature",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into feature (version_id, file_path)",
        "values (#{versionId,jdbcType=INTEGER}, #{filePath,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Feature record);

    @InsertProvider(type=FeatureSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Feature record);

    @SelectProvider(type=FeatureSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.INTEGER),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR)
    })
    List<Feature> selectByExample(FeatureExample example);

    @Select({
        "select",
        "id, version_id, file_path",
        "from feature",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="version_id", property="versionId", jdbcType=JdbcType.INTEGER),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR)
    })
    Feature selectByPrimaryKey(Integer id);

    @UpdateProvider(type=FeatureSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Feature record, @Param("example") FeatureExample example);

    @UpdateProvider(type=FeatureSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Feature record, @Param("example") FeatureExample example);

    @UpdateProvider(type=FeatureSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Feature record);

    @Update({
        "update feature",
        "set version_id = #{versionId,jdbcType=INTEGER},",
          "file_path = #{filePath,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Feature record);
}