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
     * @param category
     * @return affected rows
     * @throws SQLException
     */
    int createCategory(@Param("category") Category category) throws SQLException;

    /**
     * 更新分类
     *
     * @param category
     * @return affected rows
     * @throws SQLException
     */
    int updateCategory(@Param("category") Category category) throws SQLException;

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
     * @param name
     * @param pageIndex
     * @param pageSize
     * @param userId
     * @return
     */
    List<Category> queryCategoryByPage(@Param("name") String name, @Param("page_index") int pageIndex,
                                       @Param("page_size") int pageSize, @Param("user_id") int userId);

    /**
     * 查询最大的序号
     *
     * @param userId
     * @return
     */
    Integer queryMaxSeq(@Param("user_id") int userId);

    /**
     * 查询数量
     *
     * @param userId
     * @return
     */
    int queryCatCount(@Param("user_id") int userId);

    /**
     * 根据名称查询 ID
     *
     * @param name
     * @return
     */
    Integer queryCatIdByName(@Param("name") String name);
}
