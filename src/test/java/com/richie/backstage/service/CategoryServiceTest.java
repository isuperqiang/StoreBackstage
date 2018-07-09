package com.richie.backstage.service;

import com.richie.backstage.domain.Category;
import com.richie.backstage.domain.User;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author richie on 2018.07.09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceTest {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Test
    @Transactional
    public void createCategory() {
        Category category = new Category();
        category.setUser(new User(1));
        category.setName("123");
        boolean b = categoryService.createCategory(category);
        assertThat(b, Matchers.equalTo(true));
    }

    @Test
    @Transactional
    public void updateCategory() {
        Category category = new Category();
        category.setCatId(5);
        category.setName("123");
        boolean b = categoryService.updateCategory(category);
        assertThat(b, Matchers.equalTo(true));
    }

    @Test
    @Transactional
    public void deleteCategory() {
        boolean b = categoryService.deleteCategory(5);
        assertThat(b, Matchers.equalTo(true));
    }

    @Test
    public void queryAllCategories() {
    }

    @Test
    public void queryCatCount() {
    }
}