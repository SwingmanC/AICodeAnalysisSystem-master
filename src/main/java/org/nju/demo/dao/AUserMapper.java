package org.nju.demo.dao;

import org.nju.demo.entity.AUser;
import org.nju.demo.entity.AUserExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AUserMapper {
    @SelectProvider(type=AUserSqlProvider.class, method="countByExample")
    long countByExample(AUserExample example);

    @DeleteProvider(type=AUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(AUserExample example);

    @Delete({
        "delete from a_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into a_user (username, `password`)",
        "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(AUser record);

    @InsertProvider(type=AUserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(AUser record);

    @SelectProvider(type=AUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    List<AUser> selectByExample(AUserExample example);

    @Select({
        "select",
        "id, username, `password`",
        "from a_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    AUser selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AUser record, @Param("example") AUserExample example);

    @UpdateProvider(type=AUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AUser record, @Param("example") AUserExample example);

    @UpdateProvider(type=AUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AUser record);

    @Update({
        "update a_user",
        "set username = #{username,jdbcType=VARCHAR},",
          "`password` = #{password,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AUser record);
}