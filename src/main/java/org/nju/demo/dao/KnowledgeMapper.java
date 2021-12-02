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
import org.nju.demo.entity.Knowledge;
import org.nju.demo.entity.KnowledgeExample;

public interface KnowledgeMapper {
    @SelectProvider(type=KnowledgeSqlProvider.class, method="countByExample")
    long countByExample(KnowledgeExample example);

    @DeleteProvider(type=KnowledgeSqlProvider.class, method="deleteByExample")
    int deleteByExample(KnowledgeExample example);

    @Delete({
        "delete from knowledge",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into knowledge (knowledge_name, content, ",
        "pattern_id)",
        "values (#{knowledgeName,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
        "#{patternId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Knowledge record);

    @InsertProvider(type=KnowledgeSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Knowledge record);

    @SelectProvider(type=KnowledgeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="knowledge_name", property="knowledgeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.INTEGER)
    })
    List<Knowledge> selectByExample(KnowledgeExample example);

    @Select({
        "select",
        "id, knowledge_name, content, pattern_id",
        "from knowledge",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="knowledge_name", property="knowledgeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.INTEGER)
    })
    Knowledge selectByPrimaryKey(Integer id);

    @UpdateProvider(type=KnowledgeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Knowledge record, @Param("example") KnowledgeExample example);

    @UpdateProvider(type=KnowledgeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Knowledge record, @Param("example") KnowledgeExample example);

    @UpdateProvider(type=KnowledgeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Knowledge record);

    @Update({
        "update knowledge",
        "set knowledge_name = #{knowledgeName,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "pattern_id = #{patternId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Knowledge record);
}