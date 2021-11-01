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
import org.nju.demo.entity.AUserInfo;
import org.nju.demo.entity.AUserInfoExample;

public interface AUserInfoMapper {
    @SelectProvider(type=AUserInfoSqlProvider.class, method="countByExample")
    long countByExample(AUserInfoExample example);

    @DeleteProvider(type=AUserInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(AUserInfoExample example);

    @Delete({
        "delete from a_user_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into a_user_info (user_id, telephone, ",
        "email)",
        "values (#{userId,jdbcType=INTEGER}, #{telephone,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(AUserInfo record);

    @InsertProvider(type=AUserInfoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(AUserInfo record);

    @SelectProvider(type=AUserInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="telephone", property="telephone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR)
    })
    List<AUserInfo> selectByExample(AUserInfoExample example);

    @Select({
        "select",
        "id, user_id, telephone, email",
        "from a_user_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="telephone", property="telephone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR)
    })
    AUserInfo selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AUserInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AUserInfo record, @Param("example") AUserInfoExample example);

    @UpdateProvider(type=AUserInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AUserInfo record, @Param("example") AUserInfoExample example);

    @UpdateProvider(type=AUserInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AUserInfo record);

    @Update({
        "update a_user_info",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "telephone = #{telephone,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AUserInfo record);
}