package com.richie.backstage.service;

import com.richie.backstage.dao.CategoryMapper;
import com.richie.backstage.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author richie on 2018.06.26
 */
@Service
public class CategoryService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CategoryMapper categoryMapper;

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public boolean createCategory(String name, int userId) {
        Integer maxSeq = categoryMapper.queryMaxSeq(userId);
        if (maxSeq == null) {
            maxSeq = 0;
        }
        try {
            int key = categoryMapper.createCategory(name, maxSeq + 1, userId);
            return key > 0;
        } catch (SQLException e) {
            logger.error("create cat failed", e);
        }
        return false;
    }

    public boolean updateCategory(Category category) {
        try {
            int affected = categoryMapper.updateCategory(category.getName(), category.getSequence(), category.getCatId());
            return affected > 0;
        } catch (SQLException e) {
            logger.error("update cat failed", e);
        }
        return false;
    }

    public boolean deleteCategory(int catId) {
        try {
            int affected = categoryMapper.deleteCategory(catId);
            return affected > 0;
        } catch (SQLException e) {
            logger.error("delete cat failed", e);
        }
        return false;
    }

    public List<Category> queryAllCategories(int userId) {
        return categoryMapper.queryAllCategories(userId);
    }
}
