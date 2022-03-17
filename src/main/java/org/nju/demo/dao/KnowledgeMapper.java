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
import org.nju.demo.entity.Knowledge;
import org.nju.demo.entity.KnowledgeExample;

public interface KnowledgeMapper {
    @SelectProvider(type=KnowledgeSqlProvider.class, method="countByExample")
    long countByExample(KnowledgeExample example);

    @DeleteProvider(type=KnowledgeSqlProvider.class, method="deleteByExample")
    int deleteByExample(KnowledgeExample example);

    @Delete({
        "delete from knowledge",
        "where knowledge_id = #{knowledgeId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String knowledgeId);

    @Insert({
        "insert into knowledge (knowledge_id, knowledge_name, ",
        "create_time, pattern_id, ",
        "content)",
        "values (#{knowledgeId,jdbcType=VARCHAR}, #{knowledgeName,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{patternId,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    int insert(Knowledge record);

    @InsertProvider(type=KnowledgeSqlProvider.class, method="insertSelective")
    int insertSelective(Knowledge record);

    @SelectProvider(type=KnowledgeSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="knowledge_id", property="knowledgeId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="knowledge_name", property="knowledgeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Knowledge> selectByExampleWithBLOBs(KnowledgeExample example);

    @SelectProvider(type=KnowledgeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="knowledge_id", property="knowledgeId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="knowledge_name", property="knowledgeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR)
    })
    List<Knowledge> selectByExample(KnowledgeExample example);

    @Select({
        "select",
        "knowledge_id, knowledge_name, create_time, pattern_id, content",
        "from knowledge",
        "where knowledge_id = #{knowledgeId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="knowledge_id", property="knowledgeId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="knowledge_name", property="knowledgeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pattern_id", property="patternId", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    Knowledge selectByPrimaryKey(String knowledgeId);

    @UpdateProvider(type=KnowledgeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Knowledge record, @Param("example") KnowledgeExample example);

    @UpdateProvider(type=KnowledgeSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") Knowledge record, @Param("example") KnowledgeExample example);

    @UpdateProvider(type=KnowledgeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Knowledge record, @Param("example") KnowledgeExample example);

    @UpdateProvider(type=KnowledgeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Knowledge record);

    @Update({
        "update knowledge",
        "set knowledge_name = #{knowledgeName,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "pattern_id = #{patternId,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where knowledge_id = #{knowledgeId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(Knowledge record);

    @Update({
        "update knowledge",
        "set knowledge_name = #{knowledgeName,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "pattern_id = #{patternId,jdbcType=VARCHAR}",
        "where knowledge_id = #{knowledgeId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Knowledge record);
}