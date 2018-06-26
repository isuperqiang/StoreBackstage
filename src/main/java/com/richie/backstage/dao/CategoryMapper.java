package com.richie.backstage.dao;

import com.richie.backstage.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author richie on 2018.06.26
 */
@Mapper
@Repository
public interface CategoryMapper {
    /**
     * 创建分类
     *
     * @param name
     * @param seq
     * @param userId
     * @return primary key
     * @throws SQLException
     */
    int createCategory(@Param("name") String name, @Param("sequence") int seq, @Param("user_id") int userId) throws SQLException;

    /**
     * 更新分类
     *
     * @param name
     * @param seq
     * @param catId
     * @return affected rows
     * @throws SQLException
     */
    int updateCategory(@Param("name") String name, @Param("sequence") int seq, @Param("cat_id") int catId) throws SQLException;

    /**
     * 删除分类
     *
     * @param catId
     * @return affected rows
     * @throws SQLException
     */
    int deleteCategory(@Param("cat_id") int catId) throws SQLException;

    /**
     * 查询所有分类
     *
     * @param userId
     * @return
     */
    List<Category> queryAllCategories(@Param("user_id") int userId);

    /**
     * 查询最大的序号
     *
     * @param userId
     * @return
     */
    Integer queryMaxSeq(@Param("user_id") int userId);
}
