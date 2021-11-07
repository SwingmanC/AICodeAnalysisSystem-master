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
import org.nju.demo.entity.ATemplate;
import org.nju.demo.entity.ATemplateExample;

public interface ATemplateMapper {
    @SelectProvider(type=ATemplateSqlProvider.class, method="countByExample")
    long countByExample(ATemplateExample example);

    @DeleteProvider(type=ATemplateSqlProvider.class, method="deleteByExample")
    int deleteByExample(ATemplateExample example);

    @Delete({
        "delete from a_template",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into a_template (template_name, file_path, ",
        "`state`, user_id)",
        "values (#{templateName,jdbcType=VARCHAR}, #{filePath,jdbcType=VARCHAR}, ",
        "#{state,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ATemplate record);

    @InsertProvider(type=ATemplateSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ATemplate record);

    @SelectProvider(type=ATemplateSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="template_name", property="templateName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<ATemplate> selectByExample(ATemplateExample example);

    @Select({
        "select",
        "id, template_name, file_path, `state`, user_id",
        "from a_template",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="template_name", property="templateName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    ATemplate selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ATemplateSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ATemplate record, @Param("example") ATemplateExample example);

    @UpdateProvider(type=ATemplateSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ATemplate record, @Param("example") ATemplateExample example);

    @UpdateProvider(type=ATemplateSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ATemplate record);

    @Update({
        "update a_template",
        "set template_name = #{templateName,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "`state` = #{state,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ATemplate record);
}