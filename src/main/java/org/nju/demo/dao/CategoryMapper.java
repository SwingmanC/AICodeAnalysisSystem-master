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
import org.nju.demo.entity.Category;
import org.nju.demo.entity.CategoryExample;

public interface CategoryMapper {
    @SelectProvider(type=CategorySqlProvider.class, method="countByExample")
    long countByExample(CategoryExample example);

    @DeleteProvider(type=CategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(CategoryExample example);

    @Delete({
        "delete from category",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into category (category_name, likelihood)",
        "values (#{categoryName,jdbcType=VARCHAR}, #{likelihood,jdbcType=DOUBLE}) "
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Category record);

    @InsertProvider(type=CategorySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Category record);

    @SelectProvider(type=CategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="category_name", property="categoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="likelihood", property="likelihood", jdbcType=JdbcType.DOUBLE)
    })
    List<Category> selectByExample(CategoryExample example);

    @Select({
        "select",
        "id, category_name, likelihood",
        "from category",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="category_name", property="categoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="likelihood", property="likelihood", jdbcType=JdbcType.DOUBLE)
    })
    Category selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Category record);

    @Update({
        "update category",
        "set category_name = #{categoryName,jdbcType=VARCHAR},",
          "likelihood = #{likelihood,jdbcType=DOUBLE}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Category record);
}