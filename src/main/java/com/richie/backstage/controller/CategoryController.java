package com.richie.backstage.controller;

import com.richie.backstage.config.Constant;
import com.richie.backstage.domain.Category;
import com.richie.backstage.handler.ListResult;
import com.richie.backstage.handler.Result;
import com.richie.backstage.service.CategoryService;
import com.richie.backstage.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author richie on 2018.06.26
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // {"name":"电子产品"}
    @PostMapping("/create")
    public Result createCategory(@CookieValue(Constant.USER_TOKEN) String token, @RequestBody Category category,
                                 HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        boolean created = categoryService.createCategory(category.getName(), userId);
        if (created) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.CREATE_CATEGORY_FAILED);
        }
    }

    // {"catId":1, "name":"电子产品"}
    @PostMapping("/update")
    public Result updateCategory(@RequestBody Category category) {
        boolean updated = categoryService.updateCategory(category);
        if (updated) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.UPDATE_CATEGORY_FAILED);
        }
    }

    @PostMapping("/delete")
    public Result deleteCategory(@RequestBody Category category) {
        boolean deleted = categoryService.deleteCategory(category.getCatId());
        if (deleted) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.DELETE_CATEGORY_FAILED);
        }
    }

    @PostMapping("/query")
    public ListResult queryAllCategories(@CookieValue(Constant.USER_TOKEN) String token, @RequestParam(value = "page_index",
            defaultValue = "1") int pageIndex, @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "name", required = false) String name, HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        logger.info("pageIndex:{}, pageSize:{}, name:{}", pageIndex, pageSize, name);
        List<Category> categories = categoryService.queryAllCategories(pageIndex, pageSize, name, userId);
        if (categories != null) {
            int count;
            if (StringUtils.isEmpty(name)) {
                count = categoryService.queryCount(userId);
            } else {
                count = categories.size();
            }
            return ListResult.createOk(categories, count);
        } else {
            return ListResult.createNo(Result.ErrorCode.QUERY_CATEGORY_FAILED.getMessage());
        }
    }
}
