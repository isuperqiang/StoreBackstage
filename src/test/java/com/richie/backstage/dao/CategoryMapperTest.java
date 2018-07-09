package com.richie.backstage.dao;

import com.richie.backstage.domain.Category;
import com.richie.backstage.domain.User;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author richie on 2018.07.09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryMapperTest {
    private CategoryMapper categoryMapper;

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Test
    @Transactional
    public void createCategory() throws SQLException {
        Category category = new Category();
        category.setUser(new User(1));
        category.setName("123");
        int i = categoryMapper.createCategory(category);
        assertThat(i, Matchers.greaterThan(0));
    }

    @Test
    @Transactional
    public void updateCategory() throws SQLException {
        Category category = new Category();
        category.setName("123");
        category.setCatId(5);
        int i = categoryMapper.updateCategory(category);
        assertThat(i, Matchers.greaterThan(0));
    }

    @Test
    @Transactional
    public void deleteCategory() throws SQLException {
        int i = categoryMapper.deleteCategory(5);
        assertThat(i, Matchers.greaterThan(0));
    }

    @Test
    public void queryCategoryByPage() {
        List<Category> categories = categoryMapper.queryCategoryByPage("", 1, 10, 1);
        assertThat(categories, Matchers.notNullValue());
    }

    @Test
    public void queryMaxSeq() {
        Integer integer = categoryMapper.queryMaxSeq(1);
        assertThat(integer, Matchers.greaterThan(0));
    }

    @Test
    public void queryCatCount() {
        int i = categoryMapper.queryCatCount(1);
        assertThat(i, Matchers.greaterThan(0));
    }

    @Test
    public void queryCatIdByName() {
        Integer integer = categoryMapper.queryCatIdByName("电脑");
        assertThat(integer, Matchers.greaterThan(0));
    }
}